package com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.ExpertDetails;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models.ExpertsListItem;
import com.lorasoftware.mohamed_salah_zidane.imchelperdates1.R;

import java.util.List;


public class Experts_Adapter extends RecyclerView.Adapter<Experts_Adapter.ExpertsViewHolder> {
//
//
    private Context mctx;
    private List<ExpertsListItem> expertsList;
//
    public Experts_Adapter(Context mctx, List<ExpertsListItem> expertsList) {
        this.mctx = mctx;
        this.expertsList = expertsList;
    }
//
//
    @NonNull
    @Override
    public ExpertsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view =inflater.inflate(R.layout.expert_list_item,parent,false);
        final ExpertsViewHolder holder= new ExpertsViewHolder(view);

        holder.expertRelativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(mctx, ExpertDetails.class);

                myIntent.putExtra("expert", expertsList.get(holder.getAdapterPosition()));
               // Bundle b = new Bundle();
                //     b.putInt("cid",clinicListItems.get(viewHolder.getAdapterPosition()).getClinic_id());
              /*  myIntent.putExtra("cid",clinicListItems.get(viewHolder.getAdapterPosition()).getClinic_id());
                myIntent.putExtra("cName",clinicListItems.get(viewHolder.getAdapterPosition()).getName());*/
                mctx.startActivity(myIntent);

               // Toast.makeText(context,clinicListItems.get(viewHolder.getAdapterPosition()).getClinic_id()+"",Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }
//
//    @Override
    public void onBindViewHolder(@NonNull ExpertsViewHolder holder, int position) {

        ExpertsListItem experts = expertsList.get(position);


        holder.textViewTitle.setText(experts.getTitle());

        holder.textview.setText(experts.getNationality());
     /*   holder.imageView.setImageDrawable(mctx.getResources().getDrawable(experts.getImage()));*/


    }
//
    @Override
    public int getItemCount() {
        return expertsList.size();
    }
//
//
//
    class ExpertsViewHolder extends RecyclerView.ViewHolder {
//
        ImageView imageView;
        TextView textViewTitle,textview;
        RelativeLayout expertRelativeLayout;
//
        public ExpertsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item_image);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            textview = itemView.findViewById(R.id.textViewShortDesc);
            /*textViewDate = itemView.findViewById(R.id.textViewDate);
            speciality = itemView.findViewById(R.id.textViewSpecialization);*/
            expertRelativeLayout =(RelativeLayout) itemView.findViewById(R.id.expertContent);

            }
    }
//
}
