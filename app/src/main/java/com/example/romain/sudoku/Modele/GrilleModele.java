package com.example.romain.sudoku.Modele;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romain on 04/02/2017.
 */

public class GrilleModele implements Parcelable {

    private List<List> grille;
    private List<SudokuCase> ligneGrille;

    public GrilleModele(String grilleS){
        grille = new ArrayList<>();
        char[] grilleC = grilleS.toCharArray();

        for(int i=0;i<9;i++){
            ligneGrille = new ArrayList<>();
            for(int j=0;j<9;j++){
                ligneGrille.add(new SudokuCase(Integer.valueOf(String.valueOf(grilleC[(i*9)+j]))));
            }
            grille.add(ligneGrille);
        }

    }

    public List<List> getGrille() {
        return grille;
    }

    public void modiferCase(int x, int y, int valeur){
        List<SudokuCase> ligne = grille.get(x);
        SudokuCase caseS = ligne.get(y);
        caseS.setValeur(valeur);
        ligne.set(y, caseS);
    }

    public boolean ajoutPossible(int x,int y, int valeur){
        boolean retour = true;

        //Test ligne
        List<SudokuCase> ligne = grille.get(x);
        for(SudokuCase caseSudoku : ligne){
            if(caseSudoku.getValeur() == valeur){
                retour = false;
                break;
            }
        }
        if(retour) {
            List<SudokuCase> colonne = new ArrayList<>();

            for (List<SudokuCase> listeLigne : grille) {
                colonne.add(listeLigne.get(y));
            }

            for(SudokuCase caseSudoku : colonne){
                if(caseSudoku.getValeur() == valeur){
                    retour = false;
                    break;
                }
            }
        }

        if(retour) {
            int xCarre = x - x % 3;
            int yCarre = y - y % 3;
            List<SudokuCase> carre = new ArrayList<>();
            for (int i = xCarre; i < xCarre + 3; i++) {
                List<SudokuCase> ligneGrille = grille.get(i);
                for (int j = yCarre; j < yCarre + 3; j++) {
                    carre.add(ligneGrille.get(j));
                }
            }

            for (SudokuCase caseSudoku : carre) {
                if(caseSudoku.getValeur() == valeur){
                    retour = false;
                    break;
                }
            }
        }
        return retour;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(grille);

    }

    public static final Parcelable.Creator<GrilleModele> CREATOR = new Parcelable.Creator<GrilleModele>() {

        @Override
        public GrilleModele createFromParcel(Parcel source) {
            return new GrilleModele(source);
        }


        @Override
        public GrilleModele[] newArray(int size) {
            return new GrilleModele[size];
        }

    };

    public GrilleModele(Parcel in) {
        grille = new ArrayList<>();
        in.readList(grille,getClass().getClassLoader());
    }
}
