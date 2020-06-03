package com.example.JFood_Android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SelesaiPesananRequest extends StringRequest {
    private Map<String, String> params;
    private static final String SELESAI_URL = "http://10.0.2.2:8080/invoice/changeStatus/";

    public SelesaiPesananRequest(int customerId, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.PUT, SELESAI_URL + String.valueOf(customerId), listener, errorListener);
        params = new HashMap<>();
        params.put("invoiceStatus", "FINISHED");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}


