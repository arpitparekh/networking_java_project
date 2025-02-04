package com.arpit.networking_java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

import com.arpit.networking_java.models.Root;
import com.google.gson.Gson;

public class ApiCalling {
  public static void main(String[] args) {


    // api calling
    // HTTPURLConnection
    // Okhttp

    String api_ur = "http://api.open-notify.org/iss-now.json";

    try {
      URI uri = new URI(api_ur);

      HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
      InputStream stream = connection.getInputStream();

      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

      String json = "";
      json = reader.readLine();
      System.out.println(json);

      Gson gson = new Gson();
      Root root = gson.fromJson(json, Root.class);
      System.out.println(root.iss_position.latitude);
      System.out.println(root.iss_position.longitude);


    } catch (Exception e) {
      System.out.println("Invalid URL");
    }



  }
}
