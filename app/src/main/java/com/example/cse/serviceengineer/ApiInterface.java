package com.example.cse.serviceengineer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    //login
    @GET("collections/engineer_login/?apiKey=h3IQbkK_J7am5m45-Wd-FryEVIqrvOJI")
    Call<List<LoginDataBringer>> Login(@Query("q") String s);

    @GET("collections/AssignedTo/?apiKey=h3IQbkK_J7am5m45-Wd-FryEVIqrvOJI")
    Call<List<Home_Model>> getHomeData(@Query("q") String apiKey);


    @GET("sms.php")
    Call<String> getOTP_Reg(@Query("username") String un,@Query("password") String pass,@Query("from") String from,@Query("to")String to,@Query("msg") Integer msg,@Query("type") String type);
    @GET("sms.php")
    Call<String> getOTP(@Query("username") String un,@Query("password") String pass,@Query("from") String from,@Query("to")String to,@Query("msg") Integer msg,@Query("type") String type);


    @GET("collections/performanceEngineer/?apiKey=h3IQbkK_J7am5m45-Wd-FryEVIqrvOJI")
    Call<List<Report_Data_Bringer>> getReport_data(@Query("q") String str);
}
