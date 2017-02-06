package com.example.romain.sudoku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.romain.sudoku.Modele.GrilleModele;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romain on 03/02/2017.
 */

public class AffichageGrille extends AppCompatActivity {

    private Thread th;
    private Grille gDessin;
    private long timeDebut = -1;
    private long temps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grille);

        Bundle bundle = getIntent().getExtras();
        GrilleModele g = (GrilleModele) bundle.get("grille");

        final Grille gDessin = (Grille) findViewById(R.id.dessin);
        this.gDessin = gDessin;
        gDessin.setGrille(g);


        th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long time = System.currentTimeMillis();
                    final long diffTime = (time - timeDebut)/1000;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String value;
                            if(diffTime / 60 == 0){
                                if(diffTime < 10) {
                                    value = "00:0" + String.valueOf(diffTime);
                                }else {
                                    value = "00:" + String.valueOf(diffTime);
                                }
                            }else{
                                if(diffTime/60 < 10){
                                    if(diffTime % 60 < 10) {
                                        value = "0" + (diffTime/60) +":0" + String.valueOf(diffTime % 60);
                                    }else {
                                        value = "0"+ (diffTime/60) +":" + String.valueOf(diffTime % 60);
                                    }
                                }else{
                                    if(diffTime % 60 < 10) {
                                        value = (diffTime/60) +":0" + String.valueOf(diffTime % 60);
                                    }else {
                                        value = (diffTime / 60) + ":"+String.valueOf(diffTime % 60);
                                    }
                                }

                            }

                            gDessin.setTemps(String.valueOf(value));
                        }

                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        long time = System.currentTimeMillis();
        temps = (time - timeDebut);
        th.interrupt();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(timeDebut == -1 ) {
            th.start();
            timeDebut = System.currentTimeMillis();
            gDessin.setTemps("00:00");
        }else{
            timeDebut = System.currentTimeMillis() - temps;
            th.start();
        }
    }
}
