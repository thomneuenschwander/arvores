package arvores.binarias.alvinegra;

import arvores.binarias.NoBin;

public class NoRB<T extends Comparable<T>> extends NoBin<T> {

    private Boolean cor;

    public NoRB(T elemento, Boolean cor) {
        super(elemento);
        this.cor = cor;
    }

    public NoRB(T elemento) {
        super(elemento);
        this.cor = false;
    }

    public Boolean getCor() {
        return cor;
    }

    public void setCor(Boolean cor) {
        this.cor = cor;
    }
    
}
