package com.example.rickyandmorty.ViewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickyandmorty.Adapters.HorizontalRVAdapter;
import com.example.rickyandmorty.Interface.RVDataPass;
import com.example.rickyandmorty.Adapters.VerticalRVAdapter;
import com.example.rickyandmorty.retrofit.ApiUtils;
import com.example.rickyandmorty.retrofit.Character;
import com.example.rickyandmorty.retrofit.CharactersDAOInterface;
import com.example.rickyandmorty.retrofit.Location;
import com.example.rickyandmorty.retrofit.LocationsAll;
import com.example.rickyandmorty.retrofit.LocationsDAOInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    private LocationsDAOInterface locationsDAOInterface = ApiUtils.getLocationsDAOInterface();
    private CharactersDAOInterface charactersDAOInterface = ApiUtils.getCharactersDAOInterface();


    private List<Location> locations = new ArrayList<>();
    private List<Character> characters;

    public void allLocations(RecyclerView horizontalRV, Context context, RVDataPass rvDataPass, int page) {
        locationsDAOInterface.getLocations(page).enqueue(new Callback<LocationsAll>() {
            @Override
            public void onResponse(Call<LocationsAll> call, Response<LocationsAll> response) {
                if (response.isSuccessful()) {
                    List<Location> results = response.body().getResults();
                    locations.addAll(results);
                    HorizontalRVAdapter horizontalRVAdapter = new HorizontalRVAdapter(context, locations, rvDataPass);
                    horizontalRV.setAdapter(horizontalRVAdapter);
                    for (Location result : results) {
                        Log.e("TAG_X", "Location: " + result.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<LocationsAll> call, Throwable t) {
                locations = null;
            }
        });

    }

    public void getCharacters(RecyclerView verticalRV, String characterIds, Context context) {
        charactersDAOInterface.getCharacters(characterIds).enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                if(response.isSuccessful()){
                    List<Character> results = response.body();
                    characters = results;
                    VerticalRVAdapter verticalRVAdapter = new VerticalRVAdapter(context,characters);
                    verticalRV.setAdapter(verticalRVAdapter);
                    for (Character character : characters){
                        Log.e("TAG_X", "character: " + character.getName());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                characters = null;
                VerticalRVAdapter verticalRVAdapter = new VerticalRVAdapter(context,characters);
                verticalRV.setAdapter(verticalRVAdapter);
                Log.e("TAG_X", "character: " + "Failed");
            }
        });
    }




}
