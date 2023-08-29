package com.doza.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int[] minIncome = new int[N];
        for (int i = 0; i < N; i++) {
            minIncome[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(br.readLine());
        int[] requiresEducation = new int[N];
        for (int i = 0; i < N; i++) {
            requiresEducation[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(br.readLine());
        int[] allowsChildren = new int[N];
        for (int i = 0; i < N; i++) {
            allowsChildren[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int Q = Integer.parseInt(br.readLine());
        tokenizer = new StringTokenizer(br.readLine());
        int[] incomes = new int[Q];
        for (int i = 0; i < Q; i++) {
            incomes[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(br.readLine());
        int[] hasEducation = new int[Q];
        for (int i = 0; i < Q; i++) {
            hasEducation[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(br.readLine());
        int[] parentCitizenships = new int[Q];
        for (int i = 0; i < Q; i++) {
            parentCitizenships[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] chosenCountries = new int[Q];
        for (int i = 0; i < Q; i++) {
            boolean foundCountry = false;
            for (int j = 0; j < N; j++) {
                if ((parentCitizenships[i] == j + 1 || allowsChildren[j] == 1) &&
                        incomes[i] >= minIncome[j] && hasEducation[i] >= requiresEducation[j]) {
                    chosenCountries[i] = j + 1;
                    foundCountry = true;
                    break;
                }
            }
            if (!foundCountry) {
                chosenCountries[i] = 0;
            }
        }

        for (int i = 0; i < Q; i++) {
            System.out.print(chosenCountries[i] + " ");
        }
    }
}