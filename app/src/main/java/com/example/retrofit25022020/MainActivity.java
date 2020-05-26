package com.example.retrofit25022020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cách sử dụng lib Retrofit gồm có 4 bước:
        // 1 : Khởi tạo ra retrofit

        // OkHttpClinet: Config các connection
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                    .readTimeout(30, TimeUnit.SECONDS)
                                    .connectTimeout(30, TimeUnit.SECONDS)
                                    .writeTimeout(30, TimeUnit.SECONDS)
                                    .retryOnConnectionFailure(true)
                                    .protocols(Arrays.asList(Protocol.HTTP_1_1))
                                    .build();
        //Gson :  parse dữ liệu về dạng object của java
        Gson gson = new GsonBuilder()
                .setLenient()
                .disableHtmlEscaping() // base những dữ liệu dính khoảng trắng thì nó tự mất khoảng trắng
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://khoapham.vn/KhoaPhamTraining/json/tien/")
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create(gson)) //add từ phía web về dạng Object của Java
                            .build();
        // 2: Định nghĩa các request call http
        // 3: Gọi request muốn thực thi
        // 4 : Nhận dữ liệu từ request thông qua phương thức Call
    }
}
