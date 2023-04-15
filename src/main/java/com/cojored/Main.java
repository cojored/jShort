package com.cojored;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File dir = new File(".");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (child.getName().endsWith(".jShort")) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(child));
                        FileTranslator translator = new FileTranslator(child.getName());

                        String line;
                        while ((line = reader.readLine()) != null) translator.process(line);

                        translator.end();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}