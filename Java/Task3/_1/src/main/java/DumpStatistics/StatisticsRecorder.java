package DumpStatistics;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.stream.XMLStreamException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class StatisticsRecorder {
    public static void record(String folderPath, ExecutorService executorService) throws XMLStreamException, IOException, ExecutionException, InterruptedException {
        Collection<FineReport> fineReports = DumpCollector.collect(folderPath, executorService);
        Map<Integer, Map<String, Double>> fineStatistics = StatisticsCalculator.calculate(fineReports);

        Path statisticsDump = Path.of(folderPath);
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(statisticsDump.resolve("stats.json").toString())
        )) {
            ObjectMapper mapper = new ObjectMapper();
            writer.write(mapper.writeValueAsString(fineStatistics));
        }
    }
}
