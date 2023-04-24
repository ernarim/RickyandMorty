package com.example.rickyandmorty.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationsDAOInterface {

    @GET("location")
    Call<LocationsAll> getLocations(@Query("page") int page);
}
