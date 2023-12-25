package arvores.binarias;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import arvores.Arvore;

public abstract class ArvoreBinaria<T extends Comparable<T>> implements Arvore<T> {

    protected NoBin<T> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

//______________________________________________________________________ PESQUISAR

    // ----------------------PROCURAR ELEMENTO
    public Boolean pesquisar(T x, NoBin<T> i) throws Exception {
        if (i != null) {
            if (i.compareTo(x) == 0) {
                return true;
            } else if (i.compareTo(x) < 0) {
                return pesquisar(x, i.getEsq());
            } else if (i.compareTo(x) > 0) {
                return pesquisar(x, i.getDir());
            } else {
                throw new Exception("Error");
            }
        } else {
            return false;
        }
    }

    public Boolean pesquisar(T x) throws Exception {
        Boolean resp = pesquisar(x, raiz);
        return resp;
    }

//______________________________________________________________________ MOSTRAR

    // ----------------------MOSTRAR POS ORDEM
    public void posOrdem() {
        System.out.print("Caminhando pos ordem [ ");
        posOrdem(raiz);
        System.out.println("]");
    }

    public void posOrdem(NoBin<T> i) {
        if (i != null) {
            posOrdem(i.getEsq());
            posOrdem(i.getDir());
            System.out.print(i.getElemento() + " ");
        }
    }

    // ----------------------MOSTRAR EM ORDEM
    public void inOrdem() {
        System.out.print("Caminhando em ordem [ ");
        inOrdem(raiz);
        System.out.println("]");
    }

    public void inOrdem(NoBin<T> i) {
        if (i != null) {
            inOrdem(i.getEsq());
            System.out.print(i.getElemento() + " ");
            inOrdem(i.getDir());
        }
    }

    // ----------------------MOSTRAR PRE ORDEM
    public void preOrdem() {
        System.out.print("Caminhando pre ordem [ ");
        preOrdem(raiz);
        System.out.println("]");
    }

    public void preOrdem(NoBin<T> i) {
        if (i != null) {
            System.out.print(i.getElemento() + " ");
            preOrdem(i.getEsq());
            preOrdem(i.getDir());
        }
    }

    // ----------------------MOSTRAR POR NIVEL
    public void BSF(NoBin<T> root) {
        Queue<NoBin<T>> fila = new LinkedList<>();
        fila.add(root);
        while (!fila.isEmpty()) {
            NoBin<T> i = fila.poll();
            System.out.print(i.getElemento() + " ");
            if (i.getEsq() != null) {
                fila.add(i.getEsq());
            }
            if (i.getDir() != null) {
                fila.add(i.getDir());
            }
        }
    }

    public void mostrarPorNivel() {
        System.out.print("Caminhando por nivel [ ");
        BSF(raiz);
        System.out.println("]");
    }

    // ----------------------MOSTRAR ZIG ZAG
    public void mostrarPorZigZag() {
        List<NoBin<T>> lista = new ArrayList<>();
        Deque<NoBin<T>> pilha = new ArrayDeque<>();
        lista.add(raiz);
        boolean start = true;
        while (!lista.isEmpty() || !pilha.isEmpty()) {
            if (start) {
                while (!lista.isEmpty()) {
                    NoBin<T> i = lista.remove(0);
                    System.out.print(i.getElemento() + " ");
                    if (i.getEsq() != null) {
                        pilha.push(i.getEsq());
                    }
                    if (i.getDir() != null) {
                        pilha.push(i.getDir());
                    }
                }
                start = false;
            } else if (!start) {
                while (!pilha.isEmpty()) {
                    NoBin<T> i = pilha.pop();
                    System.out.print(i.getElemento() + " ");
                    if (i.getDir() != null) {
                        lista.add(0, i.getDir());
                    }
                    if (i.getEsq() != null) {
                        lista.add(0, i.getEsq());
                    }
                }
                start = true;
            }
        }
    }
//______________________________________________________________________ ALTURA

    // ---------------------- CALCULAR ALTURA
    private int getAltura(NoBin<T> i, int count) {
        if (i == null) {
            count--;
        } else {
            int esq = getAltura(i.getEsq(), count + 1);
            int dir = getAltura(i.getDir(), count + 1);
            count = esq > dir ? esq : dir;
        }
        return count;
    }

    public int getAltura() {
        int resp = getAltura(raiz, 0);
        return resp;
    }

    // ---------------------- CALCULAR QUANTIDADE DE NOS
    private int getNumeroNo(NoBin<T> i, int count) {
        if (i != null) {
            count++;
            count = getNumeroNo(i.getEsq(), count); 
            count = getNumeroNo(i.getDir(), count); 
        }
        return count;
    }

    public int getNumeroNo() {
        return getNumeroNo(raiz, 0);
    }

//______________________________________________________________________ ROTACIONAR NO

    protected NoBin<T> rotDir(NoBin<T> no) {
        System.out.println("Rotacao DIR(" + no.getElemento() + ")");
        NoBin<T> noEsq = no.getEsq();
        NoBin<T> noEsqDir = noEsq.getDir();

        noEsq.setDir(no);
        no.setEsq(noEsqDir);

        return noEsq;
    }

    protected NoBin<T> rotEsq(NoBin<T> no) {
        System.out.println("Rotacao ESQ(" + no.getElemento() + ")");
        NoBin<T> noDir = no.getDir();
        NoBin<T> noDirEsq = noDir.getEsq();

        noDir.setEsq(no);
        no.setDir(noDirEsq);
        return noDir;
    }

    protected NoBin<T> rotDirEsq(NoBin<T> no) {
        no.setDir(rotDir(no.getDir()));
        return rotEsq(no);
    }

    protected NoBin<T> rotEsqDir(NoBin<T> no) {
        no.setEsq(rotEsq(no.getEsq()));
        return rotDir(no);
    }

}
