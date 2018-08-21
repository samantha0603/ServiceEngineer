package com.example.cse.serviceengineer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiInterfacePut {
    //register
    @POST("collections/engineer_login/?apiKey=h3IQbkK_J7am5m45-Wd-FryEVIqrvOJI")
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> saveDetails(@Body String body);

    @POST("collections/Login/?apiKey=h3IQbkK_J7am5m45-Wd-FryEVIqrvOJI")
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> Registration(@Body String str);
    @PUT("collections/Login/?apiKey=h3IQbkK_J7am5m45-Wd-FryEVIqrvOJI")
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> SaveUpdate(@Query("q") String str , @Body String body);

    //update
    @POST("collections/AssignedTo?apiKey=h3IQbkK_J7am5m45-Wd-FryEVIqrvOJI")
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> savePut(@Body String body);


}
