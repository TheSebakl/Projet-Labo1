package be.nbel.projet.genetic.breeding;

public enum BreedingParentsType {
    ONLY_BEST(OnlyBest.class), ALL(OnlyBest.class), ROULETTE(OnlyBest.class), TOURNAMENT(OnlyBest.class);

    private Class<? extends IBreeding> classe;
    private BreedingParentsType(Class<? extends IBreeding> classe){
        this.classe = classe;
    }

    public Class<? extends IBreeding> getClasse(){
        return classe;
    }

}
