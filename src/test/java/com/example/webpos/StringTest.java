package com.example.webpos;

import org.junit.jupiter.api.Test;

public class StringTest {
    @Test
    void subString() {

        String s = " @민진기";

        String substring = s.trim().substring(1);
        System.out.println(substring);


    }

}
