package com.cojored;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileTranslator {
    private final ReplaceFile matches;
    private final ReplaceFile special;
    private final FileWriter file;

    FileTranslator(String name) {
        matches = new ReplaceFile("general");
        special = new ReplaceFile("special");
        try {
            file = new FileWriter("./" + name.replace("jShort", "java"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void process(String line) {
        String[] characters = line.split("");
        ArrayList<String> finalLine = new ArrayList<String>();
        String temp = "";

        for (int i = 0; i < characters.length; i++) {
            String match = matches.getMatch(characters[i]);

            if (special.getMatch(characters[i]) != null) {
                match = matches.getMatch(temp);
                if (match == null) match = temp;
                temp = "";
            }

            if (match != null) finalLine.add(match);

            if (special.getMatch(characters[i]) != null) finalLine.add(characters[i]);
            else temp += characters[i];

            if (characters.length - 1 == i) {
                String m = matches.getMatch(temp);
                if(m != null)
                    finalLine.add(m);
                else
                    finalLine.add(temp);
            }
        }

        if (String.join("", finalLine).endsWith(")")) finalLine.add(";");

        try {
            file.write(String.join("", finalLine) + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void end() throws IOException {
        file.close();
    }
}
