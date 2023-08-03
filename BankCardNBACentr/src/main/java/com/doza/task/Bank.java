package com.doza.task;

import java.util.Scanner;

public class Bank {

    DebitCard debitCard = new DebitCard();
    CreditCard creditCard = new CreditCard();
    DebitJuniorCard debitJuniorCard = new DebitJuniorCard();
    CreditJobCard creditJobCard = new CreditJobCard();

    public void startBank() {

        Scanner cons = new Scanner(System.in);
        boolean b = true;
        while (b) {
            System.out.println("""
                    Выберите :
                    1.Карты
                    2.Выход""");

            String choose = cons.next();

            switch (choose) {
                case "1" -> {
                    System.out.println("""
                            Выберите карту :
                            1.Дебетовая карта
                            2.Кредитная крата
                            3.Дебетовая детская карта
                            4.Кредитная рабочая карта""");
                    String chooseCard = cons.next();
                    switch (chooseCard) {
                        case "1" -> debitCard.Debit();
                        case "2" -> creditCard.Credit();
                        case "3" -> debitJuniorCard.Debit();
                        case "4" -> creditJobCard.Credit();
                    }
                }
                case "2" -> {
                    System.out.println("Хорошего дня");
                    b = false;
                }
            }
        }
    }
}
