package XMLProcessor;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class XMLParser {
    public static void parse(String filepath) throws IOException {

        File inputFile = new File(filepath);
        Scanner scanner = new Scanner(inputFile);

        Path processedFilePath = Path.of(filepath);
        processedFilePath = processedFilePath
                .getParent()
                .resolve("[Fixed]" + processedFilePath.getFileName());
        BufferedWriter writer = new BufferedWriter(new FileWriter(processedFilePath.toFile()));

        //find <persons> tag
        scanner.useDelimiter("<");
        String personsTag = scanner.next();
        //scanner.next
        writer.write("<" + personsTag);

        //to find other <person/> tags
        scanner.useDelimiter("<");
        while(scanner.hasNext()) {
            String tag = "<" + scanner.next();

            // the last tag found
            if (!scanner.hasNext()) {
                writer.write(tag);
                break;
            }

            String processedTag = TagParser.parse(tag);
            writer.write(processedTag);
        }

        scanner.close();
        writer.close();
    }
}
