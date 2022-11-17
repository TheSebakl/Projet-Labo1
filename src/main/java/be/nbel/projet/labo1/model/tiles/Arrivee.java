package be.nbel.projet.labo1.model.tiles;


import be.nbel.projet.labo1.model.Coordonnees;
import org.newdawn.slick.Color;

public class Arrivee implements ITiles {
    private Coordonnees coordonnees;

    public Arrivee(int x, int y) {
        coordonnees = new Coordonnees(x, y);
    }

    public Arrivee(){
        this(0, 0);
    }

    @Override
    public Coordonnees getCoord() {
        return coordonnees;
    }

    @Override
    public String getTileImagePath() {
        return null;
    }

    @Override
    public Color getColor() {
        return new Color(243, 25, 10);
    }

    @Override
    public boolean renderBorder() {
        return false;
    }

    @Override
    public String toString() {
        return "Arrivee " + getCoord().toString();
    }
}
