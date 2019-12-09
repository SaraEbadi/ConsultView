package com.example.consultview;

import com.example.consultview.model.ConsultModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/legalEntity/getLegalEntity/{id}")
    Call<ConsultModel> getMyJson(@Path("id") String id);

}
