package com.example.rickyandmorty.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharactersDAOInterface {
    @GET("character/{id}")
    Call<List<Character>> getCharacters(@Path(value = "id", encoded = true) String id);
}
