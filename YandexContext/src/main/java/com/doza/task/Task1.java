package com.doza.task;

import java.io.IOException;
import java.util.*;

public class Task1 {
    public static void main(String[] args) throws IOException {

        Scanner cons = new Scanner(System.in);

        int n = cons.nextInt();
        int[] carters = new int[n];

        for (int i = 0; i < n; i++) {
            carters[i] = cons.nextInt();
        }

        int[] line = new int[n];

        for (int i = 0; i < n; i++) {
            line[i] = cons.nextInt();
        }

        int k = cons.nextInt();
        int[] str = new int[k];

        for (int i = 0; i < k; i++) {
            str[i] = cons.nextInt();
        }

        Map<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            mp.put(carters[i], line[i]);
        }

        int count = 0;

        for (int i = 0; i < str.length - 1; i++) {
            if (!Objects.equals(mp.get(str[i]), mp.get(str[i + 1]))) {
                count++;
            }
        }

        System.out.println(count);
    }
}