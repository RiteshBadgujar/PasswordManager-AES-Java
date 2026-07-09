package com.ritesh.passwordmanager;

import java.util.Base64;

public class TestBase64 {

    public static void main(String[] args) {

        String text = "Hello";

        String encoded = Base64.getEncoder().encodeToString(text.getBytes());

        System.out.println(encoded);
    }
}