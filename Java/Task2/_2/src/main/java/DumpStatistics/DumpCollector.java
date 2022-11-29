package DumpStatistics;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

public class DumpCollector {

    public static Collection<FineReport> collect(String folderPath) throws XMLStreamException, FileNotFoundException {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        Collection<FineReport> fineReports = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".xml")) {
                fineReports.addAll(
                        FileProcessor.process(listOfFiles[i].getPath())
                );
            }
        }
        return  fineReports;
    }
}
