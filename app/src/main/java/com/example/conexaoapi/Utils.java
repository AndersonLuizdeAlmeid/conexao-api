package com.example.conexaoapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public PessoaObj getInformacao(String end) {
        String json;
        PessoaObj retorno;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJson(json);

        return retorno;
    }

    private PessoaObj parseJson(String json) {
        try {
            PessoaObj pessoa = new PessoaObj();

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("results");


//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Date data;

            JSONObject objArray = array.getJSONObject(0);

            JSONObject obj = objArray.getJSONObject("location");


            JSONObject coordenadas = obj.getJSONObject("coordinates");
            pessoa.setLongitude(coordenadas.getString("longitude"));
            pessoa.setLatitude(coordenadas.getString("latitude"));



            return pessoa;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}