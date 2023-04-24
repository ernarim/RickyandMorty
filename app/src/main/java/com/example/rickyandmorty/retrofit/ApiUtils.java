package com.example.rickyandmorty.retrofit;

public class ApiUtils {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/";

    public static LocationsDAOInterface getLocationsDAOInterface(){
        return RetrofitClient.getClient(BASE_URL).create(LocationsDAOInterface.class);
    }
    public static CharactersDAOInterface getCharactersDAOInterface(){
        return RetrofitClient.getClient(BASE_URL).create(CharactersDAOInterface.class);
    }

}
