package be.nbel.projet.labo1.model;

public class Coordonnees {
    public int X, Y;

    public Coordonnees(int x, int y){
        this.X = x;
        this.Y = y;
    }

    public Coordonnees(Coordonnees coordonnees){
        this.X = coordonnees.X;
        this.Y = coordonnees.Y;
    }

    @Override
    public String toString() {
        return "(" + X + ";" + Y + ")";
    }
}
