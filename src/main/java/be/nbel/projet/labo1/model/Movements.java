package be.nbel.projet.labo1.model;

public enum Movements {
    LEFT(-1,0),
    LEFT_TOP(-1,1),
    TOP(0,1),
    RIGHT_TOP(1,1),
    RIGHT(1,0),
    RIGHT_BOTTOM(1,-1),
    BOTTOM(0,-1),
    LEFT_BOTTOM(-1,-1)
    ;

    int X, Y;

    Movements(int x, int y){
        this.X = x;
        this.Y = y*-1;
    }
}
