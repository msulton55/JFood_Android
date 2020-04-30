package com.example.JFood_Android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public EditText email;
    public EditText password;
    public Button btnLogin;
    public TextView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);
        btnRegister = (TextView) findViewById(R.id.register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailListen = email.getText().toString();
                String passwordListen = password.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            if (jsonObject != null)
                                intent.putExtra("currentUserId", jsonObject.getInt("id"));
                                intent.putExtra("currentUserName", jsonObject.getString("name"));
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                        } catch (JSONException e) {
                            //throw new RuntimeException(e);
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Error occurred", error);
                    }
                };
                LoginRequest loginRequest = new LoginRequest(emailListen, passwordListen, responseListener, errorListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
