package be.nbel.projet.labo1.model;

import be.nbel.projet.labo1.model.tiles.*;

import java.util.Random;

public class Plateau {


    private ITiles depart;
    private ITiles arrivee;
    private ITiles[][] plateau;


    protected Plateau(Plateau plateau){
        this.plateau = plateau.plateau;
        this.depart = plateau.depart;
        this.arrivee = plateau.arrivee;
    }
    protected Plateau(ITiles[][][] tiles){
        this.plateau = tiles[0];
        this.depart = tiles[1][0][0];
        this.arrivee = tiles[1][0][1];

    }

    public ITiles[][] getTiles() {
        return this.plateau;
    }

    public ITiles getDepart(){
        return depart;
    }

    public ITiles getArrivee() {
        return arrivee;
    }

    public ITiles getTile(int x, int y){
        if(x < 0 || x>=plateau[0].length || y >= plateau.length) return new Ground(x, y); // TODO VERIFY
        return plateau[y][x];
    }

    public boolean removeEnemy(int x, int y){
        if(getTile(x, y) instanceof Obstacle){
            plateau[y][x] = null;
            return true;
        }
        return false;
    }

    public Plateau copy(){
        return new Plateau(this);
    }
}

