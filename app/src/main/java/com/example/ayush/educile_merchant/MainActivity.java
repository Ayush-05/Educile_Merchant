package com.example.ayush.educile_merchant;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.backendless.Backendless;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView tv1,tv2,tv3,tv4;

    List<Products> productList;
    RecyclerView recyclerView;
    ProgressBar pb;
    Myadapter adapter;

    String url2;
    Button bt;

    Intent i22;
    String url="https://api.backendless.com/875251C1-5C56-A158-FF59-E1585F146E00/502A4C9A-1E01-B54E-FFA0-BC87ADC18200/data/Mybookings?pageSize=100&sortBy=created%20desc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        pb=(ProgressBar)findViewById(R.id.progressBar2);
        tv3=(TextView)findViewById(R.id.tv3);



        productList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Backendless.setUrl(Defaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY);





        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            //Toast.makeText(MainActivity.this,"Working",Toast.LENGTH_LONG).show();
                            JSONArray array2 = new JSONArray(response);
                            pb.setVisibility(View.GONE);

                            //traversing through all the object
                            for (int i = 0; i < array2.length(); i++) {

                                //getting product object from json array
                                JSONObject product2 = array2.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Products(

                                        product2.getString("Date_time"),
                                        product2.getString("Name"),
                                        product2.getString("Phone"),
                                        product2.getString("Chapter"),
                                        product2.getString("objectId"),
                                        product2.getString("Payment"),
                                        product2.getString("Tutor"),
                                        product2.getString("Students"),
                                        product2.getString("Address"),
                                        product2.getString("Additional_Comments")





                                        ));

                            }
                             //creating adapter object and setting it to recyclerview
                            adapter = new Myadapter(MainActivity.this, productList);

                            //setting adapter to recyclervie
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Check your connection", Toast.LENGTH_LONG).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest2);





      /*  bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tutor = ed1.getText().toString();
                String phone = tv2.getText().toString();
                url2 = "http://api.msg91.com/api/sendhttp.php?sender=MSGIND&route=4&mobiles="+phone+"&authkey=181007AeDbQUPn859f2f2a7&country=91" +
                        "&message=Congrats,Your booking has been confirmed.";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(MainActivity.this,"Message sent",Toast.LENGTH_LONG).show();


                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();


                         }


                        });
                Volley.newRequestQueue(MainActivity.this).add(stringRequest);

            }
        });*/
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Tutors: i22=new Intent(MainActivity.this,MyTutors.class);
                startActivity(i22);
                break;



        }
        return super.onOptionsItemSelected(item);
    }
}
