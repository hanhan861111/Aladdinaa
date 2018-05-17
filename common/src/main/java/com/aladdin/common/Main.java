package com.aladdin.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ruijinglin
 * @date: 2018/2/3/0003
 * Explain:      .
 */

public class Main {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");


        System.err.println(list.size());
        for (String s : list) {
            if (s.equals("2")){
                s = null;
            }
        }
        System.err.println(list.size());
        list.clear();
        System.err.println(list.size());
    }

}
