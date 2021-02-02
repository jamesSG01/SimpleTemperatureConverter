package com.example.lab2_t00647822;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Declaring variables
    Spinner spinnerOptionList, spinnerOptionList2;
    EditText temp_Input;
    TextView temp_Output;
    ListView viewConversionType, viewInputList, viewOutputList, appTittleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create new array contains units.
        List<String> temperatureUnits = new ArrayList<>();
        temperatureUnits.add("°C");
        temperatureUnits.add("°K");
        temperatureUnits.add("°F");

        temp_Output = (TextView) findViewById(R.id.outputView);
        temp_Input = (EditText) findViewById(R.id.inputView);
        spinnerOptionList = (Spinner) findViewById(R.id.inputOptions);
        spinnerOptionList2 = (Spinner) findViewById(R.id.outputOptions);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,temperatureUnits);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Push ArrayAdapter into spinner.
        spinnerOptionList.setAdapter(arrayAdapter);
        spinnerOptionList2.setAdapter(arrayAdapter);

        //text changes then do the converting
        changesHappen();
    }

    protected void changesHappen() {
        //Update again if user change unit.
        spinnerOptionList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int pos, long id) {
                if (temp_Input.getText().toString().trim().length() > 0)
                    convertingNow();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {


            }});
        spinnerOptionList2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int pos, long id) {
                if (temp_Input.getText().toString().trim().length() > 0)
                    convertingNow();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {


            }});
        //Update value when user typing. => real time processing.
        temp_Input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing here so far.
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0)
                    convertingNow();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //nothing here.
            }
        });
    }
    protected void convertingNow() {
        if (spinnerOptionList.getSelectedItem().toString() == "°C" && spinnerOptionList2.getSelectedItem().toString() == "°F")
            convertC2F();
        else if (spinnerOptionList.getSelectedItem().toString() == "°F" && spinnerOptionList2.getSelectedItem().toString() == "°C")
            convertF2C();
        else if (spinnerOptionList.getSelectedItem().toString() == "°C" && spinnerOptionList2.getSelectedItem().toString() == "°K")
            convertC2K();
        else if (spinnerOptionList.getSelectedItem().toString() == "°K" && spinnerOptionList2.getSelectedItem().toString() == "°C")
            convertK2C();
        else if (spinnerOptionList.getSelectedItem().toString() == "°F" && spinnerOptionList2.getSelectedItem().toString() == "°K")
            convertF2K();
        else if (spinnerOptionList.getSelectedItem().toString() == "°K" && spinnerOptionList2.getSelectedItem().toString() == "°F")
            convertK2F();
        else if (spinnerOptionList.getSelectedItem()  == spinnerOptionList2.getSelectedItem() )
            temp_Output.setText(temp_Input.getText().toString());
    }
    // Convert C to F
    protected void convertC2F() {
        double convertedVal = Double.parseDouble(temp_Input.getText().toString())*9/5+32;
        convertedVal = Math.round(convertedVal * 100.0) / 100.0;
        temp_Output.setText(String.valueOf(convertedVal));
    }
    // Convert F to C
    protected void convertF2C ()  {
        double convertedVal = (Double.parseDouble(temp_Input.getText().toString())-32) *5/9;
        convertedVal = Math.round(convertedVal * 100.0) / 100.0;
        temp_Output.setText(String.valueOf( convertedVal ));
    }
    // Convert C to K
    protected void convertC2K () {
        double convertedVal = Double.parseDouble(temp_Input.getText().toString()) + 273.15;
        convertedVal = Math.round(convertedVal * 100.0) / 100.0;
        temp_Output.setText(String.valueOf( convertedVal));
    }
    protected void convertF2K () {
        double convertedVal = (Double.parseDouble(temp_Input.getText().toString())+459.67) * 5/9;
        convertedVal = Math.round(convertedVal * 100.0) / 100.0;
        temp_Output.setText(String.valueOf( convertedVal));
    }
    //Convert K to C
    protected void convertK2C () {
        double convertedVal = Double.parseDouble(temp_Input.getText().toString()) - 273.15;
        convertedVal = Math.round(convertedVal * 100.0) / 100.0;
        temp_Output.setText(String.valueOf(convertedVal) );
    }
    protected void convertK2F () {
        double convertedVal = (Double.parseDouble(temp_Input.getText().toString())*9/5) + 32;
        convertedVal = Math.round(convertedVal * 100.0) / 100.0;
        temp_Output.setText(String.valueOf( convertedVal ));
    }

}