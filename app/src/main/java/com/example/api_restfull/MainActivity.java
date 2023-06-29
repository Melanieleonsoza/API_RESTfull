package com.example.api_restfull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://api.fake-rest.refine.dev/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtSaludo=(TextView) findViewById(R.id.txtSaludo);
       String list="";
       String nombre, apellido, correo,fnac;
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject usr= JSONlista.getJSONObject(i);
            nombre=usr.getString("firstName");
            apellido=usr.getString("lastName");
            correo=usr.getString("email");
            fnac=usr.getString("birthday");
            list=list+"**********************************"+"\n"+"Nombre  " +nombre+"\n"+"Apellido  "+apellido+"\n"
                    +"Correo  "+correo+"\n"+"Fecha_nacimiento  "+fnac+"\n"+"**********************************";
        }
        txtSaludo.setText(list);
    }
}