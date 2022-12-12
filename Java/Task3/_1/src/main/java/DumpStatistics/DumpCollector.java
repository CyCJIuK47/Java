package DumpStatistics;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class DumpCollector {

    public static Collection<FineReport> collect(String folderPath, ExecutorService executorService) throws XMLStreamException, FileNotFoundException, ExecutionException, InterruptedException {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        Collection<FineReport> fineReports = new ArrayList<>();
        List<File> files = Arrays.stream(listOfFiles)
                .filter(x -> x.isFile() && x.getName().endsWith(".xml"))
                .collect(Collectors.toList());

        List<CompletableFuture<Collection<FineReport>>> processFileFutures = files.stream()
                .map(file -> processFile(file, executorService))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                processFileFutures.toArray(new CompletableFuture[0]));

        CompletableFuture<List<Collection<FineReport>>> allFileProcessFuture = allFutures.thenApply(v -> {
            return processFileFutures.stream()
                    .map(fileProcessFuture -> fileProcessFuture.join())
                    .collect(Collectors.toList());
        });

        CompletableFuture<Collection<FineReport>> dumpFuture = allFileProcessFuture.thenApply(reports ->
        {
            List<FineReport> res = new ArrayList<>();
            reports.forEach(res::addAll);
            return res;
        });

        fineReports = dumpFuture.get();
        return fineReports;
    }

    private static CompletableFuture<Collection<FineReport>> processFile(File file, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            Collection<FineReport> r = null;
            try {
                r = FileProcessor.process(file.getPath());
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return r;

        }, executorService);
    }

    ;
}
