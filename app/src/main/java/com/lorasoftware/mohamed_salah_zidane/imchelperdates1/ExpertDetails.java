package com.lorasoftware.mohamed_salah_zidane.imchelperdates1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models.ExpertsListItem;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpertDetails extends AppCompatActivity {

    private TextView nameTextView,nationalityTextView,dateTextView,generalSpectTextView;
    private EditText specialSpectEditText;
    private ExpertsListItem expert;
    private CardView cardView;
    private Button reserveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_details);



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        nameTextView = (TextView)findViewById(R.id.expert_name);
        reserveButton = (Button) findViewById(R.id.reserveExpertButton);
        cardView = (CardView) findViewById(R.id.expertCardView);
        nationalityTextView = (TextView)findViewById(R.id.expert_nationality);
        dateTextView = (TextView)findViewById(R.id.expert_date);
        generalSpectTextView = (TextView)findViewById(R.id.general_spect);
        specialSpectEditText = (EditText) findViewById(R.id.special_spect_editText);


        specialSpectEditText.setKeyListener( null );
        specialSpectEditText.setFocusable( false );
        specialSpectEditText.setCursorVisible(false);
        expert =(ExpertsListItem) getIntent().getSerializableExtra("expert");

      //  cardView.setBackgroundResource(R.drawable.expert_detail_card_background);
        String to="الي" ;
        String from="من" ;

        String tostr=expert.getTo() ;
        String fromstr=expert.getFrom() ;
        dateTextView.setText(from+"  "+fromstr+"  "+to+"  "+tostr);
        nameTextView.setText(expert.getTitle());
        nationalityTextView.setText(expert.getNationality());
        generalSpectTextView.setText(expert.getGeneralSpec());
        specialSpectEditText.setText(expert.getSpecialSpec());




        String valid_until = expert.getTo();


        String currentDateandTime = sdf.format(new Date());
        //   Log.d("currentTime",sdf.toString());

        //   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date strDate = null;
        try {
            strDate = sdf.parse(valid_until);
            if (new Date().after(strDate)) {
                Log.d("currentTime","outOfdate");
                reserveButton.setEnabled(false);
                reserveButton.setText("غير متاح حاليا");
                reserveButton.setAlpha(0);
                Toast.makeText(ExpertDetails.this,"عفوا غير متاح الحجز معه الان",Toast.LENGTH_LONG).show();
            }else{

                reserveButton.setEnabled(true);
                reserveButton.setText("احجز الان");
                reserveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(ExpertDetails.this, ReserveExActivity.class);

                        myIntent.putExtra("expert", (Serializable) expert);
                        ExpertDetails.this.startActivity(myIntent);
                    }
                });
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
       /* reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ExpertDetails.this, ReserveExActivity.class);

                myIntent.putExtra("expert", (Serializable) expert);
                ExpertDetails.this.startActivity(myIntent);
            }
        });*/
    }
}
