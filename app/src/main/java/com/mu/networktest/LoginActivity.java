package com.mu.networktest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private EditText inputId;
    private EditText inputPassword;
    private Button loginButton;
    private Button signUpButton;
    private TextView passwordVisibleView;

    private String id;
    private String passWord;
    private boolean isShowPass = false;

    private static final String USER_URL = "http://192.168.6.18:9101/user/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputId = (EditText) findViewById(R.id.edit_id);
        inputPassword = (EditText) findViewById(R.id.edit_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        id = inputId.getText().toString();
        passWord = inputPassword.getText().toString();

    }

    public void loginButtonClicked(View view) {

        String id = inputId.getText().toString();
        String password = inputPassword.getText().toString();
//        new LoginTask().execute(id, password);
        new DetailUserRequest().execute();

    }


    class DetailUserRequest extends AsyncTask<String, Integer, UserItemData> {

        @Override
        protected UserItemData doInBackground(String... strings) {

            try {
                URL url = new URL(USER_URL);
                Log.d("test", USER_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept", "application/json");

                int code = conn.getResponseCode();
                Log.d("test", "응답코드 : " + code);
                if (code >= 200 && code < 300) {
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    Gson gson = new Gson();
                    UserItemData result = gson.fromJson(br, UserItemData.class);
                    Log.d("test", "성공 메시지 : " + result.getMessage());

                    return result;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(UserItemData str) {
            super.onPostExecute(str);
            if (str != null) {
                Log.d("test", str.getData().getUserNick());
            } else {
                Log.d("test", "error");
            }

        }

    }

    class LoginTask extends AsyncTask<String, Integer, UserItemData> {

        @Override
        protected UserItemData doInBackground(String... strings) {

            try {

                String message = "userId=" + strings[0]+"" + "&userPass=" +strings[1]+"";
                URL url = new URL(USER_URL);
                Log.d("test", USER_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                OutputStream os = conn.getOutputStream();
                os.write(message.getBytes());
                os.flush();
                os.close();

                int code = conn.getResponseCode();
                Log.d("test", "응답코드 : " + code);
                if (code >= 200 && code < 300) {
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    Gson gson = new Gson();
                    UserItemData result = gson.fromJson(br, UserItemData.class);
                    Log.d("test", "성공 메시지 : " + result.getMessage());

                    return result;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(UserItemData str) {
            super.onPostExecute(str);
            if (str != null) {
                Log.d("test", str.getData().getUserNick());
            } else {
                Log.d("test", "error");
            }

        }

    }
}
