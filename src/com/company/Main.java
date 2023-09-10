package com.company;

import org.w3c.dom.Element;

import java.lang.annotation.ElementType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        HashMap<Double, ArrayList<String>> map1 = new HashMap<>();

        ArrayList<String> listOfWords = new ReadText().go(".\\text.txt");
        ArrayList<String> listOfUniqueWords = new ArrayList<>();
        ArrayList<Double> listOfUniqueFrequency = new ArrayList<>();

        int n = listOfWords.toArray().length;

        for (String word : listOfWords) {
            Object value = map.get(word);
            map.put(word, (value == null) ? (1) : ((int) value + 1));
            if (value == null) {
                listOfUniqueWords.add(word);
            }
        }

        for (String word : listOfUniqueWords) {
            double frequency = map.get(word);
            ArrayList<String> v = map1.get(frequency);
            if (v == null) {
                v = new ArrayList<>();
                v.add(word);
                listOfUniqueFrequency.add(frequency);
            } else {
                v.add(word);
            }
            map1.put(frequency, v);
        }


        System.out.println("Частота ---- Слова с данной частотой");
        for (Double freq : listOfUniqueFrequency) {
            System.out.println(freq + "     ---> " + map1.get(freq));
        }


//        ReadText rt = new ReadText();
//        rt.go(".\\text.txt");
//        System.out.print(Math.asin(0.5)/Math.PI * 180);
    }
}
