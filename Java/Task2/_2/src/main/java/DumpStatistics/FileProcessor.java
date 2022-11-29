package DumpStatistics;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

public class FileProcessor {

    public static Collection<FineReport> process(String filepath) throws XMLStreamException, FileNotFoundException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(
                new FileInputStream(filepath)
        );

        Collection<FineReport> fineReports = new ArrayList<>();

        FineReport fineReport = new FineReport();
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "fine_report":
                        fineReport = new FineReport();
                        break;
                    case "date_time":
                        Integer year = Integer.parseInt(reader.getElementText().substring(0, 4));
                        fineReport.setYear(year);
                        break;
                    case "name":
                        nextEvent = reader.nextEvent();
                        fineReport.setName(nextEvent.asCharacters().getData());
                        break;
                    case "surname":
                        nextEvent = reader.nextEvent();
                        fineReport.setSurname(nextEvent.asCharacters().getData());
                        break;
                    case "type":
                        nextEvent = reader.nextEvent();
                        fineReport.setType(nextEvent.asCharacters().getData());
                        break;
                    case "fine_amount":
                        nextEvent = reader.nextEvent();
                        fineReport.setAmount(
                                Double.parseDouble(
                                        nextEvent.asCharacters().getData()
                                )
                        );
                        break;
                }
            }
            if (nextEvent.isEndElement()) {
                EndElement endElement = nextEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("fine_report")) {
                    fineReports.add(fineReport);
                }
            }

        }

        reader.close();

        return  fineReports;
    }
}
