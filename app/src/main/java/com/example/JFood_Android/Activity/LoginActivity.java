package com.example.JFood_Android.Activity;

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
import com.example.JFood_Android.R;
import com.example.JFood_Android.Request.LoginRequest;
import com.example.JFood_Android.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is the front page of the application. It needs user to regist and login.
 *
 * @author Muhammad Sulton Tauhid
 * @version June 7th, 2020
 */
public class LoginActivity extends AppCompatActivity {

    // Instances variable
    private EditText login_email;
    private EditText login_password;
    private Button btnLogin;
    private TextView btnRegister;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // Instantiate the component to the activity
        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);
        btnLogin = (Button) findViewById(R.id.login_button);
        btnRegister = (TextView) findViewById(R.id.login_register_button);

        sharedPrefManager = new SharedPrefManager(this);



        // Login button function
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginEmailListen = login_email.getText().toString();
                String loginPasswordListen = login_password.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            if (jsonObject != null) {
                                intent.putExtra("currentUserId", jsonObject.getInt("id"));
                                intent.putExtra("currentUserName", jsonObject.getString("name"));
                                //sharedPrefManager.saveSPInt(SharedPrefManager.ID_KEY, jsonObject.getInt("id"));
                                //sharedPrefManager.saveSPString(SharedPrefManager.NAME_KEY, jsonObject.getString("name"));
                                //sharedPrefManager.saveSPString(SharedPrefManager.EMAIL_KEY, jsonObject.getString("email"));
                                //sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();
                            }
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

                /*
                if (sharedPrefManager.getSPSudahLogin()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //intent.putExtra("currentUserId", sharedPrefManager.getSPId());
                    //intent.putExtra("currentUserName", sharedPrefManager.getSPNama());
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
                 */

                LoginRequest loginRequest = new LoginRequest(loginEmailListen, loginPasswordListen, responseListener, errorListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);


            }
        });

        // Register button function
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
