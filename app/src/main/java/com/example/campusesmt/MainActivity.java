package com.example.campusesmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText txtLogin, txtPassword;
    private Button btnConnect, btnSign;
    private String login , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);
        btnConnect = findViewById(R.id.btnConnect);
        btnSign = findViewById(R.id.btnSign);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = txtLogin.getText().toString().trim();
                password = txtPassword.getText().toString().trim();
                if (login.isEmpty() || password.isEmpty()){
                    String message = getString(R.string.error_fields);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent  = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    //loginServer(login,password);
                }
            }
        });
    }
    public  void  loginServer(String  login,String password){
        String url ="";
        OkHttpClient client = new  OkHttpClient();//on creer les clients
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String message = getString(R.string.error_connection);
                        Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  try {
                      JSONObject jo = new JSONObject(response.body().string());
                      String status = jo.getString("status");
                      if (status.equals("OK")){
                          Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                          intent.putExtra("LOGIN",login);
                          startActivity(intent);
                      }else {
                          runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  String message = getString(R.string.error_parameters);
                                  Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();
                              }
                          });
                      }
                  }catch (Exception e){
                      e.printStackTrace();
                  }
            }
        });
    }
}