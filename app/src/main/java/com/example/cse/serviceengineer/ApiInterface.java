package com.example.cse.serviceengineer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    //login
    @GET("collections/Login/?apiKey=h3IQbkK_J7am5m45-Wd-FryEVIqrvOJI")
    Call<List<LoginDataBringer>> Login(@Query("q") String s);

    @GET("collections/manager/")
    Call<List<Home_Model>> getHomeData(@Query("apiKey") String apiKey);

}
