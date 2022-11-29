package DumpStatistics;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsCalculator {

    public static Map<Integer, Map<String, Double>> calculate(Collection<FineReport> fineReports) {

        Map<Integer, List<FineReport>> yearStatistics;

        yearStatistics = fineReports
                .stream()
                .collect(Collectors.groupingBy(FineReport::getYear));


        Map<Integer, Map<String, Double>> totalStatistics = new HashMap<>();

        for (Map.Entry<Integer, List<FineReport>> entry : yearStatistics.entrySet()) {
            totalStatistics.put(entry.getKey(), calculateYearStatistics(entry.getValue()));
        }



        return yearStatistics
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                                e -> calculateYearStatistics(e.getValue())
                        )
                );
    }
    private static Map<String, Double> calculateYearStatistics(List<FineReport> yearReports) {
        Map<String, List<FineReport>> fineReports = new HashMap<>();
        fineReports = yearReports.stream().collect(Collectors.groupingBy(FineReport::getType));

        Map<String, Double> statisticsResult = new HashMap<>();
        statisticsResult = fineReports
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> (e.getValue()
                                .stream()
                                .mapToDouble(FineReport::getAmount)
                                .sum()
                        )
                ));

        final Map<String, Double> finalStatisticsResult = statisticsResult;

        TreeMap<String, Double> sortedMap = new TreeMap<>(
                (k1, k2) -> {
                    int comp = finalStatisticsResult.get(k1).compareTo(
                            finalStatisticsResult.get(k2));
                    return comp == 0 ? -1 : -comp;
                });

        sortedMap.putAll(statisticsResult);
        return sortedMap;
    }
}
