package com.cojored;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ReplaceFile {
    private final HashMap<String, String> matches;

    ReplaceFile(String name) {
        matches = new HashMap<String, String>();

        InputStream stream = ClassLoader.getSystemResourceAsStream(name);
        assert stream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    String[] args = line.split("=");
                    String key = args[0];
                    args[0] = "";
                    String value = String.join("=", args);
                    value = value.substring(1);
                    matches.put(key.replace("\"", ""), value.replace("\"", ""));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMatch(String str) {
        return matches.get(str);
    }
}
