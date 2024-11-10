package org.semyonq;

import org.semyonq.exceptions.BadBirthdayException;
import org.semyonq.exceptions.BadFullnameException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Profile {
    private final String name;
    private final String age;
    private final String sex;

    public Profile(String fullname, String birthday) throws BadFullnameException, BadBirthdayException {
        fullname = fullname.trim();
        name = fullnameToName(fullname);
        sex = defineSex(fullname);
        age = birthdayToAge(birthday.trim());
    }
    private String fullnameToName(String fullname) throws BadFullnameException {
        String[] tokens = fullname.split(" ");

        if(tokens.length != 3)
            throw new BadFullnameException("Ожидалось ФИО из трёх частей");
        return String.format("%s %c.%c.", tokens[0], tokens[1].charAt(0), tokens[2].charAt(0));
    }

    private String birthdayToAge(String birthday) throws BadBirthdayException {
        String[] tokens = birthday.split("\\.");
        if(tokens.length != 3)
            throw new BadBirthdayException("Неправильный формат даты (ожидалась дата в формате дд.мм.гггг):");
        long years;
        try {
            years = ChronoUnit.YEARS.between(LocalDate.of(Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[0])), LocalDate.now());
        } catch (Exception e) {
            throw new BadBirthdayException("Неправильный формат даты (ожидалась дата в формате дд.мм.гггг):");
        }
        String res = String.valueOf(years);
        if(years % 10 == 1 && years % 100 != 11)
            res += " год";
        else if ((years % 10 > 1 && years % 10 < 5) && !(years % 100 > 11 && years % 100 < 15))
            res += " года";
        else
            res += " лет";
        return res;
    }

    private String defineSex(String fullname) {
        String[] tokens = fullname.split(" ");
        String patronymic = tokens[2].toLowerCase();
        if(patronymic.endsWith("ич")) return Sex.MALE;
        else if (patronymic.endsWith("на")) return Sex.FEMALE;
        else return Sex.UNDEFINED;
    }

    @Override
    public String toString() {
        return String.format("Инициалы: %s\nПол: %s\nКоличество полный лет: %s", name, sex, age);
    }
}
