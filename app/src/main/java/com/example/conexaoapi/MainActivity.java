package com.example.conexaoapi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView longitude;
    private TextView latitude;
    //private ProgressDialog load;
//    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pessoa);

        longitude = (TextView) findViewById(R.id.longitude);
        latitude = (TextView) findViewById(R.id.latitude);

    }

    public void btn_achar_localizacao(View view) {
        GetJson download = new GetJson();
        //Chama Async Task
        download.execute();
    }


    public void abrir_mapa(View v){
        Intent it_mapa_coordenadas = new Intent(this, MapsActivity.class);
        it_mapa_coordenadas.putExtra("p_longitude", String.valueOf(longitude));
        it_mapa_coordenadas.putExtra("p_latitude", String.valueOf(latitude));
        startActivity(it_mapa_coordenadas);
    }


    private class GetJson extends AsyncTask<Void, Void, PessoaObj> {

        @Override
        protected void onPreExecute() {
//            load = ProgressDialog.show(MainActivity.this,
//                    "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }

        @Override
        protected PessoaObj doInBackground(Void... params) {
            Utils util = new Utils();

            return util.getInformacao("https://randomuser.me/api/1.4");
        }

        @Override
        protected void onPostExecute(PessoaObj pessoa) {
            longitude.setText(pessoa.getLongitude());
            latitude.setText(pessoa.getLatitude());
        }
    }
}