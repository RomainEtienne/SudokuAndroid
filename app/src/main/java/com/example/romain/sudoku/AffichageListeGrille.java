package com.example.romain.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.romain.sudoku.Modele.GrilleModele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romain on 02/02/2017.
 */

public class AffichageListeGrille extends AppCompatActivity {

    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_liste_grille);
        context = this;

        ListView listeViewGrille = (ListView) findViewById(R.id.listeViewGrille);
        Bundle objetbundle = this.getIntent().getExtras();
        int level = objetbundle.getInt("niveau");
        final List<VGrille> listeGrille = new ArrayList<>();
        for(int i=0;i<100;i++){
            listeGrille.add(new VGrille(level, i, (int) (Math.random()*100)));
        }
        MonAdapteurListe adapter = new MonAdapteurListe(this, (ArrayList<VGrille>) listeGrille);
        listeViewGrille.setAdapter(adapter);
        listeViewGrille.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VGrille vGrille = listeGrille.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(AffichageListeGrille.this);
                builder.setTitle("Information");
                builder.setMessage(String.valueOf(vGrille.getNum())+" -- "+vGrille.getDone()+"%");

                builder.setNeutralButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(context, AffichageGrille.class);
                        GrilleModele g = new GrilleModele("008203500009670408346050702430010059967005001000496203280034067703500904004107020");
                        intent.putExtra("grille",g);
                        context.startActivity(intent);
                    }
                });



                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

}
