package com.example.JFood_Android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class BuatPesananActivity extends AppCompatActivity {
    private int currentUserId;
    private String currentUserName;
    private int id_food;
    private String foodName;
    private String foodCategory;
    private int foodPrice;
    private String promoCode;
    private String selectedPayment;
    private Promo promo;
    private boolean pesananActive;
    private TextView food_name;
    private TextView food_category ;
    private TextView food_price ;
    private TextView total_price ;
    private TextView promo_code ;
    private EditText promo_code_field ;
    private RadioGroup radioGroup ;
    private Button hitung ;
    private Button pesan ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buat_pesanan_activity);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUserId = extras.getInt("currentUserId");
            currentUserName = extras.getString("currentUserName");
            id_food = extras.getInt("food_id");
            foodName = extras.getString("food_name");
            foodCategory = extras.getString("food_category");
            foodPrice = extras.getInt("food_price");
        }

        food_name = (TextView) findViewById(R.id.foodTextview);
        food_category = (TextView) findViewById(R.id.categoryTextview);
        food_price = (TextView) findViewById(R.id.priceTextview);
        total_price = (TextView) findViewById(R.id.total_price);
        promo_code = (TextView) findViewById(R.id.promo_code);
        promo_code_field = (EditText) findViewById(R.id.promo_code_field);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        hitung = (Button) findViewById(R.id.hitung);
        pesan = (Button) findViewById(R.id.pesan);

        promo_code.setEnabled(false);
        promo_code_field.setEnabled(false);
        pesan.setEnabled(false);

        food_name.setText(foodName);
        food_category.setText(foodCategory);
        food_price.setText("Rp. " + String.valueOf(foodPrice));
        total_price.setText("Rp. 0");

        Log.d("Customer name: ", currentUserName);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                String selected = radioButton.getText().toString().trim();

                if (selected.equals("CASHLESS")) {
                    promo_code.setEnabled(true);
                    promo_code_field.setEnabled(true);
                } else {
                    promo_code.setEnabled(false);
                    promo_code_field.setEnabled(false);
                }
            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedRadioId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadio = findViewById(selectedRadioId);
                promoCode = promo_code_field.getText().toString();
                selectedPayment = selectedRadio.getText().toString().trim();

                if (selectedPayment.equals("CASH")) {
                    total_price.setText("Rp. " + String.valueOf(foodPrice));
                }

                else if (selectedPayment.equals("CASHLESS") && !promoCode.isEmpty()) {

                    Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                int id = response.getInt("id");
                                String code = response.getString("code");
                                int discount = response.getInt("discount");
                                int minPrice = response.getInt("minPrice");
                                boolean active = response.getBoolean("active");
                                promo = new Promo(id, code, discount, minPrice, active);

                                if (promo.isActive() && foodPrice > promo.getMinPrice()) {
                                    foodPrice = foodPrice - promo.getDiscount();
                                    total_price.setText("Rp: " + String.valueOf(foodPrice));
                                } else
                                    total_price.setText("Rp: " + String.valueOf(foodPrice));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Log.d("Error", "Error occurred", error);
                            Toast.makeText(BuatPesananActivity.this, "Promo tidak ditemukan", Toast.LENGTH_SHORT).show();
                        }
                    };
                    CheckPromoRequest checkPromoRequest = new CheckPromoRequest(promoCode, listener, errorListener);
                    RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                    queue.add(checkPromoRequest);


                }

                else
                    total_price.setText("Rp. " + String.valueOf(foodPrice));

                pesan.setEnabled(true);


            }
        });


        pesan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedRadioId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadio = findViewById(selectedRadioId);
                selectedPayment = selectedRadio.getText().toString().trim();

                Log.d("food_price", food_price.getText().toString().trim().substring(3));
                buatPesanan();

                /*
                boolean result = cekPesanan(currentUserId);
                if (!result)
                else
                    Toast.makeText(BuatPesananActivity.this, "Pesanan Gagal! Sedang ada pesanan aktif!", Toast.LENGTH_SHORT).show();
                 */

            }
        });

    }

    private boolean cekPesanan(int customerId) {
        pesananActive = false;
        Response.Listener<JSONArray> cekPesananListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() == 0)
                    return;
                else {
                    pesananActive = true;
                    /*
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject invoiceObject = response.getJSONObject(i);
                            JSONArray foodsArray = invoiceObject.getJSONArray("foods");
                            for (int j = 0; j < foodsArray.length(); j++) {
                                JSONObject foodObject = foodsArray.getJSONObject(j);
                                prevFoodId = foodObject.getInt("id");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                     */
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Error occurred", error);
            }
        };
        LiatPesananRequest liatPesananRequest = new LiatPesananRequest(customerId, cekPesananListener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
        queue.add(liatPesananRequest);
        return pesananActive;
    }

    private void buatPesanan() {
        BuatPesananRequest buatPesananRequest = null;
        Response.Listener<String> buatPesananListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject != null)
                        Toast.makeText(BuatPesananActivity.this, "Pesanan Berhasil !", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(BuatPesananActivity.this, "Pesanan Gagal! Sedang ada pesanan aktif!", Toast.LENGTH_SHORT).show();
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
        if (prevFoodId == 0) {
            if (selectedPayment.equals("CASH"))
                buatPesananRequest = new BuatPesananRequest(15000, id_food, currentUserId, buatPesananListener, errorListener);
            else
                buatPesananRequest = new BuatPesananRequest(promoCode, id_food, currentUserId, buatPesananListener, errorListener);
        } else {
            if (prevFoodId != id_food) {
                buatPesananRequest = new BuatPesananRequest(id_food, currentUserId, buatPesananListener, errorListener);
            } else {
                Toast.makeText(BuatPesananActivity.this, "Pesanan ini sedang dalam proses!", Toast.LENGTH_SHORT).show();
            }
        }
         */

        if (selectedPayment.equals("CASH"))
            buatPesananRequest = new BuatPesananRequest(15000, currentUserId, id_food, buatPesananListener, errorListener);
        else
            if (promoCode.isEmpty()) {
                Toast.makeText(BuatPesananActivity.this, "Code promo tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            } else if (Integer.parseInt(food_price.getText().toString().trim().substring(4)) < promo.getMinPrice()) {
                Toast.makeText(BuatPesananActivity.this, "Harga makanan tidak mencapai minimum harga promo!", Toast.LENGTH_SHORT).show();
                return;
            } else
                buatPesananRequest = new BuatPesananRequest(promoCode, currentUserId, id_food, buatPesananListener, errorListener);

        RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
        queue.add(buatPesananRequest);
        finish();

    }

}
