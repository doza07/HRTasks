package com.doza.task;

import java.util.Scanner;

public class CreditCard extends BankCard {
    private int creditLimit;
    private int creditBalance;


    Scanner cons = new Scanner(System.in);

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Кредитные средства: " + creditBalance);
        System.out.println("Всего средств: " + (getBalance() + creditBalance));
    }




    public void Credit() {

        menu();

        System.out.println("5.Поменять кредитный лимит");

        String option = cons.next();
        switch (option) {
            case "1" -> {
                System.out.println("Введите сумму");
                if (creditBalance < creditLimit) {
                    creditBalance += cons.nextInt();
                    if (creditBalance > creditLimit) {
                        int residue = creditBalance - creditLimit;
                        creditBalance -= residue;
                        topUp(residue);
                    }
                } else {
                    topUp(cons.nextInt());
                }
                System.out.println("Собственные средства: " + getBalance());
                System.out.println("Кредитные средства: " + this.creditBalance);
            }
            case "2" -> {
                System.out.println("Введите сумму :");
                int pay = cons.nextInt();
                if (pay <= getBalance()) {
                    pay(pay);
                } else if (pay > getBalance() + creditBalance) {
                    System.out.println("Недостаточно средств");
                } else if (pay > getBalance()) {
                    int credit = pay - getBalance();
                    setBalance(0);
                    creditBalance -= credit;

                }
                System.out.println("Ваш баланс: " + (getBalance() + creditBalance));
            }
            case "3" -> System.out.println("Ваш баланс: " + (getBalance() + this.creditBalance));
            case "4" -> this.getInfo();
            case "5" -> {
                System.out.println("Введите лимит: ");
                setCreditLimit(cons.nextInt());
            }
        }
    }


    public int getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(int creditBalance) {
        this.creditBalance = creditBalance;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        if (creditLimit > this.creditLimit) {
            int res = this.creditLimit - creditBalance;
            this.creditLimit = creditLimit;
            creditBalance = creditLimit - res;
        }
    }
}
