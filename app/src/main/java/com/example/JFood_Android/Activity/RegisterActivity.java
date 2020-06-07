package com.example.JFood_Android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.JFood_Android.R;
import com.example.JFood_Android.Request.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is the register page of the application.
 *
 * @author Muhammad Sulton Tauhid
 * @version June 7th, 2020
 */
public class RegisterActivity extends AppCompatActivity {

    // Instances variables
    private String registerName;
    private String registerEmail;
    private String registerPassword;
    private EditText register_name;
    private EditText register_email;
    private EditText register_password;
    private Button btnRegisterSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Instantiate the component to the activity.
        register_name = (EditText) findViewById(R.id.register_name);
        register_email = (EditText) findViewById(R.id.register_email);
        register_password = (EditText) findViewById(R.id.register_password);
        btnRegisterSubmit = (Button) findViewById(R.id.register_submit_button);

        btnRegisterSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerName = register_name.getText().toString();
                registerEmail = register_email.getText().toString();
                registerPassword = register_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null)
                                Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            Toast.makeText(RegisterActivity.this, "Register Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Error occurred", error);
                    }
                };

                RegisterRequest registerRequest = new
                        RegisterRequest (
                            registerName,
                            registerEmail,
                            registerPassword,
                            responseListener,
                            errorListener
                        );
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}




