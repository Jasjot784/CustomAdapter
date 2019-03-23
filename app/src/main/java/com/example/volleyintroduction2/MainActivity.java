package com.example.volleyintroduction2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleyintroduction2.Adapter.UserAdapter;
import com.example.volleyintroduction2.Models.Phone;
import com.example.volleyintroduction2.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String Url_JSonRequest = "https://api.androidhive.info/volley/person_object.json";
    String Url_JSonArrayRequest ="https://api.androidhive.info/volley/person_array.json";

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView lvUsers = findViewById(R.id.listOfUsers);

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url_JSonRequest, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//                Log.d(TAG, "onResponse: "+response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.e(TAG, "onErrorResponse: "+error );
//            }
//        }) ;
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonObjectRequest);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url_JSonArrayRequest,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (BuildConfig.DEBUG)Log.d(TAG, "onResponse: "+response);
                // Creating a list of users
                List<User> users1 = new ArrayList<>();
                //Manual Parsing begins from here

                for (int i = 0; i <= users1.size(); i++) {
                    //We have used try catch because data may or may not be present in backend
                    try {
                        JSONObject userJsonObject = response.getJSONObject(i);
                        User user = new User();//User class

                        Phone phone = new Phone();//Phone class
                        //Firstly getting data then setting data
                        String name = userJsonObject.getString("name");
                        String email = userJsonObject.getString("email");
                        JSONObject phoneJsonObject = userJsonObject.getJSONObject("phone");
                        String home = phoneJsonObject.getString("home");
                        String mobile = phoneJsonObject.getString("mobile");
                        phone.setHome(home);
                        phone.setMobile(mobile);
                        user.setName(name);
                        user.setEmail(email);
                        user.setPhone(phone);

                        users1.add(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Log.d(TAG, "onResponseUsersListSize: "+ users1.size());
                //Data is added to custom adapter
                // pass context and list of users to UserAdapter
                UserAdapter adapter = new UserAdapter(MainActivity.this, users1);
                lvUsers.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error );
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);
    }
}
