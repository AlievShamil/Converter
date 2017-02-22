package com.example.devcom.converter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.devcom.converter.controller.DataController;
import com.example.devcom.converter.models.ValuteItem;

import org.w3c.dom.Text;


public class ConverterActivity extends Activity {
    Spinner spinnerToValute;
    Spinner spinnerFromValute;
    EditText edValueFrom;
    TextView labelResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        edValueFrom=(EditText)findViewById(R.id.edValuteFrom);
        edValueFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        spinnerFromValute = (Spinner) findViewById(R.id.spinnerFromValute);
        ArrayAdapter<ValuteItem> adapterFromValute =
                new ArrayAdapter<ValuteItem>(
                        getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        DataController.getValCurs().value);
        adapterFromValute.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinnerFromValute.setAdapter(adapterFromValute);
        spinnerFromValute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerToValute = (Spinner) findViewById(R.id.spinnerToValute);
        ArrayAdapter<ValuteItem> adapterToValute =
                new ArrayAdapter<ValuteItem>(
                        getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        DataController.getValCurs().value);
        adapterToValute.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinnerToValute.setAdapter(adapterToValute);
        spinnerToValute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculate();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
            });
        labelResult=(TextView)findViewById(R.id.labelResult);




    }

    private void calculate(){
        try {
            int currentValue = Integer.parseInt(edValueFrom.getText().toString());
            ValuteItem valuteFrom = (ValuteItem) spinnerFromValute.getSelectedItem();
            ValuteItem valuteTo = (ValuteItem) spinnerToValute.getSelectedItem();
            int nominalFrom = valuteFrom.getNominal();
            int nominalTo = valuteTo.getNominal();
            Double valueFrom = Double.parseDouble(valuteFrom.getValue().replace(',','.'));
            Double valueTo = Double.parseDouble(valuteTo.getValue().replace(',','.'));

            Double result = currentValue * (valueFrom / nominalFrom) / (valueTo / nominalTo);
            labelResult.setText(String.format(" %.4f %s",result, valuteTo.getCharCode()));
        }
        catch(Exception e) {
            labelResult.setText("");
        }


    }

}
