package com.example.romain.sudoku;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.romain.sudoku.Modele.GrilleModele;
import com.example.romain.sudoku.Modele.SudokuCase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Romain on 03/02/2017.
 */

public class Grille extends View implements View.OnTouchListener{

    private int num = 0;
    private int height;
    private int width;
    private Context context;
    private GrilleModele grille;
    private String temps;

    private boolean firstTouch;
    private long tempsClick;

    public Grille(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOnTouchListener(this);
    }

    @Override
    public void onDraw(Canvas canvas){
        List<List> listGrille = grille.getGrille();

        height = getHeight();
        width = getWidth();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);

        Paint paintCase = new Paint();
        paintCase.setColor(Color.GRAY);




        for(int i=1;i<=9;i++){
            if(i%3==0){
                paint.setStrokeWidth(6);
            }else{
                paint.setStrokeWidth(2);
            }
            canvas.drawLine((width/9)*i,0,(width/9)*i,width,paint);
            canvas.drawLine(0,(width/9)*i,width,(width/9)*i,paint);
            canvas.drawRect(width/10*i-25, width + 50, width/10*i+25, width + 100, paintCase);
            canvas.drawText(String.valueOf(i),width/10*i-10,width+75+15,paint);
        }

        for(int i=0;i<9;i++){
            List<SudokuCase> listCase = listGrille.get(i);
            for(int j=0;j<9;j++){
                SudokuCase caseActuelle = listCase.get(j);
                if(caseActuelle.getValeur() != 0){
                    canvas.drawText(String.valueOf(caseActuelle.getValeur()),width/9*j+((width/9)/2-10),width/9*i+((width/9)/2+10),paint);
                }
            }
        }

        Paint paintChrono = new Paint();
        paintChrono.setTextSize(40);
        paintChrono.setColor(Color.GREEN);
        canvas.drawText(temps,width/2-30,width+200,paintChrono);
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
            int x =(int)event.getX();
            int y =(int)event.getY();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    for(int i=1;i<=9;i++){
                        if(x >= width/10*i-25 && x <= width/10*i+25 && y >= width+50 && y <= width+100){
                            num = i;
                        }
                    }
                    Log.d("num", String.valueOf(num));
                    break;
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_UP:
                    int xCase = -1;
                    int yCase = -1;

                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                if (x >= width / 9 * i && x < width / 9 * (i + 1) && y >= width / 9 * j && y < width / 9 * (j + 1)) {
                                    xCase = j;
                                    yCase = i;
                                }
                            }
                        }
                    if(num != 0 && xCase != -1 && yCase != -1) {
                        if(grille.ajoutPossible(xCase, yCase, num)) {
                            grille.modiferCase(xCase, yCase, num);
                        }
                    }
                    num = 0;

                    if(firstTouch && System.currentTimeMillis() - tempsClick < 300 && xCase != -1 && yCase != -1){
                        grille.modiferCase(xCase, yCase, 0);
                        firstTouch = false;
                        Log.d("DT", "doubleTap ");
                    }else{
                        firstTouch = true;
                        tempsClick = System.currentTimeMillis();
                    }
                    break;
            }
            this.invalidate();
            return true;

    }


    public void setGrille(GrilleModele grille) {
        this.grille = grille;
    }
    public void setTemps(String value){
        this.temps = value;
        this.invalidate();
    }
}
