package com.example.JFood_Android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.JFood_Android.R;
import com.example.JFood_Android.Request.BatalPesananRequest;
import com.example.JFood_Android.Request.LiatPesananRequest;
import com.example.JFood_Android.Request.SelesaiPesananRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is to check the status of the order.
 *
 * @author Muhammad Sulton Tauhid
 * @version June 7th, 2020
 */
public class StatusPesananActivity extends AppCompatActivity {

    private int currentUserId;
    private String currentUserName;

    private TextView static_invoice_customer_id;
    private TextView selesai_invoice_customer_id;
    private TextView static_invoice_customer_name;
    private TextView selesai_invoice_customer_name;
    private TextView static_invoice_id;
    private TextView selesai_invoice_id;
    private TextView static_invoice_date;
    private TextView selesai_invoice_date;
    private TextView static_invoice_payment;
    private TextView selesai_invoice_payment;
    private TextView static_invoice_status;
    private TextView selesai_invoice_status;
    private TextView static_invoice_food_name;
    private TextView selesai_invoice_food_name;
    private TextView static_invoice_food_category;
    private TextView selesai_invoice_food_category;
    private TextView static_invoice_food_price;
    private TextView selesai_invoice_food_price;
    private TextView static_invoice_identifier;
    private TextView selesai_invoice_identifier ;
    private TextView static_invoice_identifier_opt1;
    private TextView selesai_invoice_identifier_opt1;
    private TextView static_invoice_identifier_opt2;
    private TextView selesai_invoice_identifier_opt2;
    private TextView static_invoice_identifier_opt3 ;
    private TextView selesai_invoice_identifier_opt3;
    private TextView static_invoice_identifier_opt4;
    private TextView selesai_invoice_identifier_opt4;
    private Button button_cancel;
    private Button button_finish;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selesai_pesanan_activity);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            currentUserId = extras.getInt("currentUserId");
            currentUserName = extras.getString("currentUserName");
        }

        // Instantiate all components to the activity.
        static_invoice_customer_id = findViewById(R.id.static_invoice_customer_id);
        selesai_invoice_customer_id = findViewById(R.id.selesai_invoice_customer_id);
        static_invoice_customer_name = findViewById(R.id.static_invoice_customer_name);
        selesai_invoice_customer_name = findViewById(R.id.selesai_invoice_customer_name);
        static_invoice_id = findViewById(R.id.static_invoice_id);
        selesai_invoice_id = findViewById(R.id.selesai_invoice_id);
        static_invoice_date = findViewById(R.id.static_invoice_date);
        selesai_invoice_date = findViewById(R.id.selesai_invoice_date);
        static_invoice_payment = findViewById(R.id.static_invoice_payment);
        selesai_invoice_payment = findViewById(R.id.selesai_invoice_payment);
        static_invoice_status = findViewById(R.id.static_invoice_status);
        selesai_invoice_status = findViewById(R.id.selesai_invoice_status);
        static_invoice_food_name = findViewById(R.id.static_invoice_food_name);
        selesai_invoice_food_name = findViewById(R.id.selesai_invoice_food_name);
        static_invoice_food_category = findViewById(R.id.static_invoice_food_category);
        selesai_invoice_food_category = findViewById(R.id.selesai_invoice_food_category);
        static_invoice_food_price = findViewById(R.id.static_invoice_food_price);
        selesai_invoice_food_price = findViewById(R.id.selesai_invoice_food_price);
        static_invoice_identifier = findViewById(R.id.static_invoice_identifier);
        selesai_invoice_identifier = findViewById(R.id.selesai_invoice_identifier);
        static_invoice_identifier_opt1 = findViewById(R.id.static_invoice_identifier_option1);
        selesai_invoice_identifier_opt1 = findViewById(R.id.selesai_invoice_identifier_option1);
        static_invoice_identifier_opt2 = findViewById(R.id.static_invoice_identifier_option2);
        selesai_invoice_identifier_opt2 = findViewById(R.id.selesai_invoice_identifier_option2);
        static_invoice_identifier_opt3 = findViewById(R.id.static_invoice_identifier_option3);
        selesai_invoice_identifier_opt3 = findViewById(R.id.selesai_invoice_identifier_option3);
        static_invoice_identifier_opt4 = findViewById(R.id.static_invoice_identifier_option4);
        selesai_invoice_identifier_opt4 = findViewById(R.id.selesai_invoice_identifier_option4);
        button_cancel = findViewById(R.id.btnCancel);
        button_finish = findViewById(R.id.btnFinish);

        static_invoice_customer_id.setVisibility(View.INVISIBLE);
        selesai_invoice_customer_id.setVisibility(View.INVISIBLE);
        static_invoice_customer_name.setVisibility(View.INVISIBLE);
        selesai_invoice_customer_name.setVisibility(View.INVISIBLE);
        static_invoice_id.setVisibility(View.INVISIBLE);
        selesai_invoice_id.setVisibility(View.INVISIBLE);
        static_invoice_date.setVisibility(View.INVISIBLE);
        selesai_invoice_date.setVisibility(View.INVISIBLE);
        static_invoice_payment.setVisibility(View.INVISIBLE);
        selesai_invoice_payment.setVisibility(View.INVISIBLE);
        static_invoice_status.setVisibility(View.INVISIBLE);
        selesai_invoice_status.setVisibility(View.INVISIBLE);
        static_invoice_food_name.setVisibility(View.INVISIBLE);
        selesai_invoice_food_name.setVisibility(View.INVISIBLE);
        static_invoice_food_category.setVisibility(View.INVISIBLE);
        selesai_invoice_food_category.setVisibility(View.INVISIBLE);
        static_invoice_food_price.setVisibility(View.INVISIBLE);
        selesai_invoice_food_price.setVisibility(View.INVISIBLE);
        static_invoice_identifier.setVisibility(View.INVISIBLE);
        selesai_invoice_identifier.setVisibility(View.INVISIBLE);
        static_invoice_identifier_opt1.setVisibility(View.INVISIBLE);
        selesai_invoice_identifier_opt1.setVisibility(View.INVISIBLE);
        static_invoice_identifier_opt2.setVisibility(View.INVISIBLE);
        selesai_invoice_identifier_opt2.setVisibility(View.INVISIBLE);
        static_invoice_identifier_opt3.setVisibility(View.INVISIBLE);
        selesai_invoice_identifier_opt3.setVisibility(View.INVISIBLE);
        static_invoice_identifier_opt4.setVisibility(View.INVISIBLE);
        selesai_invoice_identifier_opt4.setVisibility(View.INVISIBLE);
        button_cancel.setVisibility(View.INVISIBLE);
        button_finish.setVisibility(View.INVISIBLE);

        fetchPesanan();

        final RequestQueue queue = Volley.newRequestQueue(StatusPesananActivity.this);

        // Cancel button function
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> buttonListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(StatusPesananActivity.this, MainActivity.class);
                        if (response.equals("true")) {
                            Toast.makeText(StatusPesananActivity.this, "Pesanan Berhasil dibatalkan!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(StatusPesananActivity.this, "Permintaan gagal!", Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        finish();
                    }
                };
                Response.ErrorListener buttonErrorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                };
                BatalPesananRequest batalPesananRequest = new BatalPesananRequest(currentUserId, buttonListener, buttonErrorListener);
                queue.add(batalPesananRequest);
            }
        });

        // Finish button function
        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> buttonListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(StatusPesananActivity.this, MainActivity.class);
                        if (response.equals("true")) {
                            Toast.makeText(StatusPesananActivity.this, "Pesanan Selesai!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(StatusPesananActivity.this, "Permintaan gagal!", Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent);
                        finish();
                    }
                };
                Response.ErrorListener buttonErrorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                };
                SelesaiPesananRequest selesaiPesananRequest = new SelesaiPesananRequest(currentUserId, buttonListener, buttonErrorListener);
                queue.add(selesaiPesananRequest);
            }
        });

    }

    private void fetchPesanan() {
        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() == 0) {
                    Toast.makeText(StatusPesananActivity.this, "Tidak ada pesanan aktif!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(StatusPesananActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    static_invoice_customer_id.setVisibility(View.VISIBLE);
                    selesai_invoice_customer_id.setVisibility(View.VISIBLE);
                    static_invoice_customer_name.setVisibility(View.VISIBLE);
                    selesai_invoice_customer_name.setVisibility(View.VISIBLE);
                    static_invoice_id.setVisibility(View.VISIBLE);
                    selesai_invoice_id.setVisibility(View.VISIBLE);
                    static_invoice_date.setVisibility(View.VISIBLE);
                    selesai_invoice_date.setVisibility(View.VISIBLE);
                    static_invoice_payment.setVisibility(View.VISIBLE);
                    selesai_invoice_payment.setVisibility(View.VISIBLE);
                    static_invoice_status.setVisibility(View.VISIBLE);
                    selesai_invoice_status.setVisibility(View.VISIBLE);
                    static_invoice_food_name.setVisibility(View.VISIBLE);
                    selesai_invoice_food_name.setVisibility(View.VISIBLE);
                    static_invoice_food_category.setVisibility(View.VISIBLE);
                    selesai_invoice_food_category.setVisibility(View.VISIBLE);
                    static_invoice_food_price.setVisibility(View.VISIBLE);
                    selesai_invoice_food_price.setVisibility(View.VISIBLE);
                    button_cancel.setVisibility(View.VISIBLE);
                    button_finish.setVisibility(View.VISIBLE);

                    selesai_invoice_customer_id.setText(String.valueOf(currentUserId));
                    selesai_invoice_customer_name.setText(currentUserName);

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject invoiceObject = response.getJSONObject(i);
                            JSONArray foodsArray = invoiceObject.getJSONArray("foods");
                            selesai_invoice_id.setText(String.valueOf(invoiceObject.getInt("id")));
                            selesai_invoice_date.setText(invoiceObject.getString("date"));
                            selesai_invoice_payment.setText(invoiceObject.getString("paymentType"));
                            selesai_invoice_status.setText(invoiceObject.getString("invoiceStatus"));

                            for (int j = 0; j < foodsArray.length(); j++) {
                                JSONObject foodObject = foodsArray.getJSONObject(j);
                                selesai_invoice_food_name.setText(foodObject.getString("name"));
                                selesai_invoice_food_category.setText(foodObject.getString("category"));
                                selesai_invoice_food_price.setText(String.valueOf(foodObject.getInt("price")));
                            }

                            if (selesai_invoice_payment.getText().equals("Cash")) {
                                static_invoice_identifier.setVisibility(View.VISIBLE);
                                selesai_invoice_identifier.setVisibility(View.VISIBLE);
                                static_invoice_identifier_opt1.setVisibility(View.VISIBLE);
                                selesai_invoice_identifier_opt1.setVisibility(View.VISIBLE);

                                static_invoice_identifier.setText("Delivery Fee");
                                selesai_invoice_identifier.setText(String.valueOf(invoiceObject.getInt("deliveryFee")));
                                static_invoice_identifier_opt1.setText("Total Price");
                                selesai_invoice_identifier_opt1.setText(String.valueOf(invoiceObject.getInt("totalPrice")));
                            } else {
                                JSONObject promo = invoiceObject.getJSONObject("promo");
                                static_invoice_identifier.setVisibility(View.VISIBLE);
                                selesai_invoice_identifier.setVisibility(View.VISIBLE);
                                static_invoice_identifier_opt1.setVisibility(View.VISIBLE);
                                selesai_invoice_identifier_opt1.setVisibility(View.VISIBLE);
                                static_invoice_identifier_opt2.setVisibility(View.VISIBLE);
                                selesai_invoice_identifier_opt2.setVisibility(View.VISIBLE);
                                static_invoice_identifier_opt3.setVisibility(View.VISIBLE);
                                selesai_invoice_identifier_opt3.setVisibility(View.VISIBLE);
                                static_invoice_identifier_opt4.setVisibility(View.VISIBLE);
                                selesai_invoice_identifier_opt4.setVisibility(View.VISIBLE);

                                static_invoice_identifier.setText("Promo Code");
                                selesai_invoice_identifier.setText(promo.getString("code"));
                                static_invoice_identifier_opt1.setText("Discount");
                                selesai_invoice_identifier_opt1.setText(String.valueOf(promo.getInt("discount")));
                                static_invoice_identifier_opt2.setText("Min Price");
                                selesai_invoice_identifier_opt2.setText(String.valueOf(promo.getInt("minPrice")));
                                static_invoice_identifier_opt3.setText("Promo Active");
                                selesai_invoice_identifier_opt3.setText(String.valueOf(promo.getBoolean("active")));
                                static_invoice_identifier_opt4.setText("Total Price");
                                selesai_invoice_identifier_opt4.setText(String.valueOf(invoiceObject.getInt("totalPrice")));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Error occurred", error);
            }
        };

        LiatPesananRequest request = new LiatPesananRequest(currentUserId, listener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(StatusPesananActivity.this);
        queue.add(request);
    }
}
