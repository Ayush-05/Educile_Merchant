package com.example.ayush.educile_merchant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.backendless.Backendless;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyTutors extends AppCompatActivity {

    String url="https://api.backendless.com/875251C1-5C56-A158-FF59-E1585F146E00/502A4C9A-1E01-B54E-FFA0-BC87ADC18200/services/gettutors/Tutors";
    List<Products1> productList;
    RecyclerView recyclerView;
    Myadapter1 adapter;
    ProgressBar progressBar;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tutors);
        recyclerView = (RecyclerView) findViewById(R.id.rv2);
        recyclerView.setHasFixedSize(true);
        editText=(EditText)findViewById(R.id.editTextSearch);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        productList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Backendless.setUrl(Defaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //after the change calling the method and passing the search input
                filter(s.toString());
            }
        });



        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONArray array2 = new JSONArray(response);
                            progressBar.setVisibility(View.GONE);


                            //traversing through all the object
                            for (int i = 0; i < array2.length(); i++) {

                                //getting product object from json array
                                JSONObject product2 = array2.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Products1(

                                        product2.getString("name"),
                                        product2.getString("Phone"),
                                        product2.getString("Subject"),
                                        product2.getString("Chapters")


                                        ));

                            }

                            //creating adapter object and setting it to recyclerview
                            adapter = new Myadapter1(MyTutors.this, productList);

                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyTutors.this, "Check your connection", Toast.LENGTH_LONG).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest2);


    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<Products1> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (Products1 s : productList) {
            //if the existing elements contains the search input
            if (s.getSubject().toLowerCase().contains(text)) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdNames);
    }


}
