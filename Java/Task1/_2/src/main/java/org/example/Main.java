package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, Long> top5HashTags = Solution.top5HashTags(new ArrayList<>(
                List.of(
                        "#a, #b #c. #d #e #q? #w #t #y #u!!!",
                        "#1 2 #3 #5 #5 #6!",
                        "#1 2? #3 #5 #5 #6.",
                        "#1 2: #3 #4 #5 #6 7...",
                        "#1 #2 #3 - #4 #5 #7")));

        System.out.println(top5HashTags);
    }
}