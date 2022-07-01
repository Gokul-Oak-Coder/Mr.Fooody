package com.example.mrfooody;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.Listing_holder>{

    List<FoodListing> data;
    Context context;
    int selectedItem = 0;

    OnListingClick onListingClick;

public interface OnListingClick{
    void onClick(int pos);
}
    public FoodListAdapter(List<FoodListing> data, Context context, OnListingClick onListingClick) {
        this.data = data;
        this.context = context;
        this.onListingClick = onListingClick;
    }


    @NonNull
    @Override
    public Listing_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.listing_holder,parent, false);
        return new Listing_holder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(@NonNull Listing_holder holder, int position) {
         holder.image.setImageResource(data.get(position).getImage());
         holder.title.setText(data.get(position).getName());


         if(position == selectedItem){
             // Make card selected
             holder.cardView.setOutlineSpotShadowColor(context.getColor(R.color.rose));
             holder.cardView.setOutlineAmbientShadowColor(context.getColor(R.color.rose));
             holder.cardView.setStrokeWidth(2);
             holder.title.setTextColor(context.getColor(R.color.rose));
             holder.image.setColorFilter(R.color.rose);
             holder.image.setColorFilter(ContextCompat.getColor(context,R.color.rose), PorterDuff.Mode.SRC_IN);

         }else{
             // Make card inactive
             holder.cardView.setOutlineSpotShadowColor(context.getColor(R.color.gray1));
             holder.cardView.setOutlineAmbientShadowColor(context.getColor(R.color.gray1));
             holder.title.setTextColor(context.getColor(R.color.gray1));
             holder.image.setColorFilter(ContextCompat.getColor(context,R.color.gray1), PorterDuff.Mode.SRC_IN);
             holder.cardView.setStrokeWidth(0);
         }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Listing_holder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;
        MaterialCardView cardView;
        public Listing_holder(View holder){
            super(holder);
            title = holder.findViewById(R.id.chick);
            image = holder.findViewById(R.id.chick_img);
            cardView = holder.findViewById(R.id.card_vew);

            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    selectedItem = getAdapterPosition();

                    //reset items, so that color changes where we click on card

                    if(onListingClick != null){

                        onListingClick.onClick(getAdapterPosition());
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}
