package com.example.roomdaggermvvm;

public interface ResponseListener<T> {

    void onStart();

    void onFinish();

    void onResponse(ApiResponse<T> apiResponse);

}
