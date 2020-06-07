package com.example.JFood_Android.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuatPesananRequest extends StringRequest {
    private Map<String, Object> params;
    private ArrayList<Integer> foodIdList;
    private static final String CASH_URL = "http://10.0.2.2:8080/invoice/createCashInvoice";
    private static final String CASHLESS_URL = "http://10.0.2.2:8080/invoice/createCashlessInvoice";
    private static final String ADD_FOOD_URL = "http://10.0.2.2:8080/invoice/addFood/";

    public BuatPesananRequest(int deliveryFee, int currentUserId, int food_id, Response.Listener<String> listenerCash, Response.ErrorListener errorListenerCash) {
        super(Method.POST, CASH_URL, listenerCash, errorListenerCash);
        foodIdList = new ArrayList<>();
        foodIdList.add(food_id);
        params = new HashMap<>();
        params.put("foodIdList", foodIdList);
        params.put("customerId", currentUserId);
        params.put("deliveryFee", deliveryFee);
    }


    public BuatPesananRequest(String code, int currentUserId, int food_id, Response.Listener<String> listenerCashless, Response.ErrorListener errorListenerCashless) {
        super(Method.POST, CASHLESS_URL, listenerCashless, errorListenerCashless);
        foodIdList = new ArrayList<>();
        foodIdList.add(food_id);
        params = new HashMap<>();
        params.put("foodIdList", foodIdList);
        params.put("customerId", currentUserId);
        params.put("promoCode", code);
    }

    public BuatPesananRequest(int food_id, int currentUserId, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.PUT, ADD_FOOD_URL + String.valueOf(currentUserId), listener, errorListener);
        foodIdList = new ArrayList<>();
        foodIdList.add(food_id);
        params = new HashMap<>();
        params.put("foodIdList", foodIdList);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> newParams = new HashMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() instanceof String)
                newParams.put(entry.getKey(), (String) entry.getValue());
            else if (entry.getValue() instanceof ArrayList)
                for (int i = 0; i < ((ArrayList) entry.getValue()).size(); i++)
                newParams.put(entry.getKey(), ((ArrayList) entry.getValue()).get(i).toString());
            else
                newParams.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return newParams;
    }



}
