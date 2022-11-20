package be.nbel.projet.labo1.model;

public class MovementMapper {
    public static boolean MODE_KEYBOARD = true;
    public static boolean MODE_NUMBER = false;

    private boolean modeKeyBoard;

    public MovementMapper setMode(boolean mode){
        this.modeKeyBoard = mode;
        return this;
    }

    public Movements parse(int key){
        if(modeKeyBoard) return parseKey(key);
        else return parseNumber(key);
    }

    private Movements parseNumber(int key){
        switch (key){
            case 7: // 7 - TOP LEFT
                return Movements.LEFT_TOP;
            case 8: // 8 - TOP
                return Movements.TOP;
            case 9: // 9 - TOP RIGHT
                return Movements.RIGHT_TOP;
            case 4: // 4 - LEFT
                return Movements.LEFT;
            case 6: // 6 - Right
                return Movements.RIGHT;
            case 1: // 1 - bottom left
                return Movements.LEFT_BOTTOM;
            case 2: // 2 - Bottom
                return Movements.BOTTOM;
            case 3: // 3 - Right Bottom
                return Movements.RIGHT_BOTTOM;
            default:
                return Movements.STAND;
        }
    }

    private Movements parseKey(int key){
        switch (key){
            case 71: // 7 - TOP LEFT
                return Movements.LEFT_TOP;
            case 72: // 8 - TOP
                return Movements.TOP;
            case 73: // 9 - TOP RIGHT
                return Movements.RIGHT_TOP;
            case 75: // 4 - LEFT
                return Movements.LEFT;
            case 77: // 6 - Right
                return Movements.RIGHT;
            case 79: // 1 - bottom left
                return Movements.LEFT_BOTTOM;
            case 80: // 2 - Bottom
                return Movements.BOTTOM;
            case 81: // 3 - Right Bottom
                return Movements.RIGHT_BOTTOM;
            case 76: // 5 - Stand
            default:
                return Movements.STAND;
        }
    }



}
