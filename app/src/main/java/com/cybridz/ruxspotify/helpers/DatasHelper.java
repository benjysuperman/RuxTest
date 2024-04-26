package com.cybridz.ruxspotify.helpers;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DatasHelper {
    private static Map<String, StringBuilder> descriptions = new HashMap<>();

    public static String getDescription(AssetManager assetManager, String key) {
        if (descriptions.containsKey(key)) {
            return Objects.requireNonNull(descriptions.get(key)).toString();
        }
        StringBuilder description = new StringBuilder();
        try {
            InputStream is = assetManager.open("descriptions");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(key + ";")) {
                    description.append(line.split(";", 2)[1]);
                    descriptions.put(key, description);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return description.toString();
    }
}
