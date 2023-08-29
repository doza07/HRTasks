package com.doza.task;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int[] prices = new int[N];
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(tokenizer.nextToken());
        }

        List<Pair> transactions = new ArrayList<>();
        int capital = 1;
        int buyDay = -1;

        for (int i = 0; i < N - 1; i++) {
            if (prices[i] < prices[i + 1] && capital > 0) {
                buyDay = i;
                capital = 0;
            } else if (prices[i] > prices[i + 1] && capital == 0) {
                transactions.add(new Pair(buyDay + 1, i + 1));
                capital = 1;
            }
        }

        if (capital == 0 && buyDay != N - 1) {
            transactions.add(new Pair(buyDay + 1, N));
        }

        System.out.println(transactions.size());
        for (Pair pair : transactions) {
            System.out.println(pair.buy + " " + pair.sell);
        }
    }

    static class Pair {
        int buy, sell;

        Pair(int buy, int sell) {
            this.buy = buy;
            this.sell = sell;
        }
    }
}