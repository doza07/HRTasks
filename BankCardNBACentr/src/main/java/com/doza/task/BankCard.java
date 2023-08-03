package com.doza.task;

public abstract class BankCard {
    private int balance;




    //    «Пополнить» — пополняет карту на переданную сумму;
    public void topUp(int num) {
        balance += num;
    }

    //    «Оплатить» — списывает с карты переданную сумму и возвращает результат типа Boolean;
    public boolean pay(int num) {
        if (balance - num >= 0) {
            balance -= num;
            return true;
        } else {
            System.out.println("Недостаточно средств");
            return false;
        }
    }
//    «Получить информацию о балансе»;

    public int getBalance() {
        return balance;
    }

    //    «Получить информацию о доступных средствах» — возвращает информацию о балансе, кредитном лимите и любых других средствах.
    public void getInfo() {
        System.out.println("Собственные средства: " + getBalance());
    }

    //Функции
    public void menu() {
        System.out.print("""
                Выберите :
                0.Выход
                1.Пополнить баланс
                2.Заплатить
                3.Посмотреть баланс
                4.Посмотреть информацию
                """);
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
