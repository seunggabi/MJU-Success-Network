package com.seunggabi.mju_success_network.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.model.bean.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by seunggabi on 2016-10-11.
 */

//Singleton Pattern & 도구 클래스
public class Tool {
    private static Tool tool;

    private Tool() {}

    public static Tool getInstance() {
        if (tool == null) {
            tool = new Tool();
        }
        return tool;
    }

    //숫자인지 확인하는 함수
    public boolean isInteger(String s, Context context) {
        try {
            int num = Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            Toast.makeText(context, "숫자만 입력 가능합니다!", LENGTH_LONG).show();
            return false;
        }
    }

    //토스트 띄워주는 함수
    public void toast(String s, Context context) {
        Toast.makeText(context, s, LENGTH_LONG).show();
    }

    //전화 거는 함수
    public void call(String tel, Context context) {
        String URI = "tel:";
        URI += tel;
        Uri number = Uri.parse(URI);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        context.startActivity(callIntent);
    }

    //서버로 Data 보내는 함수
    public void sendToServer(final HashMap<String, String> data, final String url) {
        new Thread() {
            public void run() {
                OkHttpClient client = new OkHttpClient();
                FormBody.Builder formBuilder = new FormBody.Builder();
                for (String key : data.keySet()) {
                    formBuilder.add(key, data.get(key));
                }
                RequestBody body = formBuilder.build();

                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                try {
                    client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //서버로 Data 보내고 JSON Data 받는 함수
    public JSONArray getToServer(final HashMap<String, String> data, final String url) {
        boolean check = true;
        JsonThread thread = new JsonThread(data, url);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return thread.getArray();
    }

    //네트워크 연결 확인 함수
    public boolean isNetwork(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        // wifi 또는 모바일 네트워크 어느 하나라도 연결이 되어있다면,
        if (wifi.isConnected() || mobile.isConnected()) {
            return true;
        } else {
            toast("인터넷이 연결되지 않았습니다.", context);
            return false;
        }
    }

    //화면 리로드 함수
    public void reload(Activity act) {
        act.finish();
        act.startActivity(act.getIntent());
    }

    //User 정보 가져오는 함수
    public void getUser(Context context) {
        String url = "http://" + Constants.IP + "/api/user.php?mode=get";
        User user = new User();
        user.testData();
        JSONArray array = null;
        HashMap<String, String> data = new HashMap<String, String>();
        if (FirebaseInstanceId.getInstance().getToken() != null)
            data.put("token", FirebaseInstanceId.getInstance().getToken());
        else data = null;

        if (Tool.getInstance().isNetwork(context))
            array = Tool.getInstance().getToServer(data, url);

        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject obj = array.getJSONObject(i);
                    user = new User();
                    user.setId(Integer.parseInt(obj.getString("u_id")));
                    user.setEmail(obj.getString("u_email"));
                    user.setIntro(obj.getString("u_intro"));
                    user.setLevel(Integer.parseInt(obj.getString("u_level")));
                    user.setName(obj.getString("u_name"));
                    user.setP_id(Integer.parseInt(obj.getString("p_id")));
                    user.setD_id(Integer.parseInt(obj.getString("d_id")));
                    user.setPhone(obj.getString("u_phone"));
                    user.setAlarm(obj.getString("u_alarm").charAt(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Constants.user = user;
        }
    }

    //Data to String
    public String dateToString(Date date) {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return transFormat.format(date);
    }

    //String to Date
    public Date stringToDate(String date) {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return transFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //YN 토글 함수
    public char toggleYN(char yn) {
        return yn == 'Y' ? 'N' : 'Y';
    }

    //Activity 이동 함수
    public void goActivity(Activity from, Class<?> cls) {
        Intent intent = new Intent(from, cls);
        from.startActivity(intent);
    }
}
