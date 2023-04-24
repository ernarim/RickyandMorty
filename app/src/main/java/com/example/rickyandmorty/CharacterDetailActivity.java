package com.example.rickyandmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.rickyandmorty.ViewModel.CharacterDetailActivityViewModel;
import com.example.rickyandmorty.databinding.ActivityCharacterDetailBinding;
import com.example.rickyandmorty.retrofit.Character;
import com.squareup.picasso.Picasso;

public class CharacterDetailActivity extends AppCompatActivity {
    private ActivityCharacterDetailBinding dataBinder;
    private CharacterDetailActivityViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinder = DataBindingUtil.setContentView(this, R.layout.activity_character_detail);
        viewModel = new ViewModelProvider(this).get(CharacterDetailActivityViewModel.class);


        Character character = (Character) getIntent().getSerializableExtra("characterObject");
        dataBinder.setCharacter(character);
        Picasso.get().load(character.getImage()).into(dataBinder.charImage);
        dataBinder.setEpisodes(viewModel.parseEpisodes(character.getEpisode()));
        dataBinder.setCreated(viewModel.parseCreated(character.getCreated()));



        dataBinder.charBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}