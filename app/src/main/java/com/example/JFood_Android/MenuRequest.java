package com.example.JFood_Android;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;


public class MenuRequest extends JsonArrayRequest {
    private static final String URL = "http://10.0.2.2:8080/food/listFood";

    public MenuRequest(Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(Method.GET, URL, null, listener, errorListener);
    }

}
