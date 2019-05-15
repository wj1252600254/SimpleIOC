package com.sjtu.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Utils {
    public static String readFile(String path) {
        String content = "";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String temp = "";
            while ((temp = bufferedReader.readLine()) != null) {
                content += temp;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    public static ArrayList<String> split(String str, String split) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, split);
        ArrayList<String> arrayList = new ArrayList<>();
        while (stringTokenizer.hasMoreElements()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return arrayList;
    }

}
