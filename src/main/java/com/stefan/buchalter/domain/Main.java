package com.stefan.buchalter.domain;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        AReport aReport = new AReport(2017);

//        System.out.println(aReport.toString());

        List<String> l = new ArrayList<>();
        l.add("a");
        l.add(1, "b");
        l.remove(0);
        l.remove(1);

        System.out.println("L=" + l.toString());
    }

}
