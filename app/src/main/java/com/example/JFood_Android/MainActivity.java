package com.example.JFood_Android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Seller> listSeller = new ArrayList<>();
    private ArrayList<Food> foodIdList = new ArrayList<>();
    private HashMap<Seller, ArrayList<Food>> childMapping = new HashMap<>();

    private static int currentUserId;
    private static String currentUserName;

    ArrayList<String> listDataHeader= new ArrayList<>();
    HashMap<String, ArrayList<String>> listDataChild = new HashMap<>();
    ExpandableListView expListView;
    MainListAdapter mainListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            currentUserId = extras.getInt("currentUserId");
            currentUserName = extras.getString("currentUserName");
        }

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        refreshList();

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

    }

    protected void refreshList() {
        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject food = response.getJSONObject(i);
                        JSONObject seller = food.getJSONObject("seller");
                        JSONObject location = seller.getJSONObject("location");

                        String province = location.getString("province");
                        String description = location.getString("description");
                        String city = location.getString("city");
                        Location location1 = new Location(province,description,city);

                        int sellerId = seller.getInt("id");
                        String sellerName = seller.getString("name");
                        String sellerEmail = seller.getString("email");
                        String sellerPhoneNumber = seller.getString("phoneNumber");
                        Seller seller1 = new Seller(sellerId, sellerName, sellerEmail, sellerPhoneNumber, location1);
                        if(listSeller.size()>0){
                            boolean success = true;
                            for (Seller object : listSeller){
                                if ((object.getId() == (seller1.getId()))) {
                                    success = false;
                                    break;
                                }
                            }
                            if(success){
                                listSeller.add(seller1);
                            }
                        }
                        else{
                            listSeller.add(seller1);
                        }

                        int foodId = food.getInt("id");
                        String foodName = food.getString("name");
                        int foodPrice = food.getInt("price");
                        String foodCategory = food.getString("category");
                        Food food1 = new Food(foodId, foodName, foodPrice, foodCategory, seller1);
                        if(foodIdList.size()>0){
                            boolean success = true;
                            for (Food object : foodIdList){
                                if ((object.getId() == (food1.getId()))) {
                                    success = false;
                                    break;
                                }
                            }
                            if(success){
                                foodIdList.add(food1);
                            }
                        }
                        else{
                            foodIdList.add(food1);
                        }

                    }
                    for(Seller objSeller:listSeller){
                        ArrayList<Food> tmp = new ArrayList<>();
                        for(Food objFood:foodIdList){
                            if(objFood.getSeller().getId() == objSeller.getId()){
                                tmp.add(objFood);
                            }
                        }
                        childMapping.put(objSeller,tmp);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(Seller s : listSeller){
                    listDataHeader.add(s.getName());
                    Log.d("foods", String.valueOf(s.getName()));
                    ArrayList<Food> tmpFood = childMapping.get(s);
                    ArrayList<String> foodFood = new ArrayList<>();

                    for(Food i : tmpFood){
                        foodFood.add(i.getName());
                        Log.d("foods", String.valueOf(i.getName()));
                    }

                    listDataChild.put(s.getName(),foodFood);
                    Log.d("foods", String.valueOf(s.getName())+":"+String.valueOf(foodFood));
                }
                Log.d("sellertest", "onResponse: "+listDataHeader);
                Log.d("sellertest", "onResponse: "+listDataChild);
                mainListAdapter = new MainListAdapter(MainActivity.this,listDataHeader,listDataChild);
                expListView.setAdapter(mainListAdapter);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Error occurred", error);
            }
        };

        MenuRequest menuRequest = new MenuRequest(responseListener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);

    }


}
