package be.nbel.projet.labo1.model.tiles;


import be.nbel.projet.labo1.model.Coordonnees;
import org.newdawn.slick.Color;

public class Obstacle implements ITiles {
    private Coordonnees coordonnees;

    public Obstacle(int x, int y) {
        coordonnees = new Coordonnees(x, y);
    }

    public Obstacle(){
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
        return new Color(243, 255, 10);
    }

    @Override
    public String toString() {
        return "Obstacle " + getCoord().toString();
    }
}
