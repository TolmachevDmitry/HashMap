package com.company;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ReadText {

    private boolean checkSymbol(String sym) {
        String regex1 = "^[a-z]$";
        String regex2 = "^[A-Z]$";
        String regex3 = "^[а-я]$";
        String regex4 = "^[А-Я]$";

        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);
        Pattern pattern4 = Pattern.compile(regex4);

        return pattern1.matcher(sym).matches() || pattern2.matcher(sym).matches() ||
                pattern3.matcher(sym).matches() || pattern4.matcher(sym).matches();
    }

    public ArrayList<String> go(String fileName) throws Exception {
        FileReader fr= new FileReader(fileName);
        Scanner scan = new Scanner(fr);

        ArrayList<String> listOfWords = new ArrayList<String>();

        StringBuilder word = new StringBuilder("");
        boolean wordIsBuilding = false;
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            for (int i = 0; i < line.length(); i++) {
                String note = "" + line.charAt(i);

                if (checkSymbol(note) || (note.equals("-") && !(i == line.length() - 1) && wordIsBuilding)) {
                    word.append(note);
                    wordIsBuilding = true;
                }
                if (wordIsBuilding && (!(checkSymbol(note) || note.equals("-")) ||
                        (i == line.length() - 1 && !note.equals("-")))) {

                    listOfWords.add(("" + word).toLowerCase(Locale.ROOT));
                    word = new StringBuilder("");
                    wordIsBuilding = false;
                }
            }
        }

        fr.close();

//        for (int i = 0; i < listOfWords.size(); i++) {
//            System.out.println("( " + listOfWords.get(i) + " )");
//        }

        return listOfWords;
    }


}
