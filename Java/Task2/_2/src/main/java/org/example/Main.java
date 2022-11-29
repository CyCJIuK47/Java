package org.example;

import DumpStatistics.*;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws XMLStreamException, IOException {
        String folderPath = "";
        StatisticsRecorder.record(folderPath);
    }
}