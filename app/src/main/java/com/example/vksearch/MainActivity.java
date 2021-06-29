package com.example.vksearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.vksearch.utils.NetworkUtils.generateURL;
import static com.example.vksearch.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchGo;
    private ProgressBar loadingIndIndicator;

    private RecyclerView numbersList;
    private UsersAdapter usersAdapter;

    public static final String YS = "167";
    public static final String KORSAKOV = "6984";
    public SharedPreferences CitySettings;

    public int countCity() {
        //здесь считается количество городов для URL массива
        int count=0;
        CitySettings = getSharedPreferences("CitySettings", MODE_PRIVATE);
        if (CitySettings.getBoolean("YS", true )) count++;
        if (CitySettings.getBoolean("Korsakov", true )) count++;
        return count;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = (EditText) findViewById(R.id.et_search_field);
        searchGo = findViewById(R.id.b_go);
        loadingIndIndicator = findViewById(R.id.pb_loading);




        numbersList = findViewById(R.id.rv_users);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);//создаем менеджер
        numbersList.setLayoutManager(layoutManager);
        numbersList.setHasFixedSize(true);//указали что знаем размер нашего списка.

        //адаптер:


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 0;
                CitySettings = getSharedPreferences("CitySettings", MODE_PRIVATE);

                if (countCity()==0){
                    Toast.makeText(MainActivity.this, "Не заданы города в City settings", Toast.LENGTH_SHORT).show();
                    return;
                }
                URL[] asdf = new URL[countCity()];
                if(CitySettings.getBoolean("YS", true )){
                    asdf [i] = generateURL( searchField.getText().toString(), YS);
                    i++;
                }
                if(CitySettings.getBoolean("Korsakov", true )){
                    asdf [i] = generateURL( searchField.getText().toString(), KORSAKOV);
                    i++;
                }




                new VKQueryTask().execute(asdf);
            }
        };
        //searchField.setOnClickListener(onClickListener);
        searchGo.setOnClickListener(onClickListener);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Intent intent = new Intent (MainActivity.this, SettingsCityActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "City settings");
        return super.onCreateOptionsMenu(menu);
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

                    //считаем количество пользователей в ответе. (это то число, которое есть в ответе, может быть не точным)
//                    try {
//                        count1 += new JSONObject(response.get(i))
//                                .getJSONObject("response").getInt("count");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

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
            int count2 = 0;
            List<String[]> users = new ArrayList<>();
            loadingIndIndicator.setVisibility(View.GONE);

            for(int i = 0; i< response.size(); i++){
                try {

                    //сократили респонс до нужного массива
                    JSONArray jsonResponseArray = new JSONObject(response.get(i))
                            .getJSONObject("response").getJSONArray("items");

                    for(int f = 0; f<jsonResponseArray.length(); f++){
                        JSONSerialize SerObj= new Handler(jsonResponseArray.getJSONObject(f)).getSerObj();
                        users.add(new String[]{SerObj.getFirstName(), SerObj.getLastName(),SerObj.getId(),SerObj.getBdate(),SerObj.getPhoto_50(),SerObj.getCity()});

                        count2++;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            Toast count = Toast.makeText(MainActivity.this, String.valueOf(count2), Toast.LENGTH_SHORT);
            count.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 300);
            count.show();

            usersAdapter = new UsersAdapter(users.size(), users, MainActivity.this);
            numbersList.setAdapter(usersAdapter);

            if(count2==0){
                Toast toast = Toast.makeText(MainActivity.this, R.string.ZeroCountSearchUsers, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 300); toast.show();
            }else {
            }
        }
    }
}