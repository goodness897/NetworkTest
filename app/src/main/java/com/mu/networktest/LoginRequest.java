package com.mu.networktest;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mu on 2016-11-09.
 */

public class LoginRequest extends NetworkRequest implements NetworkRequest.AsyncResponse {

    private static final String URL = "http://192.168.6.18:9101/login";
    private static final String USER_ID = "userId";
    private static final String USER_PASS = "userPass";

    public LoginRequest(String method, String userId, String userPass) {
        setMethod(method);
        map.put(USER_ID, userId);
        map.put(USER_PASS, userPass);
    }

    @Override
    public URL getURL() throws MalformedURLException {
        return new URL(URL);
    }

    @Override
    protected void setRequestProperty(HttpURLConnection conn) {
        super.setRequestProperty(conn);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    }

    @Override
    protected void write(OutputStream out) {
        super.write(out);
        try {
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            write.write(getPostDataString(map));
            write.flush();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void successResult(String output) {
        Gson gson = new Gson();
        NetworkResultTemp temp = gson.fromJson(output, NetworkResultTemp.class);
        if (temp.getCode() == 0) {
            UserItemData result = gson.fromJson(output, UserItemData.class);
            listener.onSuccess(this, result);
        }
    }

}
