package com.mu.networktest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        LoginRequest request = new LoginRequest("POST", id, password);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener() {
            @Override
            public void onSuccess(NetworkRequest request, Object result) {
                UserItemData userItemData = (UserItemData) result;
                Toast.makeText(LoginActivity.this, "성공 : " + userItemData.getData().getUserId(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(NetworkRequest request, int errorCode, String errorMessage) {
                Toast.makeText(LoginActivity.this, "실패 : " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
//        new LoginTask().execute(id, password);



    }

    public void testButtonClicked(View view) {
        String userNum = "1";
        UserDetailRequest request = new UserDetailRequest("GET", userNum);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener() {
            @Override
            public void onSuccess(NetworkRequest request, Object result) {
                UserItemData userItemData = (UserItemData) result;
                Toast.makeText(LoginActivity.this, "성공 : " + userItemData.getData().getUserNick(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(NetworkRequest request, int errorCode, String errorMessage) {
                Toast.makeText(LoginActivity.this, "실패 : " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}
