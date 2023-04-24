package com.example.rickyandmorty.Adapters;

import com.example.rickyandmorty.CharacterDetailActivity;
import com.example.rickyandmorty.R;
import com.example.rickyandmorty.retrofit.Character;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VerticalRVAdapter extends RecyclerView.Adapter<VerticalRVAdapter.VerticalRVCardViewHolder> {
    private Context context;
    private List<Character> characters;

    public VerticalRVAdapter(Context context, List<Character> characters) {
        this.context = context;
        this.characters = characters;
    }

    public class VerticalRVCardViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView characterName;
        public ShapeableImageView characterImage;
        public ImageView characterGender;

        public VerticalRVCardViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view_vertical);
            characterImage = view.findViewById(R.id.characterImage);
            characterName = view.findViewById(R.id.charName);
            characterGender = view.findViewById(R.id.characterGender);
        }
    }

    @NonNull
    @Override
    public VerticalRVAdapter.VerticalRVCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_vertical_list, parent, false);

        return new VerticalRVCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRVAdapter.VerticalRVCardViewHolder holder, int position) {
        holder.characterName.setText(characters.get(position).getName());

        String imageUrl = characters.get(position).getImage();

        Picasso.get().load(imageUrl).into(holder.characterImage);


        if(characters.get(position).getGender().equals("Male")){
            holder.characterGender.setImageResource(R.drawable.ic_male);
        }
        else if(characters.get(position).getGender().equals("Female")){
            holder.characterGender.setImageResource(R.drawable.ic_female);
        }
        else{
            holder.characterGender.setImageResource(R.drawable.ic_question_mark);
        }

        holder.cardView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mainToCharDetail = new Intent(v.getContext(), CharacterDetailActivity.class);
                        mainToCharDetail.putExtra("characterObject", (Character) characters.get(holder.getAdapterPosition()));
                        v.getContext().startActivity(mainToCharDetail);
                    }
                });
    }

    @Override
    public int getItemCount() {
        if(characters == null)
            return 0;
        else{
            return characters.size();
        }
    }


}
