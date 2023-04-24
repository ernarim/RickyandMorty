package com.example.rickyandmorty.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickyandmorty.R;
import com.example.rickyandmorty.Interface.RVDataPass;
import com.example.rickyandmorty.retrofit.Location;

import java.util.List;

import okhttp3.HttpUrl;

public class HorizontalRVAdapter extends RecyclerView.Adapter<HorizontalRVAdapter.HorizontalRVCardViewHolder> {
    private Context context;
    private List<Location> locations;
    private String characterIds;
    private RVDataPass rvDataPass;
    private int singleItemSelection;


    public HorizontalRVAdapter(Context context, List<Location> locations, RVDataPass rvDataPass) {
        this.context = context;
        this.locations = locations;
        this.rvDataPass = rvDataPass;
        this.singleItemSelection = 0;
        rvDataPass.passData(findCharacterIds(locations.get(0).getResidents()));
    }



    public class HorizontalRVCardViewHolder extends RecyclerView.ViewHolder{
        public TextView locationName;
        public CardView cardView;

            public HorizontalRVCardViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view_horizontal);
            locationName = view.findViewById(R.id.locationName);



        }
    }

    private void setSingleSelection(int adapterPosition){
        if(adapterPosition == RecyclerView.NO_POSITION) return;
        notifyItemChanged(singleItemSelection);
        singleItemSelection = adapterPosition;
        notifyItemChanged(singleItemSelection);
    }

    @NonNull
    @Override
    public HorizontalRVCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_horizontal_list, parent, false);

        return new HorizontalRVCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRVCardViewHolder holder, int position) {
        holder.locationName.setText(locations.get(position).getName());

        if(singleItemSelection == position){
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }
        else{
            holder.cardView.setCardBackgroundColor(Color.parseColor("#BABABA"));
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setSingleSelection(holder.getAdapterPosition());

                List<String> characterUrls = locations.get(holder.getAdapterPosition()).getResidents();
                characterIds = findCharacterIds(characterUrls);

                Log.e("TAG_X", "characterIds: " + characterIds);
                rvDataPass.passData(characterIds);
            }
        });




    }

    @Override
    public int getItemCount() {
        if(locations != null){
            return locations.size();
        }
        else{
            return 0;
        }
    }

    public String parseUrl(String url){
        HttpUrl httpUrl = HttpUrl.parse(url);
        String id = httpUrl.pathSegments().get(2);
        return id;
    }

    public String findCharacterIds(List<String> characterUrls){
        String characterIds = "";
        for(String url : characterUrls){
            characterIds = characterIds + parseUrl(url) + ",";
        }
        return characterIds;
    }





}
