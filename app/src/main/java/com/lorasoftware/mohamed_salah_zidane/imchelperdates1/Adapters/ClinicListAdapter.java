package com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.ClinicDetailsActivity;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models.ClinicListItem;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.R;

import java.util.List;

public class ClinicListAdapter extends RecyclerView.Adapter<ClinicListAdapter.ViewHolder> {

    private List<ClinicListItem> clinicListItems;
    private Context context;
    RequestOptions option;

    public ClinicListAdapter(List<ClinicListItem> clinicListItems, Context context) {
        this.clinicListItems = clinicListItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.clinic_list_item,viewGroup,false);

        final ViewHolder viewHolder = new ViewHolder(view) ;


        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(context, ClinicDetailsActivity.class);

                Bundle b = new Bundle();
           //     b.putInt("cid",clinicListItems.get(viewHolder.getAdapterPosition()).getClinic_id());
                myIntent.putExtra("cid",clinicListItems.get(viewHolder.getAdapterPosition()).getClinic_id());
                myIntent.putExtra("cName",clinicListItems.get(viewHolder.getAdapterPosition()).getName());
                context.startActivity(myIntent);

               // Toast.makeText(context,clinicListItems.get(viewHolder.getAdapterPosition()).getClinic_id()+"",Toast.LENGTH_LONG).show();
            }
        });

       /* for (int x=0;x<=clinicListItems.size();x++){
            Log.d("Name",list.getName());
        }*/
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        ClinicListItem list = clinicListItems.get(i);

        viewHolder.textViewName.setText(list.getName());


    }

    @Override
    public int getItemCount() {
        return clinicListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public LinearLayout linearLayout;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.title);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.container);
            imageView = (ImageView)itemView.findViewById(R.id.list_item_image);

        }
    }
}
