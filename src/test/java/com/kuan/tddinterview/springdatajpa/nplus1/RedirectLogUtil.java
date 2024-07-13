package com.kuan.tddinterview.springdatajpa.nplus1;

import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Supplier;

public class RedirectLogUtil {

    @Getter
    private static ByteArrayOutputStream outputStream;

    static protected void redirectOutputLog(Supplier<Object> supplier) {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        supplier.get();
        System.setOut(System.out);
    }

    static protected int getSelectTimes(String text) {
        return text.split("Hibernate: select", -1).length - 1;
    }

}
