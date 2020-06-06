package com.example.JFood_Android;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

public class CheckPromoRequest extends JsonObjectRequest {
    private static final String PROMO_URL = "http://10.0.2.2:8080/promo/code/";

    public CheckPromoRequest(String promoCode, Response.Listener<JSONObject> listener,
                             Response.ErrorListener errorListener)
    {
        super(Method.GET, PROMO_URL + promoCode, null, listener, errorListener);
    }
}
