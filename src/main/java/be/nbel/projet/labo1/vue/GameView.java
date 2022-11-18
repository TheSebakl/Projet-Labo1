package be.nbel.projet.labo1.vue;

import be.nbel.projet.labo1.controller.GamesController;
import be.nbel.projet.labo1.model.Coordonnees;
import be.nbel.projet.labo1.model.GameCharacter;
import be.nbel.projet.labo1.model.Movements;
import be.nbel.projet.labo1.model.tiles.ITiles;
import org.lwjgl.Sys;
import org.newdawn.slick.*;

public class GameView extends BasicGame {

    public static int SCREEN_WIDTH = 1200;
    public static int SCREEN_HEIGHT = 900;
    private static int BANNER_HEIGHT = 100;
    private static int GAME_HEIGHT = SCREEN_HEIGHT - BANNER_HEIGHT;

    private GamesController gc;

    public GameView(String title, GamesController gc) {
        super(title);
        this.gc = gc;
    }


    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {


    }

    @Override
    public void render(GameContainer gameContainer, Graphics g) throws SlickException {
        renderBanneer(g);
        renderFond(g);
        renderTiles(g);
        renderMainCharacter(g);
        renderEnd(g);
    }

    public void keyReleased(int key, char code){
        System.out.println("Key: " + key);
        System.out.println("code: " + code);
        switch (key){
            case 49: // LETTER N
                gc.renew();
                break;
            case 19: // LETTER R
                gc.reload(); //TODO pas le même type de reload si manuel
                break;
            case 71: // 7 - TOP LEFT
                if(gc.isManuel()) gc.getActiveGame().moveCharacter(Movements.LEFT_TOP);
                break;
            case 72: // 8 - TOP
                if(gc.isManuel()) gc.getActiveGame().moveCharacter(Movements.TOP);
                break;
            case 73: // 9 - TOP RIGHT
                if(gc.isManuel()) gc.getActiveGame().moveCharacter(Movements.RIGHT_TOP);
                break;
            case 75: // 4 - Bottom
                if(gc.isManuel()) gc.getActiveGame().moveCharacter(Movements.LEFT);
                break;
            case 77: // 6 - Right
                if(gc.isManuel()) gc.getActiveGame().moveCharacter(Movements.RIGHT);
                break;
            case 79: // 7 - bottom left
                if(gc.isManuel()) gc.getActiveGame().moveCharacter(Movements.LEFT_BOTTOM);
                break;
            case 80: // 8 - Bottom
                if(gc.isManuel()) gc.getActiveGame().moveCharacter(Movements.BOTTOM);
                break;
            case 81: // 9 - Right Bottom
                if(gc.isManuel()) gc.getActiveGame().moveCharacter(Movements.RIGHT_BOTTOM);
                break;
        }
    }

    private void renderTiles(Graphics g){
        ITiles[][] tiles = gc.getActiveGame().getTiles();
        int wt = SCREEN_WIDTH/tiles[0].length;
        int ht = (GAME_HEIGHT)/tiles.length;
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                ITiles tile = tiles[i][j];
                if(tile != null) {
                    g.setColor(tile.getColor());
                    g.fillRect((j * wt), BANNER_HEIGHT + (i * ht), wt, ht);
                    if(tile.renderBorder()) {
                        g.setColor(new Color(0, 0, 0));
                        g.drawRect((j * wt), BANNER_HEIGHT + (i * ht), wt, ht);
                    }
                }

            }
        }


    }

    private void renderMainCharacter(Graphics g) {
        GameCharacter gameCharacterActive = gc.getActiveGame().getMainCharacter();
        if(!gameCharacterActive.isDead()) {
            Coordonnees playerCoordonnees =  gameCharacterActive.getCoordonnees();
            ITiles[][] tiles = gc.getActiveGame().getTiles();
            int wt = SCREEN_WIDTH / tiles[0].length;
            int ht = (GAME_HEIGHT) / tiles.length;


            g.setColor(Color.cyan);
            g.fillRect((playerCoordonnees.X * wt), BANNER_HEIGHT + (playerCoordonnees.Y * ht), wt, ht);
        }
    }

    private void renderBanneer(Graphics g){
        g.setColor(new Color(212, 212, 212));
        g.fillRect(0, 0, SCREEN_WIDTH, BANNER_HEIGHT);
    }

    private void renderFond(Graphics g){
        g.setColor(new Color(200, 243, 255));
        g.fillRect(0, BANNER_HEIGHT, SCREEN_WIDTH, GAME_HEIGHT);
    }

    private void renderEnd(Graphics g){
        if(gc.getActiveGame().isGameFinished()){
            String message = "Game Over";
            if(!gc.getActiveGame().getMainCharacter().isDead()) message = "Félicitation";
            g.setColor(Color.black);

            g.drawString(message, (int)((float)SCREEN_WIDTH/2 - g.getFont().getWidth(message)/2), 80);


        }
    }


}
