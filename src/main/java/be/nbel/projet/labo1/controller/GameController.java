package be.nbel.projet.labo1.controller;

import be.nbel.projet.labo1.model.Plateau;
import be.nbel.projet.labo1.model.PlateauFactory;
import be.nbel.projet.labo1.model.tiles.ITiles;

public class GameController {
    private PlateauFactory factory = new PlateauFactory(-1, -1);
    private Plateau game;

    public  GameController(){
        renew();
    }

    public void renew(){
        this.game = factory.generate();
    }

    public void setWidthAndHeight(int width, int height){
        this.factory = new PlateauFactory(width, height);
    }

    public ITiles[][] getTiles(){
        return game.getTiles();
    }
}
