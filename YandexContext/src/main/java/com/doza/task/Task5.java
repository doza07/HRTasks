package com.doza.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = br.readLine().split(" ");
        int N = Integer.parseInt(input1[0]);
        int K = Integer.parseInt(input1[1]);

        String S = br.readLine();
        int[] p = new int[N];
        int[] d = new int[N];
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(input2[i]) - 1;
        }

        String[] input3 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            d[i] = Integer.parseInt(input3[i]);
        }

        long[] powerSum = new long[26];
        List<Integer>[] positions = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            positions[i] = new ArrayList<>();
        }

        long totalPower = 0;
        for (int i = 0; i < N; i++) {
            int c = S.charAt(i) - 'a';

            totalPower += positions[c].size();
            positions[c].add(i);

            int base = c;
            int delta = d[i];
            for (int j = 1; j <= K; j++) {
                int newIndex = (base + (j - 1) * delta) % 26;
                for (int idx : positions[c]) {
                    powerSum[newIndex] += (i - idx) / K + 1;
                }
            }
        }

        long result = totalPower;
        for (long sum : powerSum) {
            result += sum;
        }

        System.out.println(result);
    }
}