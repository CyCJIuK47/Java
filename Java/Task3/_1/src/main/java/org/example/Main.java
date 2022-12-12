package org.example;

import DumpStatistics.StatisticsRecorder;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) throws XMLStreamException, IOException, ExecutionException, InterruptedException {
        String folderPath = "folderPath";
        ExecutorService executor1 = Executors.newFixedThreadPool(1);

        long start1 = System.currentTimeMillis();
        StatisticsRecorder.record(folderPath, executor1);
        long finish1 = System.currentTimeMillis();
        System.out.println(finish1 - start1);

        executor1.shutdown();

        ExecutorService executor2 = Executors.newFixedThreadPool(2);

        long start2 = System.currentTimeMillis();
        StatisticsRecorder.record(folderPath, executor2);
        long finish2 = System.currentTimeMillis();
        System.out.println(finish2 - start2);

        executor2.shutdown();

        ExecutorService executor3 = Executors.newFixedThreadPool(4);

        long start3 = System.currentTimeMillis();
        StatisticsRecorder.record(folderPath, executor3);
        long finish3 = System.currentTimeMillis();
        System.out.println(finish3 - start3);

        executor3.shutdown();

        ExecutorService executor4 = Executors.newFixedThreadPool(8);

        long start4 = System.currentTimeMillis();
        StatisticsRecorder.record(folderPath, executor4);
        long finish4 = System.currentTimeMillis();
        System.out.println(finish4 - start4);

        executor4.shutdown();
    }
}