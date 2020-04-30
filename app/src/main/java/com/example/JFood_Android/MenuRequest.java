package com.example.JFood_Android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MenuRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8080/customer/food/listFood";
    private Map<String, String> params;

    public MenuRequest(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.GET, URL, listener, errorListener);
        params = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}