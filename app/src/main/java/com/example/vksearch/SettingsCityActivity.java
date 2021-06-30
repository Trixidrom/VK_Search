package com.example.vksearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsCityActivity extends AppCompatActivity {

    Button btnSave;
    CheckBox cbYS, cbKorsakov, cbKholmsk, cbNevelsk, cbAniva, cbDolinsk, cbMakarov, cbPoronaisk,
            cbOkha, cbTomari, cbUglegorsk, cbTymovskoe, cbAleksandrovskSakhalinskii, cbNogliki, cbSmirnih;

    public SharedPreferences CitySettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_city);

        cbYS = findViewById(R.id.cb_ys);
        cbKorsakov = findViewById(R.id.cb_korsakov);
        cbAniva = findViewById(R.id.cb_aniva);
        cbKholmsk = findViewById(R.id.cb_kholmsk);
        cbNevelsk =findViewById(R.id.cb_nevelsk);
        cbDolinsk = findViewById(R.id.cb_dolinsk);
        cbMakarov = findViewById(R.id.cb_makarov);
        cbPoronaisk = findViewById(R.id.cb_poronaisk);
        cbOkha = findViewById(R.id.cb_okha);
        cbTomari = findViewById(R.id.cb_tomari);
        cbUglegorsk = findViewById(R.id.cb_uglegorsk);
        cbTymovskoe = findViewById(R.id.cb_tymovskoe);
        cbAleksandrovskSakhalinskii = findViewById(R.id.cb_aleksandrovsksakhalinskii);
        cbNogliki = findViewById(R.id.cb_nogliki);
        cbSmirnih = findViewById(R.id.cb_smirnih);
        btnSave = findViewById(R.id.btn_save);


        CitySettings = getSharedPreferences("CitySettings", MODE_PRIVATE);
        cbYS.setChecked(CitySettings.getBoolean("YS", true ));
        cbKorsakov.setChecked(CitySettings.getBoolean("Korsakov", true ));
        cbKholmsk.setChecked(CitySettings.getBoolean("Kholmsk", true ));
        cbNevelsk.setChecked(CitySettings.getBoolean("Nevelsk", true ));
        cbAniva.setChecked(CitySettings.getBoolean("Aniva", true ));
        cbDolinsk.setChecked(CitySettings.getBoolean("Dolinsk", true ));
        cbMakarov.setChecked(CitySettings.getBoolean("Makarov", true ));
        cbPoronaisk.setChecked(CitySettings.getBoolean("Poronaisk", true ));
        cbOkha.setChecked(CitySettings.getBoolean("Okha", true ));
        cbTomari.setChecked(CitySettings.getBoolean("Tomari", true ));
        cbUglegorsk.setChecked(CitySettings.getBoolean("Uglegorsk", true ));
        cbTymovskoe.setChecked(CitySettings.getBoolean("Tymovskoe", true ));
        cbAleksandrovskSakhalinskii.setChecked(CitySettings.getBoolean("AleksandrovskSakhalinskii", true ));
        cbNogliki.setChecked(CitySettings.getBoolean("Nogliki", true ));
        cbSmirnih.setChecked(CitySettings.getBoolean("Smirnih", true ));


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
        editor.putBoolean("Kholmsk", cbKholmsk.isChecked());
        editor.putBoolean("Nevelsk", cbNevelsk.isChecked());
        editor.putBoolean("Aniva", cbAniva.isChecked());

        editor.putBoolean("Dolinsk", cbDolinsk.isChecked());
        editor.putBoolean("Makarov", cbMakarov.isChecked());
        editor.putBoolean("Poronaisk", cbPoronaisk.isChecked());
        editor.putBoolean("Okha", cbOkha.isChecked());
        editor.putBoolean("Tomari", cbTomari.isChecked());
        editor.putBoolean("Uglegorsk", cbUglegorsk.isChecked());
        editor.putBoolean("Tymovskoe", cbTymovskoe.isChecked());
        editor.putBoolean("AleksandrovskSakhalinskii", cbAleksandrovskSakhalinskii.isChecked());
        editor.putBoolean("Nogliki", cbNogliki.isChecked());
        editor.putBoolean("Smirnih", cbSmirnih.isChecked());
        editor.commit();
    }


}