package com.example.roomdaggermvvm;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {
    @GET("")
    Observable<JsonObject> getSampleResp();
}
