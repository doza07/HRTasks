package com.doza.task;

public class CreditJobCard extends CreditCard {

    private int creditLimit;
    private int creditBalance;
    private int cashBack; //Кэшбэк в размере 5% от покупок(только за счет кредитных средств)

    @Override
    public void Credit() {

        menu();

        System.out.println("5.Поменять кредитный лимит");
        System.out.println("6.Перевести кэшбэк на карту");


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
                } else if (pay > (getBalance() + creditBalance)) {
                    System.out.println("Недостаточно средств");
                } else if (pay > getBalance()) {
                    int credit = pay - getBalance();
                    setBalance(0);
                    creditBalance -= credit;
                    cashBack += credit * 5 / 100;

                }
                System.out.println("Ваш баланс: " + (getBalance() + this.creditBalance));
            }
            case "3" -> System.out.println("Ваш баланс: " + (getBalance() + this.creditBalance));
            case "4" -> {
                System.out.println("Собственные средства: " + getBalance() + "\nКредитные средства: " + creditBalance +
                        "\nВсего средств: " + (getBalance() + creditBalance + cashBack +
                        "\nНакоплено кэшбэка: " + cashBack));
            }
            case "5" -> {
                System.out.println("Введите лимит: ");
                setCreditLimit(cons.nextInt());
            }
            case "6" -> {
                System.out.println("У вас накоплено: " + getCashBack() + "\nВведите сумму накоплений которую хотите перевести на карту: ");
                int sum = cons.nextInt();
                if (sum > getCashBack()) {
                    System.out.println("Недостаточно накоплений");
                } else {
                    if (creditBalance < creditLimit) {
                        creditBalance += sum;
                        if (creditBalance > creditLimit) {
                            int residue = creditBalance - creditLimit;
                            creditBalance -= residue;
                            topUp(residue);
                        }
                    } else {
                        topUp(cons.nextInt());
                    }
                }
            }
        }
    }

    @Override
    public int getCreditLimit() {
        return creditLimit;
    }

    @Override
    public int getCreditBalance() {
        return creditBalance;
    }

    public int getCashBack() {
        return cashBack;
    }

    public void setCreditLimit(int creditLimit) {
        int res = this.creditLimit - creditBalance;
        this.creditLimit = creditLimit;
        creditBalance = creditLimit - res;
    }
}
