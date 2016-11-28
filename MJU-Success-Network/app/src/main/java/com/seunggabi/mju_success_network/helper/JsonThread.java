package com.seunggabi.mju_success_network.helper;

import org.json.JSONArray;

import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by seunggabi on 2016-11-21.
 */

public class JsonThread extends Thread {
    private JSONArray array;
    private HashMap<String, String> data;
    private String url;

    public JsonThread(HashMap<String, String> data, String url) {
        super();
        this.data = data;
        this.url = url;
    }

    public void run() {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBuilder = new FormBody.Builder();
        if(data != null) {
            for (String key : data.keySet()) {
                formBuilder.add(key, data.get(key));
            }
        }
        RequestBody requestBody= formBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            array = new JSONArray(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public JSONArray getArray() {
        return array;
    }
}
