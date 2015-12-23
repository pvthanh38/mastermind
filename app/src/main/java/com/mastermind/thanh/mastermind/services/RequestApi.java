package com.mastermind.thanh.mastermind.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import com.mastermind.thanh.mastermind.constant.APIConstant;
import com.mastermind.thanh.mastermind.models.Header;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thanh on 12/22/2015.
 */
public class RequestApi {
    public String getInfoUsingGET(String urlServer) { //neu goi api co truyen them bien thi String xxx
        //Log.d("TAG","Chuoi nhan duoc = "+xxx);
        String result = "";
        HttpURLConnection connection = null;
        StringBuilder jsonString = new StringBuilder();
        try {
            // StringBuilder stringUrl = new StringBuilder(url);
            URL url = new URL(urlServer);
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader input = new InputStreamReader(
                    connection.getInputStream(), "UTF-8"); // iso-8859-1
            String line = null;
            // char[] buff = new char[1024];
            BufferedReader buff = new BufferedReader(input, 8);
            while ((line = buff.readLine()) != null) {
                jsonString.append(line).append("\n");
            }
            result = jsonString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    public String getInfoUsingPOST(String urlServer, String[] keys, String[] values) {
        Log.e("TAG", urlServer + " keys " + Arrays.toString(keys) + " values " + Arrays.toString(values));
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(urlServer);
        StringBuilder result = new StringBuilder();
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(keys.length);
            for (int i = 0; i < keys.length; i++) {
                nameValuePairs.add(new BasicNameValuePair(keys[i], values[i]));
            }
            //httpPost.setHeader("Content-Type", "application/form-data");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httpPost);

            InputStreamReader inReader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
            BufferedReader buff = new BufferedReader(inReader, 8);
            String line = "";
            while ((line = buff.readLine()) != null) {
                result.append(line).append("\n");
                Log.d("LINE = ",line);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // String a = result.toString();
        return result.toString();

    }
    /**
     * @param json
     * @return the header of json
     */
    public Header getJSONHeader(String json) {
        Header result = new Header();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(json);
            node = node.get(APIConstant.HEADER);
            TypeReference<Header> typeRef = new TypeReference<Header>() {};
            result = mapper.readValue(node.traverse(), typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    public String getJSONData(String json) {
        if (json.equals(null) || json.trim().equals("1")) {
            return null;
        }
        String result ="";
        try {
            JSONObject object = new JSONObject(json);
            result = object.getString(APIConstant.DATA);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
    public Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

}
