package be.nbel.projet.labo1.model;

import be.nbel.projet.labo1.model.tiles.Arrivee;
import be.nbel.projet.labo1.model.tiles.Ground;
import be.nbel.projet.labo1.model.tiles.ITiles;
import be.nbel.projet.labo1.model.tiles.Obstacle;
import org.lwjgl.Sys;
import org.newdawn.slick.Image;

public class GameCharacter {

    private Coordonnees coordonnees;
    private boolean isFinished = false;
    private boolean isDead = false;
    private boolean isInAir = false;
    public GameCharacter(Coordonnees coordonnees){
        this.coordonnees = coordonnees;
    }

    public boolean move(Plateau plateau, Movements movement){
        //System.out.println("Move Player");
        //System.out.println("  - Old coord : " + coordonnees.toString());
        if(!isFinished){
            boolean mov = movement(plateau, movement);
        //    System.out.println("  - New coord : " + coordonnees.toString());
            return mov;
        }
        // TODO vérifier si trou


        return false;
    }

    private boolean movement(Plateau plateau, Movements movements){
        int newX = coordonnees.X + movements.X;
        int newY = coordonnees.Y + movements.Y;
        ITiles objective = plateau.getTile(newX, newY);
        try {
            if (objective instanceof Ground) return false;
            else if (objective instanceof Obstacle)
                if (movements == Movements.BOTTOM) {
                    plateau.removeEnemy(newX, newY);
                    coordonnees.X = newX;
                    coordonnees.Y = newY;
                } else {
                    isFinished = true;
                    isDead = true;
                }
            else {
                coordonnees.X = newX;
                coordonnees.Y = newY;
                if (objective instanceof Arrivee) {
                    isFinished = true;
                    return true;
                }

            }

            ITiles floor = plateau.getTile(newX, newY + 1);
            isInAir = !(floor instanceof Ground);

        }catch(Exception e){
            isFinished = true;
            isDead = true;
        }return true;
    }



    public boolean isDead() {
        return isDead;

    }
    public boolean hasFinished() {
        return isFinished;
    }

    public Coordonnees getCoordonnees(){
        return this.coordonnees;
    }

    public boolean isInAir() {
        return isInAir;
    }
}
