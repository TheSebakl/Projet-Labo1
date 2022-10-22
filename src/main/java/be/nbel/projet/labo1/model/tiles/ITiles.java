package be.nbel.projet.labo1.model.tiles;

import be.nbel.projet.labo1.model.Coordonnees;
import org.newdawn.slick.Color;

public interface ITiles {

    public Coordonnees getCoord();

    public String getTileImagePath();

    public Color getColor();

}
