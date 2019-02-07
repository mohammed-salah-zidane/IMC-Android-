package com.lorasoftware.mohamed_salah_zidane.imchelperdates1;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.HelperClasses.URLs;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models.DoctorListItem;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models.ExpertsListItem;

import org.json.JSONObject;

import java.net.URLEncoder;


public class ReserveExActivity extends AppCompatActivity {

    private DoctorListItem doctorListItem;

    EditText pName , pPhone,pPlain;
    String name , mobile,plain;
    private RequestQueue requestQueue ;
    ExpertsListItem expert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_ex);

        pName = (EditText)findViewById(R.id.nameEditTextx);
        pPhone = (EditText)findViewById(R.id.phoneNumEditTextx);
        pPlain = (EditText)findViewById(R.id.patiantPlainx);

        final Button send = (Button)findViewById(R.id.sendButtonx);
        Button cancel = (Button)findViewById(R.id.cancel_Buttonx);
        expert = (ExpertsListItem) getIntent().getSerializableExtra("expert");



          getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

       /* Date currentTime = Calendar.getInstance().getTime();
        Log.d("currentTime",currentTime.toString());*/





        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (! pName.getText().toString().isEmpty() && ! pPhone.getText().toString().isEmpty()&& ! pPlain.getText().toString().isEmpty()){

                    //Toast.makeText(Reservation.this,pName.getText().toString().trim(),Toast.LENGTH_LONG).show();

                    //  send.setBackground(R.drawable.buttonshape_afterpress);
                    jsonrequest();

                    pName.setText("");
                    pPhone.setText("");
                    pPlain.setText("");

                    //Toast.makeText(Reservation.this,"شكرا, تم الحجز سوف نقوم بالتواصل معك وابلاغك بالمعياد المتاح",Toast.LENGTH_LONG).show();
                }else {

                    Toast.makeText(ReserveExActivity.this,"الرجاء التأكد من ادخال الاسم ورقم التليفون",Toast.LENGTH_LONG).show();

                }
            }
        });



    }

    private void jsonrequest() {

        requestQueue = Volley.newRequestQueue(ReserveExActivity.this);

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("zidan.sobelnaza.com")
                .path("/api/reserve/")
                .appendQueryParameter("id", expert.getExpertId()+"")
                .appendQueryParameter("name", pName.getText().toString().trim())
                .appendQueryParameter("phone", pPhone.getText().toString().trim())
                .appendQueryParameter("plain", pPlain.getText().toString().trim())
                .build();



        String url = URLs.URL_RESRVE_EXPERT+"/"+expert.getExpertId()
                +"/"+ URLEncoder.encode(pName.getText().toString())
                +"/"+URLEncoder.encode(pPhone.getText().toString())
                +"/"+URLEncoder.encode(pPlain.getText().toString());

     /*   final SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();*/



        //final JSONObject jsonBody = new JSONObject("{\"type\":\"example\"}");
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,  url ,null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        shwAlert();
                        Log.d("Response", response.toString());
                        //finish();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );
        requestQueue.add(getRequest);

    }

    public  void shwAlert(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ReserveExActivity.this);
        builder1.setMessage("شكرا, تم الحجز سوف نقوم بالتواصل معك في أقرب وقت وابلاغك بالمعياد");
        builder1.setCancelable(true);

        builder1.setTitle("تم الحجز");
        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
}
