package com.example.romain.sudoku;

/**
 * Created by Romain on 02/02/2017.
 */

public class VGrille {

    private int level;
    private int num;
    private int done;

    public VGrille(int level, int num, int done) {
        this.level = level;
        this.num = num;
        this.done = done;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }
}
