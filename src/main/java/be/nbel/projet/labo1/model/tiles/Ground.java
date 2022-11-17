package be.nbel.projet.labo1.model.tiles;

import be.nbel.projet.labo1.model.Coordonnees;
import org.newdawn.slick.Color;

public class Ground implements ITiles {
    private Coordonnees coordonnees;

    public Ground(int x, int y) {
        coordonnees = new Coordonnees(x, y);
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
        return new Color(100, 100, 100);
    }

    @Override
    public boolean renderBorder() {
        return true;
    }

    @Override
    public String toString() {
        return "Ground " + getCoord().toString();
    }
}
