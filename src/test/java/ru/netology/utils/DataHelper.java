package ru.netology.utils;

import ru.netology.data.UserData;

public class DataHelper {

    private DataHelper() {
    }

    public static UserData getValidUser() {
        return new UserData("vasya", "qwerty123", "12345");
    }

    public static String getFirstCardNumber() {
        return "5559 0000 0000 0001";
    }

    public static String getSecondCardNumber() {
        return "5559 0000 0000 0002";
    }

    public static String getFirstCardId() {
        return "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    }

    public static String getSecondCardId() {
        return "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
    }
}