package com.example.romain.sudoku.Modele;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Romain on 04/02/2017.
 */

public class SudokuCase implements Parcelable{

    private int valeur;

    public SudokuCase(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(valeur);
    }

    public static final Parcelable.Creator<SudokuCase> CREATOR = new Parcelable.Creator<SudokuCase>() {

        @Override
        public SudokuCase createFromParcel(Parcel source) {
            return new SudokuCase(source);
        }


        @Override
        public SudokuCase[] newArray(int size) {
            return new SudokuCase[size];
        }

    };

    public SudokuCase(Parcel in) {
        valeur = in.readInt();
    }
}
