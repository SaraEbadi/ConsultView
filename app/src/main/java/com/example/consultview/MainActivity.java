package com.example.consultview;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consultview.model.ConsultModel;
import com.example.consultview.model.Content;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity  implements BtnClickListener{

    RecyclerView recyclerView;
    ConsultAdapter consultAdapter;
    Button btnImport;
    EditText edtInputId;
    TextView txtTitle;
    List<Content> model;
    Context context;
    String htmlText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            init();
        context = this;


    }


    public void fetchData(){
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        GenerateRetrofit generateRetrofit = new GenerateRetrofit(retrofitBuilder);
        final Call<ConsultModel> request = generateRetrofit.apiClient().getMyJson(edtInputId.getText().toString());
        request.enqueue(new Callback<ConsultModel>() {
            @Override
            public void onResponse(Call<ConsultModel> call, final Response<ConsultModel> response) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        model = response.body().getContent();
                        txtTitle.setText(response.body().getTitle());
                        consultAdapter.submitList(model);
                    }
                });
            }
            @Override
            public void onFailure(Call<ConsultModel> call, Throwable t) {
                Log.i("failure", "onFailure: "+t.getMessage());
            }
        });
    }

    public void generateDataList(){
        ConsultModel consultModel = new ConsultModel();
        consultAdapter = new ConsultAdapter(context,new DiffutilCalback());
        recyclerView.setAdapter(consultAdapter);
        consultAdapter.setBtnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        btnImport  = findViewById(R.id.btnImport);
        edtInputId = findViewById(R.id.edtInputId);
        txtTitle = findViewById(R.id.txtTitle);
        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateDataList();
                fetchData();

            }
        });
    }

    @Override
    public void onClickListener(View view, int position) {
        String text = model.get(position).getText();

        if (model.get(position).getIsHtml()) {
                htmlText = text;

            CustomDialog cdd = new CustomDialog(this,htmlText);
            cdd.show();
        }

    }
}
