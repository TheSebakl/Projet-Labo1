package be.nbel.projet.labo1.vue;

import be.nbel.projet.labo1.controller.GameController;
import be.nbel.projet.labo1.model.tiles.ITiles;
import org.newdawn.slick.*;

public class GameView extends BasicGame {

    public static int SCREEN_WIDTH = 1200;
    public static int SCREEN_HEIGHT = 900;
    private static int BANNER_HEIGHT = 100;
    private static int GAME_HEIGHT = SCREEN_HEIGHT - BANNER_HEIGHT;

    private GameController gc;

    public GameView(String title, GameController gc) {
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
    }

    public void keyReleased(int key, char code){
        System.out.println("Key: " + key);
        System.out.println("code: " + code);
        switch (key){
            case 49: // LETTER N
                gc.renew();
                System.out.println("RENEW");
                break;

        }
    }

    private void renderTiles(Graphics g){
        ITiles[][] tiles = gc.getTiles();
        int wt = SCREEN_WIDTH/tiles[0].length;
        int ht = (GAME_HEIGHT)/tiles.length;
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                ITiles tile = tiles[i][j];
                if(tile != null) {
                    g.setColor(tile.getColor());
                    g.fillRect((j * wt), BANNER_HEIGHT + (i * ht), wt, ht);
                }
                g.setColor(new Color(0, 0, 0));
                g.drawRect((j*wt), BANNER_HEIGHT + (i*ht), wt, ht);

            }
        }


    }
    private void renderBanneer(Graphics g){
        g.setColor(new Color(212, 212, 212));
        g.fillRect(0, 0, SCREEN_WIDTH, BANNER_HEIGHT);
        renderButtons(g);
    }

    private void renderFond(Graphics g){
        g.setColor(new Color(100, 243, 250));
        g.fillRect(0, BANNER_HEIGHT, SCREEN_WIDTH, GAME_HEIGHT);
    }

    private void renderButtons(Graphics g){
        //renderButton(g, "Nouveau", 10, 50, 80, 25);
    }

    private void renderButton(Graphics g, String message, int x, int y, int width, int height){
        g.setColor(new Color(200, 200, 200));
        g.fillRect(x, y, width, height);
        g.setColor(new Color(0, 3, 0));
        g.drawRect(x, y, width, height);
        g.drawString(message, x+5, y+5);
    }

}
