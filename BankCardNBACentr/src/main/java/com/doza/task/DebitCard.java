package com.doza.task;

import java.util.Scanner;

public class DebitCard extends BankCard {


    Scanner cons = new Scanner(System.in);

    @Override
    public void topUp(int num) {
        super.topUp(num);
    }

    @Override
    public void getInfo() {
        super.getInfo();
    }

    public void Debit() {

        menu();

        String option = cons.next();
        switch (option) {
            case "1" -> {
                System.out.println("Введите сумму");
                topUp(cons.nextInt());
                System.out.println("Ваш баланс: " + getBalance());
            }
            case "2" -> {
                System.out.println("Введите сумму");
                pay(cons.nextInt());
                System.out.println("Ваш баланс: " + getBalance());
            }
            case "3" -> System.out.println("Ваш баланс: " + getBalance());
            case "4" -> getInfo();
        }
    }

}
