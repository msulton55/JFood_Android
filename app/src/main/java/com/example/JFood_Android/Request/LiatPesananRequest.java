package com.example.JFood_Android.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class LiatPesananRequest extends JsonArrayRequest {
    private static final String INVOICE_URL = "http://10.0.2.2:8080/invoice/customer/";

    public LiatPesananRequest(int customerId, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(Method.GET, INVOICE_URL + String.valueOf(customerId), null, listener, errorListener);
    }
}
