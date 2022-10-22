package be.nbel.projet.genetic.examples.cat;

public enum CatColors {
    COLOR_POINT((short)0),BLACK((short)0),CHOCOLATE((short)0),CREAM((short) 50), LILAC((short)100),BLUE((short)200), TABBY_BLUE((short)255),
    TABBY_COLOR_POINT((short)55), TABBY_BLACK((short) 55), TABBY_CHOCOLATE((short) 55), TABBY_CREAM((short) 105), TABBY_LILAC((short)155)
    ;

    private short score;
    CatColors(short score){
        this.score = score;
    }

    public short getScore(){
        return score;
    }

}
