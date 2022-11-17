package be.nbel.projet.labo1.model;

import be.nbel.projet.genetic.examples.game.Game;
import be.nbel.projet.labo1.controller.GameController;

import static java.lang.Thread.sleep;

public class GravityRunnable implements Runnable {
    private final GameController gc;
    private final int period;
    public GravityRunnable(GameController gc, int period){
        this.gc = gc;
        this.period = period;
    }
    @Override
    public void run() {
        while(true) {
            gc.applyGravity();
            try {
                sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
