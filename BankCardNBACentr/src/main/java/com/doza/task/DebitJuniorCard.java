package com.doza.task;

public class DebitJuniorCard extends DebitCard{

    private int accumulation; //Накопления в виде 2% от остатка суммы на карте(после 3 опираций ссрабатывает накпление)
    private int count = 0;

    @Override
    public void Debit() {
        menu();

        System.out.println("5.Перевести накопления на карту");

        String option = cons.next();
        switch (option) {
            case "1" -> {
                System.out.println("Введите сумму");
                topUp(cons.nextInt());
                System.out.println("Ваш баланс: " + getBalance());
            }
            case "2" -> {
                count++;
                System.out.println("Введите сумму");
                pay(cons.nextInt());
                if (count == 3) {
                    accumulation += getBalance() * 2 / 100;
                    count = 0;

                }
                System.out.println("Ваш баланс: " + getBalance());
            }
            case "3" -> {
                System.out.println("Ваш баланс: " + getBalance());
            }
            case "4" -> {
                getInfo();
                System.out.println("Накоплено бонусов: " + getAccumulation());
            }
            case "5" -> {
                System.out.println("У вас накоплено: " + getAccumulation() + "\nВведите сумму накоплений которую хотите перевести на карту: ");
                int sum = cons.nextInt();
                if (sum > getAccumulation()) {
                    System.out.println("Недостаточно накоплений");
                } else {
                    topUp(sum);
                    accumulation -= sum;
                }
            }
        }
    }

    public int getAccumulation() {
        return accumulation;
    }

    public void setAccumulation(int accumulation) {
        this.accumulation = accumulation;
    }
}
