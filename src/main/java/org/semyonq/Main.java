package org.semyonq;

import org.semyonq.exceptions.BadBirthdayException;
import org.semyonq.exceptions.BadFullnameException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isRun = true;
        System.out.println("Добро пожаловать!");

        while (isRun) {
            System.out.print("Введите ФИО (полностью): ");
            Scanner sc = new Scanner(System.in);
            String fullname = sc.nextLine();
            System.out.print("Введите дату рождения (в формате дд.мм.гггг): ");
            String birthday = sc.nextLine();
            System.out.println("Ваша карточка:");
            try {
                Profile profile = new Profile(fullname, birthday);
                System.out.println(profile);
            } catch (BadFullnameException | BadBirthdayException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Возникла непредвденная ошибка. Обратитесь к разработчику!\n" + e);
            }
            finally {
                System.out.print("Выйти из программы? [y/n]: ");
                String res = sc.nextLine().trim();
                if (res.equalsIgnoreCase("y") || res.equalsIgnoreCase("yes"))
                    isRun = false;
            }
        }
        System.out.println("Выход из программы");
    }
}