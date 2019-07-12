package com.example.ayush.educile_merchant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.DataQueryBuilder;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5,address1;
    EditText ed1,ed2;
    Button bt1,bt3;
    String gg,gg1,gg2,hh,id,ad;
    String url2,url3;
    Intent i22;
    HashMap hashMap;
    ProgressDialog progressDialog;
    String po;
    String urlnew="https://api.backendless.com/875251C1-5C56-A158-FF59-E1585F146E00/502A4C9A-1E01-B54E-FFA0-BC87ADC18200/data/educile_bookings/47617CFE-ABCC-1156-FF16-1114C5A20F00";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView1);
        tv4=(TextView)findViewById(R.id.textView3);
        tv5=(TextView)findViewById(R.id.textView5);
        address1=(TextView)findViewById(R.id.address1);

        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.editText);
        bt1=(Button)findViewById(R.id.bt1);
        bt3=(Button)findViewById(R.id.button3);
        Intent it=getIntent();
        if(it!=null)
        {
            hh=it.getStringExtra("Title");
            gg=it.getStringExtra("Cost");
            gg1=it.getStringExtra("Topic");
            gg2=it.getStringExtra("Time");
            id=it.getStringExtra("objectId");
            ad=it.getStringExtra("Address");
            tv1.setText(hh);
            tv2.setText(gg);
            tv3.setText(gg1);
            tv4.setText(gg2);
            tv5.setText(id);
            address1.setText(ad);
            }
       hashMap =new HashMap<>();


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tutor = ed1.getText().toString();
                String id1=tv5.getText().toString();
                progressDialog = new ProgressDialog(Main2Activity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Please wait");
                progressDialog.show();

                Map changes = new HashMap<>();
                changes.put("objectId",id1);
                changes.put( "Tutor", tutor );

                Backendless.Persistence.of("Mybookings").save(changes, new AsyncCallback<Map>() {
                    @Override
                    public void handleResponse(Map response) {
                        Toast.makeText(Main2Activity.this,"",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(Main2Activity.this,fault.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

                url2 = "http://api.msg91.com/api/sendhttp.php?sender=Educil&route=4&mobiles="+gg+"&authkey=181007AeDbQUPn859f2f2a7&country=91" +
                        "&message=Congrats,Your booking has been confirmed. "+tutor+" "+"Topic: "+gg1;


                StringRequest stringRequest = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        urlnew=urlnew+"?"+"sss="+po;
                        Toast.makeText(Main2Activity.this,"Message sent",Toast.LENGTH_LONG).show();

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(Main2Activity.this,"Error",Toast.LENGTH_LONG).show();



                            }


                        });
                Volley.newRequestQueue(Main2Activity.this).add(stringRequest);
                }
        });






        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ph=ed2.getText().toString();
                progressDialog = new ProgressDialog(Main2Activity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("PLease wait");
                progressDialog.show();

                url3 = "http://api.msg91.com/api/sendhttp.php?sender=Educil&route=4&mobiles="+ph+"&authkey=181007AeDbQUPn859f2f2a7&country=91" +
                        "&message=You have received a booking through EDUCILE..\n"+"Phone number: "+gg+"  "+
                        "Topic: "+gg1+"  "+"Name of Student: "+gg2+"  "+hh+"  "+ "Address of Student: "+ad;

                Log.e("rr",url3);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        Toast.makeText(Main2Activity.this,"Message sent",Toast.LENGTH_LONG).show();


                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(Main2Activity.this,"Error",Toast.LENGTH_LONG).show();


                            }


                        });
                Volley.newRequestQueue(Main2Activity.this).add(stringRequest);


            }
        });

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
            case R.id.Tutors: i22=new Intent(Main2Activity.this,MyTutors.class);
                startActivity(i22);
                break;



        }
        return super.onOptionsItemSelected(item);
    }
}
