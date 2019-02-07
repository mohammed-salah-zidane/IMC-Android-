package com.lorasoftware.mohamed_salah_zidane.imchelperdates1;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Adapters.DoctorListAdapter;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.HelperClasses.URLs;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models.DoctorDates;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models.DoctorListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClinicDetailsActivity extends AppCompatActivity {

    View mView;


    private int clinicId;
    private int doctorId;

    private AlertDialog dialog;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList doctorListItems;
    private ArrayList doctorDatesListItems;
    private DoctorListItem _doctor;
    private DoctorDates _doctorDates;
    private  String clinicName ;
    private JsonObjectRequest requestDoctor ;
    private JsonArrayRequest requestTimes ;
    private RequestQueue requestQueue ;
    private CheckBox checkBox;

    private static ProgressDialog progressDialog;

    JSONArray doctorJsonArray = null;
    JSONArray timesJsonArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        Bundle bundle = new Bundle();
        clinicId = getIntent().getExtras().getInt("cid");
        clinicName = getIntent().getExtras().getString("cName");

        //Log.d("id",clinicId+"");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        doctorListItems = new ArrayList<>();
        doctorDatesListItems = new ArrayList<>();

        DoctorsClinicjsonrequest();

        adapter = new DoctorListAdapter(doctorListItems,doctorDatesListItems,this);
        recyclerView.setAdapter(adapter);

    }
    private void DoctorsClinicjsonrequest() {
        requestQueue = Volley.newRequestQueue(ClinicDetailsActivity.this);

        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {

            progressDialog.show();

           // showProgressDialog();
        requestDoctor =  new JsonObjectRequest(Request.Method.GET, URLs.URL_DOCTOR+clinicId, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                           progressDialog.dismiss();

                            doctorListItems.clear();
                            doctorDatesListItems.clear();
                            timesJsonArray = response.getJSONArray("times");
                            for(int i=0; i<timesJsonArray.length(); i++){

                                JSONObject jsonObject = (JSONObject) timesJsonArray.get(i);

                                DoctorDates dates = new DoctorDates(jsonObject.getInt("doctor_id"),jsonObject.getString("day_name"),
                                        jsonObject.getString("from_to"),jsonObject.getInt("id"));

                                doctorDatesListItems.add(dates);

                                adapter.notifyDataSetChanged();



                            }
                            doctorJsonArray = response.getJSONArray("doctors");
                            for(int i=0; i<doctorJsonArray.length(); i++){

                                JSONObject jsonObject = (JSONObject) doctorJsonArray.get(i);
                                DoctorListItem doctor = new DoctorListItem(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("scient_degree")

                                ,clinicName,false,false);
                                doctorListItems.add(doctor);

                               // Log.d("doctorName",_doctor.getName());

                                adapter.notifyDataSetChanged();

                          }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }                       }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("error", "Error: " + error.getMessage());
            }
        });
        requestQueue.add(requestDoctor);



        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }


    }
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading ..!");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    }



}
