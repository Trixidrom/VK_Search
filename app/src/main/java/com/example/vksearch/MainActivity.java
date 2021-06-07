package com.example.vksearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;

import static com.example.vksearch.utils.NetworkUtils.generateURL;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchGo;
    private TextView result;

    public static final String YS = "167";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //токен
        //64e5e67eb9c34f3f7db2098cc16808456183db6a106252546db8726cbd760806efca36df09c1a3b507139
        //https://api.vk.com/method/users.search?q=%D0%A7%D1%83%D0%BC%D0%B0%D1%87%D0%B5%D0%BD%D0%BA%D0%BE%20%D0%9D%D0%B8%D0%BA%D0%B8%D1%82%D0%B0&city=167&v=5.131&access_token=64e5e67eb9c34f3f7db2098cc16808456183db6a106252546db8726cbd760806efca36df09c1a3b507139
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = (EditText) findViewById(R.id.et_search_field);
        searchGo = findViewById(R.id.b_go);
        result = findViewById(R.id.tv_result);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.et_search_field && searchField.getText().toString().equals("пользователь")){
                    searchField.setText("");
                    //searchField.setTextColor(999);
                }
                if(view.getId() == R.id.b_go){
                    URL generatedURL = generateURL( searchField.getText().toString(), YS);
                    result.setText(generatedURL.toString());
                }
            }
        };
        searchField.setOnClickListener(onClickListener);
        searchGo.setOnClickListener(onClickListener);
    }
}