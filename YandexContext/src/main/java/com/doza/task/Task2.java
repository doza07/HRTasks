package com.doza.task;


import java.util.*;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args)  {

            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt(), ideal_w = sc.nextInt(), T = sc.nextInt();

            int[] weights = new int[N];

            for (int i = 0; i < N; i++) {
                weights[i] = sc.nextInt();
            }

            Map<Integer, Integer> mapper = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                mapper.put(i, Math.abs(weights[i - 1] - ideal_w));
            }

            LinkedList<Map.Entry<Integer, Integer>> mapperList = new LinkedList<>(mapper.entrySet());
            mapperList.sort(Map.Entry.comparingByValue());

            List<Integer> ids = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : mapperList) {
                T -= entry.getValue();
                if (T >= 0) {
                    ids.add(entry.getKey());
                } else {
                    break;
                }
            }
            System.out.println(ids.size());
            System.out.println(ids.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }