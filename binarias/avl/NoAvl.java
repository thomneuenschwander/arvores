package arvores.binarias.avl;

import arvores.binarias.NoBin;

class NoAvl<T extends Comparable<T>> extends NoBin<T> {
    public int altura;

    public NoAvl(T elemento) {
        super(elemento);
        this.altura = 0;
    }

    public static <T extends Comparable<T>> int getNivel(NoAvl<T> no){
        if(no == null){
            return 0;
        }
        return no.altura;
    }

    public void setNivel() {
        int esqSubTree = getNivel(((NoAvl<T>)super.getEsq()));
        int dirSubTree = getNivel(((NoAvl<T>)super.getDir()));
        this.altura = ((esqSubTree > dirSubTree) ? esqSubTree : dirSubTree)+1;
    }

}
