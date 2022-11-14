package org.example;

import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Collection<Integer> res = Solution.positiveAndSorted(List.of(10, 2, 3, 1, 5, 4, 4, -1, -10));
        System.out.println(res);
    }
}