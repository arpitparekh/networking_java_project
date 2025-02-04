package com.arpit.networking_java;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiCallinngUsingOkHttp {

  public static void main(String[] args) {


    OkHttpClient client = new OkHttpClient();
    Request request =
    new Request.Builder()
            .url(
                "https://api.openweathermap.org/data/2.5/weather?lat=23.0225&lon=72.5714&appid=0e43ffb8f4f7ae7181b70939ecd8782f&units=metric")

            .build();

    Call call = client.newCall(request);

    // there are two ways to make a call
    // 1. synchronous
    // 2. asynchronous

    // execute() // perform on main there
    // enqueue() // perform on background thread


    // try {
    //   Response res =  call.execute();  // main thread
    // } catch (IOException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }

    call.enqueue(new Callback() {

      @Override
      public void onFailure(Call call, IOException e) {

        System.out.println(e.getMessage());

      }

      @Override
      public void onResponse(Call call, Response res) throws IOException {
        System.out.println(res.body().string());

        client.dispatcher().executorService().shutdown();

      }

    });

    System.out.println("Hello");
    System.out.println("Hello");

  }

}
