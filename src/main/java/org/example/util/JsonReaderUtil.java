package org.example.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonReaderUtil {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;/*w w  w .j  ava2 s .co  m*/
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONArray readJsonArrayFromUrl(String url)
            throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray jsonArray = new JSONArray(jsonText);
            return jsonArray;
        } finally {
            is.close();
        }
    }

    public static JSONObject readJsonObjectFromUrl(String url)
            throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject jsonObject = new JSONObject(jsonText);
            return jsonObject;
        } finally {
            is.close();
        }
    }
}
