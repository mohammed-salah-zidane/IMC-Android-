package com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Adapters;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models.DoctorDates;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models.DoctorListItem;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.R;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Reservation;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.github.aakira.expandablelayout.Utils;

import java.io.Serializable;
import java.util.ArrayList;


public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder> {

    private ArrayList doctorListItems;
    private ArrayList doctorDatesListItems;

    private int doctorId;


    private Context context;
    private AlertDialog dialog;
    SparseBooleanArray expandState = new SparseBooleanArray();


    private int lastSelectedPosition = -1;
    public DoctorListAdapter( ArrayList doctorListItems,ArrayList doctorDatesListItems, Context context) {
        this.doctorListItems = doctorListItems;
        this.doctorDatesListItems = doctorDatesListItems;

        this.context = context;
        for (int i =0;i<doctorListItems.size();i++){
            expandState.append(i,false);

        }
    }

    @Override
    public int getItemViewType(int position) {
      return 1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.doctor_list_item, viewGroup, false);


                //*viewHolder.textViewName.setText(doctorListItems.get(j).getName());*//*


        return new ViewHolder(view);


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final DoctorListItem list =(DoctorListItem) doctorListItems.get(i);

        //     DoctorDates date_list= doctorDatesListItems.get(i);


        //  viewHolder.expandableLayout.setInRecyclerView(true);
        viewHolder.expandableLayout.collapse();
        viewHolder.expandableLayout.setExpanded(expandState.get(i));
        viewHolder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {

                changeRotate(viewHolder.button, 0f, 180f).start();
                expandState.put(i, true);
            }

            @Override
            public void onPreClose() {
                changeRotate(viewHolder.button, 180f, 0f).start();
                expandState.put(i, false);
            }

            @Override
            public void onOpened() {

            }

            @Override
            public void onClosed() {

            }
        });
        viewHolder.button.setRotation(expandState.get(i) ? 180f : 0f);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.expandableLayout.toggle();
            }
        });



      //  viewHolder.selectionState.setChecked(lastSelectedPosition == i);
        viewHolder.expandableLayout.expand();



        viewHolder.textViewName.setText(list.getName());


    //    Log.d("adaptetrposition",viewHolder.getAdapterPosition()+"");
     /*   for (int j =0;j<doctorListItems.size();j++) {

                *//*viewHolder.textViewName.setText(doctorListItems.get(j).getName());*//*
            Log.d("doctorName",doctorListItems.get(j).getName());

        }*/
/*
        Log.d("doctorName",doctorListItems.get(i). );*/

        for (int j =0;j<doctorDatesListItems.size();j++){
            DoctorDates date_list=(DoctorDates) doctorDatesListItems.get(j);
           // Log.d("dates",date_list.getDay()+date_list.getFrom_to() );

            if (date_list.getDoctorId()==list.getDoctorId()){
                TextView textView1 = new TextView(viewHolder.context);
                textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                textView1.setTextColor(Color.parseColor("#ffffff"));

                textView1.setGravity(Gravity.RIGHT);
                textView1.setPadding(10, 0, 10, 0);
                textView1.setText(date_list.getDay()+" "+date_list.getFrom_to());
                textView1.setBackgroundColor(R.color.colorPrimary); // hex color 0xAARRGGBB
                viewHolder.datesLinearLayout.addView(textView1);
              //  Log.d("dates",date_list.getDay()+date_list.getFrom_to() );

            }

        }

        viewHolder.reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, Reservation.class);

                myIntent.putExtra("doctor", (Serializable) doctorListItems.get(viewHolder.getAdapterPosition()));
                context.startActivity(myIntent);
            }
        });

     /*   viewHolder.selectionState.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                lastSelectedPosition = viewHolder.getAdapterPosition();
               // notifyDataSetChanged();
                viewHolder.expandableLayout.expand();
                doctorId = list.getDoctorId();


                // cardView.setCardBackgroundColor(R.color.colorPrimaryDark);
            }
        });
*/
      /*  viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });*/
        viewHolder.textViewDegree.setText(list.getDegree());
        viewHolder.textViewClinics.setText(list.getClinicName());
       // viewHolder.textViewdates.setText("السبت من ٩ ل ٢");

    }

    private ObjectAnimator changeRotate(RelativeLayout button, float from, float to) {

        ObjectAnimator animator =ObjectAnimator.ofFloat(button,"rotation",from,to);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));

        return animator;
    }


    @Override
    public int getItemCount() {
        return doctorListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public TextView textViewDegree;
        public TextView textViewClinics;
      //  public TextView textViewdates;


        private  Button reservationButton;

        public RelativeLayout button;
        public RelativeLayout Container;
        private LinearLayout datesLinearLayout;
        public ExpandableRelativeLayout expandableLayout;

        public RadioButton selectionState;

        public ImageView imageView;

        Context context;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            textViewName = (TextView) itemView.findViewById(R.id.name);
            textViewDegree = (TextView) itemView.findViewById(R.id.degree);
            textViewClinics = (TextView) itemView.findViewById(R.id.clincsNumber);
            //  textViewdates = (TextView) itemView.findViewById(R.id.dates);
            expandableLayout = (ExpandableRelativeLayout) itemView.findViewById(R.id.expandableLayout);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
           // selectionState = (RadioButton) itemView.findViewById(R.id.radioButton5);


             reservationButton = (Button) itemView.findViewById(R.id.reserveButton);

            datesLinearLayout = (LinearLayout)itemView.findViewById(R.id.datesLinearLayout);
            button = (RelativeLayout) itemView.findViewById(R.id.button);
           // Container = (RelativeLayout) itemView.findViewById(R.id.);
            imageView = (ImageView)itemView.findViewById(R.id.list_item_image);




        }
    }

    }




