package com.example.consultview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.consultview.model.ConsultModel;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    Button btnImport;
    EditText edtInputId;
    OkHttpClient okHttpClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnImport  =findViewById(R.id.btnImport);
        edtInputId = findViewById(R.id.edtInputId);


        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();

            }
        });




    }

    public void fetchData(){
        okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://legaltest.luhfirm.ir/legalEntity/getLegalEntity/" + edtInputId.getText().toString())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

                Log.d("MainActivity", "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()){
                    String result = response.body().string();
                    Gson gson = new Gson();
                    final ConsultModel consultModel = gson.fromJson(result,ConsultModel.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerViewAdapter = new RecyclerViewAdapter(consultModel);
                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));

                        }
                    });
                }else {
                    Log.i("MainActivity", "onResponse: ");
                }
            }
        });


    }
}
