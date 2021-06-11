package com.example.vksearch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vksearch.serializeutils.Handler;
import com.example.vksearch.serializeutils.JSONSerialize;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.vksearch.utils.NetworkUtils.generateURL;
import static com.example.vksearch.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchGo;
    private TextView result;
    private ProgressBar loadingIndIndicator;

    public static final String YS = "167";
    public static final String KORSAKOV = "6984";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = (EditText) findViewById(R.id.et_search_field);
        searchGo = findViewById(R.id.b_go);
        result = findViewById(R.id.tv_result);
        loadingIndIndicator = findViewById(R.id.pb_loading);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL[] asdf = new URL[2];
                URL generatedURL = generateURL( searchField.getText().toString(), YS);
                URL generatedURL1 = generateURL( searchField.getText().toString(), KORSAKOV);
                asdf [0] = generatedURL;
                asdf [1] = generatedURL1;
                new VKQueryTask().execute(asdf);
            }
        };
        searchField.setOnClickListener(onClickListener);
        searchGo.setOnClickListener(onClickListener);
    }
    class VKQueryTask extends AsyncTask<URL, Void, List<String>>{
        int count1;
        @Override
        protected void onPreExecute(){
            ViewGroup.LayoutParams linLayoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            loadingIndIndicator.setVisibility(View.VISIBLE);
        }
        @Override
        protected List<String> doInBackground(URL... urls) {
            List<String> response = new ArrayList();
            count1=0;
            for(int i = 0; i< urls.length; i++){
                try {
                    response.add(getResponseFromURL(urls[i]));

                    //считаем количество пользователей в ответе.
                    try {
                        count1 += new JSONObject(response.get(i))
                                .getJSONObject("response").getInt("count");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response.get(0)==null){
                Toast toast = Toast.makeText(MainActivity.this, R.string.NoResponsefromServer, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 300); toast.show();
            }
            return response;
        }

        @Override
        protected void onPostExecute(List<String> response) {
            loadingIndIndicator.setVisibility(View.GONE);

            String str ="";
            for(int i = 0; i< response.size(); i++){
                try {

                    //сократили респонс до нужного массива
                    JSONArray jsonResponseArray = new JSONObject(response.get(i))
                            .getJSONObject("response").getJSONArray("items");

                    for(int f = 0; f<jsonResponseArray.length(); f++){
                        JSONSerialize SerObj= new Handler(jsonResponseArray.getJSONObject(f)).getSerObj();
                        str+= "Имя: " + SerObj.getFirstName() + "\n" +
                                "Фамилия: " +SerObj.getLastName() + "\n" +
                                "ID: " + SerObj.getId() + "\n" +
                                "Город: " + SerObj.getCity() + "\n\n";
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if(count1==0){
                Toast toast = Toast.makeText(MainActivity.this, R.string.ZeroCountSearchUsers, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 300); toast.show();
            }else {
                result.setText(str);
            }
        }
    }
}