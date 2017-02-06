package com.example.romain.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romain on 02/02/2017.
 */

class MyViewHolder{
    public TextView textNum;
    public TextView textPourcentage;
}
public class MonAdapteurListe extends BaseAdapter {

    List<VGrille> myList = new ArrayList<VGrille>();
    Activity context;

    public MonAdapteurListe(Activity context, List<VGrille> myList) {
        this.myList = myList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public VGrille getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder = null;


        // au premier appel ConvertView est null, on inflate notre layout
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.adapteur_liste, parent, false);

            // nous plaçons dans notre MyViewHolder les vues de notre layout
            mViewHolder = new MyViewHolder();
            mViewHolder.textNum = (TextView) convertView.findViewById(R.id.textNum);
            mViewHolder.textPourcentage = (TextView) convertView.findViewById(R.id.textPourcentage);

            // nous attribuons comme tag notre MyViewHolder à convertView
            convertView.setTag(mViewHolder);
        } else {
            // convertView n'est pas null, nous récupérons notre objet MyViewHolder
            // et évitons ainsi de devoir retrouver les vues à chaque appel de getView
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        // nous récupérons l'item de la liste demandé par getView
        VGrille vGrille = (VGrille) getItem(position);

        // nous pouvons attribuer à nos vues les valeurs de l'élément de la liste
        mViewHolder.textNum.setText(String.valueOf(vGrille.getNum()) +"      niveau : "+vGrille.getLevel());
        mViewHolder.textPourcentage.setText(String.valueOf(vGrille.getDone()+"%"));

        mViewHolder.textNum.setTextSize(22);
        mViewHolder.textPourcentage.setTextSize(22);

        if (vGrille.getDone() < 40) {
            mViewHolder.textPourcentage.setTextColor(Color.RED);
        }else {
            mViewHolder.textPourcentage.setTextColor(Color.GREEN);
        }

        // nous retournos la vue de l'item demandé
        return convertView;
    }

}
