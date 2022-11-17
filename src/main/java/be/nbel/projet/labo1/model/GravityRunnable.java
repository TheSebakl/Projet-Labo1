package be.nbel.projet.labo1.model;

import be.nbel.projet.genetic.examples.game.Game;
import be.nbel.projet.labo1.controller.GameController;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class GravityRunnable implements Runnable {
    private final ArrayList<GameController> gc;
    private final int period;
    public GravityRunnable(ArrayList<GameController> gameControllers, int period){
        this.gc = gameControllers;
        this.period = period;
    }

    @Override
    public void run() {
        while(true) {
            for(GameController gc : this.gc) gc.applyGravity();
            try {
                sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
