package com.example.android.booklistingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by mostafa on 1/6/18.
 */

public class QuaryUtils {
    private final static String LOG_TAG = QuaryUtils.class.getName();

    public static ArrayList<BookData> fetchData(String Url) throws IOException {
        URL url = CreateUrl(Url);
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error with fetchData method : " + e.getMessage());
        }
        return getResponse(jsonResponse);
    }


    private static ArrayList<BookData> getResponse(String jsonRsponse) {
        ArrayList<BookData> arrayList = new ArrayList<>();
        String priceMessage, author, url;
        try {
            JSONObject jsonObject = new JSONObject(jsonRsponse);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject itemsObject = itemsArray.getJSONObject(i);
                JSONObject volumeInfoObject = itemsObject.getJSONObject("volumeInfo");
                String title = volumeInfoObject.getString("title");
                //I must work here to make it write all authors names.
                if (volumeInfoObject.has("authors")) {
                    author = volumeInfoObject.getJSONArray("authors").getString(0);
                } else {
                    author = "";
                }

                if (volumeInfoObject.has("infoLink")) {
                    url = volumeInfoObject.getString("infoLink");
                } else {
                    url = "";
                }
                JSONObject saleInfoObject = itemsObject.getJSONObject("saleInfo");

                if (saleInfoObject.has("listPrice")) {
                    JSONObject listPriceObject = saleInfoObject.getJSONObject("listPrice");
                    String price = listPriceObject.getString("amount");
                    String currencyCode = listPriceObject.getString("currencyCode");
                    priceMessage = price + " " + currencyCode;
                } else {
                    priceMessage = "not availabe ";
                }
                arrayList.add(new BookData(title, author, priceMessage, url));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error with getResponse method. : " + e.getMessage());
        }
        return arrayList;
    }

    private static URL CreateUrl(String murl) {
        URL url = null;

        try {
            url = new URL(murl);
        } catch (MalformedURLException r) {
            Log.e(LOG_TAG, "Error with creat Url method. the error is :" + r.getMessage());
        }
        return url;
    }

    /*
    To make Http request and retrive data as a json formate .
    @param url The location of json string to grap.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return "";
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = ConvertInputstreamToJson(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error With get jsonresponse from makeHttpRequest method the error is : " + e.getMessage());
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;

    }

    /*
    Converting inputstream to string of characters .
     */
    private static String ConvertInputstreamToJson(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }
}
