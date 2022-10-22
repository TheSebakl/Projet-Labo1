package be.nbel.projet;

import be.nbel.projet.labo1.controller.GameController;
import be.nbel.projet.labo1.vue.GameView;
import org.lwjgl.Sys;
import org.newdawn.slick.*;

import java.io.File;

import static be.nbel.projet.labo1.vue.GameView.SCREEN_WIDTH;
import static be.nbel.projet.labo1.vue.GameView.SCREEN_HEIGHT;

public class SetupClass {



    public static void main(String[] args) throws SlickException {
        GameController gc  = setupController(args);
        launchGame(gc);
    }

    private static GameController setupController(String[] args){
        GameController gc  = new GameController();
        int w = -1, h = -1;
        if(args.length >= 2){
            try {
                w = Integer.parseInt(args[0]);
                h = Integer.parseInt(args[1]);
            }catch(Exception e){
                System.err.println("Impossible de convertir la taille en Integer");
            }
        }

        gc.setWidthAndHeight(w, h);
        gc.renew();
        return gc;
    }

    private static void launchGame(GameController gc) throws SlickException {
        System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", new File("natives").getAbsolutePath());

        AppGameContainer app = new AppGameContainer(new GameView("Projet - Labo 1", gc));
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }




}
