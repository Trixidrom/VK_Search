package com.example.vksearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsCityActivity extends AppCompatActivity {

    Button btnSave;
    CheckBox cbYS, cbKorsakov;

    public SharedPreferences CitySettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_city);

        cbYS = findViewById(R.id.cb_ys);
        cbKorsakov = findViewById(R.id.cb_korsakov);
        btnSave = findViewById(R.id.btn_save);


        CitySettings = getSharedPreferences("CitySettings", MODE_PRIVATE);
        cbYS.setChecked(CitySettings.getBoolean("YS", true ));
        cbKorsakov.setChecked(CitySettings.getBoolean("Korsakov", true ));



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
                finish();
            }
        });
    }
    protected void saveSettings() {
        CitySettings = getSharedPreferences("CitySettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = CitySettings.edit();
        editor.putBoolean("YS", cbYS.isChecked());
        editor.putBoolean("Korsakov", cbKorsakov.isChecked());
        editor.commit();
    }


}