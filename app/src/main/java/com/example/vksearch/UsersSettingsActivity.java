package com.example.vksearch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.time.Year;
import java.util.Calendar;

public class UsersSettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayAdapter<String> adapterDay, adapterMonth, adapterYear, adapterAgeFrom, adapterAgeTo;

    String[] day= new String[32];// = {"любой", "1", "2", "3", "4", "5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String[] month = {"любой", "январь", "февраль", "март", "апрель", "май","июнь","июль","август","сентябрь","октябрь","ноябрь","декабрь"};
    String [] year;
    String[] ageFromArr = new String[68];
    String[] ageToArr = new String[68];


    Spinner spinnerDay, spinnerMonth, spinnerYear, spinnerAgeFrom, spinnerAgeTo;
    Button btnOk;
    int bday, bmonth, byear, ageFrom, ageTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_settings);

        btnOk = findViewById(R.id.btn_ok);

        creatArrays();//создаем массивы для дат и возрастов



        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("bday", bday);
                intent.putExtra("bmonth", bmonth);
                intent.putExtra("byear", byear);
                intent.putExtra("ageFrom", ageFrom);
                intent.putExtra("ageTo", ageTo);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        spinnerAgeFrom = findViewById(R.id.sp_age_from);
        adapterAgeFrom = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ageFromArr);
        adapterAgeFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgeFrom.setAdapter(adapterAgeFrom);
        spinnerAgeFrom.setOnItemSelectedListener(this);

        spinnerAgeTo = findViewById(R.id.sp_age_to);
        adapterAgeTo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ageToArr);
        adapterAgeTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgeTo.setAdapter(adapterAgeTo);
        spinnerAgeTo.setOnItemSelectedListener(this);

        spinnerDay = findViewById(R.id.sp_bday);
        adapterDay = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, day);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(adapterDay);
        spinnerDay.setOnItemSelectedListener(this);

        spinnerMonth = findViewById(R.id.sp_bmonth);
        adapterMonth = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, month);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(adapterMonth);
        spinnerMonth.setOnItemSelectedListener(this);

        spinnerYear = findViewById(R.id.sp_byear);
        adapterYear = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, year);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapterYear);
        spinnerYear.setOnItemSelectedListener(this);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ((TextView) adapterView.getChildAt(0)).setTextColor(R.color.design_default_color_secondary);

        switch (adapterView.getId()){
            case R.id.sp_bday:
                bday = i;
                break;
            case R.id.sp_bmonth:
                bmonth = i;
                break;
            case R.id.sp_byear:
                if(i==0){
                    byear = 0;
                }else{
                    byear = Integer.parseInt(year[i]);
                }
                break;
            case R.id.sp_age_from:
                ageFrom = i==0 ? i : i+13 ;
                break;
            case R.id.sp_age_to:
                ageTo = i==0 ? i : i+13 ;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    private void creatArrays() {
        //массивы для возраста
        for(int i=13;i<=80;i++){
            if(i==13){
                ageFromArr[i-13] = "От";
                ageToArr[i-13] = "До";
                continue;
            }
            ageFromArr[i-13] = "От " + Integer.valueOf(i).toString();
            ageToArr[i-13] = "До " + Integer.valueOf(i).toString();
        }

        //массив для дней месяца
        for(int i=0;i<=31;i++){
            if(i==0){
                day[i] = "любой";
                continue;
            }
            day[i] = Integer.valueOf(i).toString();
        }

        //массив для годов
        int CountYear = Calendar.getInstance().get(Calendar.YEAR)-13;
        year = new String[CountYear-1902+1];
        System.out.println(year.length);
        for(int y = CountYear, i=0;y>=1902;y--, i++){
            if(i==0){
                year[i] = "любой";
                continue;
            }
            year[i] = Integer.valueOf(y).toString();
        }
    }




}