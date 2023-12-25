package arvores.binarias;

public class NoBin<T extends Comparable<T>> {
    private T elemento;
    private NoBin<T> esq, dir;

    public NoBin(T elemento2){
        this.elemento = elemento2;
        this.esq = this.dir = null;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public NoBin<T> getEsq() {
        return esq;
    }

    public void setEsq(NoBin<T> esq) {
        this.esq = esq;
    }

    public NoBin<T> getDir() {
        return dir;
    }

    public void setDir(NoBin<T> dir) {
        this.dir = dir;
    }

    public int compareTo(NoBin<T> j){
        return this.elemento.compareTo(j.getElemento());
    }
    public int compareTo(T x){
        return x.compareTo(this.elemento);
    }
    public int compareTo(int x) {
        if (this.elemento instanceof Integer) {
            Integer valorAtual = (Integer) this.elemento;
            return Integer.compare(valorAtual, x);
        } else {
            throw new UnsupportedOperationException("O elemento não é do tipo Integer");
        }
    }
}
