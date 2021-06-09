package com.example.vksearch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vksearch.serializeutils.Handler;
import com.example.vksearch.serializeutils.JSONSerialize;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.example.vksearch.utils.NetworkUtils.generateURL;
import static com.example.vksearch.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchGo;
    private TextView result;

    public static final String YS = "167";

    class VKQueryTask extends AsyncTask<URL, Void, String>{


        @Override
        protected String doInBackground(URL... urls) {
            String response = null;

            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            String str ="";
            try {
                //сократили респонс до нужного массива
                JSONArray jsonResponseArray = new JSONObject(response).getJSONObject("response").getJSONArray("items");
                
                for(int i = 0; i<jsonResponseArray.length(); i++){
                    JSONSerialize SerObj= new Handler(jsonResponseArray.getJSONObject(i)).getSerObj();
                    str+= "Имя: " + SerObj.getFirstName() + "\n" +
                            "Фамилия: " +SerObj.getLastName() + "\n" +
                            "ID: " + SerObj.getId() + "\n" +
                            "Город: " + SerObj.getCity() + "\n\n";
                }

                response = jsonResponseArray.toString();
                
            } catch (JSONException e) {
                e.printStackTrace();
            }
            result.setText(str);
        }
    }


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
                    searchField.setTextColor(Color.parseColor("#000000"));
                }
                if(view.getId() == R.id.b_go){
                    URL generatedURL = generateURL( searchField.getText().toString(), YS);

                    new VKQueryTask().execute(generatedURL);

                }
            }
        };
        searchField.setOnClickListener(onClickListener);
        searchGo.setOnClickListener(onClickListener);
    }
}