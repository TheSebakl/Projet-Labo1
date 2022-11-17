package be.nbel.projet.labo1.controller;

import be.nbel.projet.labo1.model.*;
import be.nbel.projet.labo1.model.tiles.ITiles;
import be.nbel.projet.labo1.vue.GameView;

public class GameController {
    private PlateauFactory factory = new PlateauFactory(-1, -1);
    private Plateau game;
    private GameCharacter character;

    public  GameController(){
        renew();
        new Thread(new GravityRunnable(this, 1000)).start();
    }

    public void renew(){

        this.game = factory.generate();
        this.character = new GameCharacter(new Coordonnees(game.getDepart().getCoord()));
    }

    public void setWidthAndHeight(int width, int height){
        this.factory = new PlateauFactory(width, height);
    }

    public ITiles[][] getTiles(){
        return game.getTiles();
    }

    public Coordonnees getMainCharacterCoordonnees(){
        return character.getCoordonnees();
    }

    public void moveCharacter(Movements movement){
        character.move(game, movement);
    }

    public void applyGravity() {
        if(!isGameFinished())
            character.move(this.game, Movements.BOTTOM);
    }

    public boolean isGameFinished() {
        return character.isDead() || character.hasFinished();
    }

    public boolean isDead() {
        return character.isDead();
    }
}
