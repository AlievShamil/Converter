package com.example.devcom.converter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devcom.converter.controller.DataController;
import com.example.devcom.converter.controller.URLManager;
import com.example.devcom.converter.listener.IUrlListener;
import com.example.devcom.converter.models.ValCurs;

import java.io.IOException;

public class CbrActivity extends Activity {

    String url = "http://www.cbr.ru/scripts/XML_daily.asp";
    TextView textView;
    Button btnRepeatLoading;
    ProgressBar progressBarLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRepeatLoading= (Button)findViewById(R.id.btnRepeatLoading);
        progressBarLoading=(ProgressBar)findViewById(R.id.progressBarLoading);
        btnRepeatLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarLoading.setVisibility(View.VISIBLE);
                btnRepeatLoading.setVisibility(View.GONE);
                loading();
            }
        });
        textView = (TextView) findViewById(R.id.textView);
        loading();

    }

    private void loading(){
        URLManager urlManager = new URLManager();

        urlManager.execute(new String[]{url});
        urlManager.setUrlListener(new IUrlListener() {
            @Override
            public void whenDownloadContent(String content) {
                ValCurs curs = ValCurs.deserializer(content);
                if(curs!=null){
                    DataController.setValCurs(curs);
                    DataController.saveData(getApplication(),content);
                } else {
                    DataController.loadData(getApplicationContext());
                }
                if(DataController.getValCurs()==null) {
                    //не удалось загрузить данные
                    progressBarLoading.setVisibility(View.GONE);
                    btnRepeatLoading.setVisibility(View.VISIBLE);
                    Toast.makeText(CbrActivity.this, "Не удалось загрузить данные, попробуйте снова.", Toast.LENGTH_SHORT).show();
                } else {
                    //переход на активити конвертора
                    Intent intent = new Intent(CbrActivity.this,ConverterActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}
