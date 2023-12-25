package arvores.binarias.bst;

import arvores.binarias.ArvoreBinaria;
import arvores.binarias.NoBin;

public class BST<T extends Comparable<T>> extends ArvoreBinaria<T> {

    @Override
    public void mostrarPropiedades() {
        System.out.println("A arvore PODE esta desbalanciada!");
        System.out.println("maior profundidade -> " + (getAltura() + 1));
        System.out.println("numero de nos -> " + getNumeroNo());
    }

    // ----------------------INSERIR PASSANDO ENDEREÇO
    public NoBin<T> inserir(T x, NoBin<T> i) throws Exception {
        if (i == null) {
            i = new NoBin<>(x);
        } else if (i.compareTo(x) < 0) {
            i.setEsq(inserir(x, i.getEsq()));
        } else if (i.compareTo(x) > 0) {
            i.setDir(inserir(x, i.getDir()));
        } else {
            throw new Exception("Nao se pode inserir elementos repetidos em uma BST");
        }
        return i;
    }

    public void inserir(T x) throws Exception {
        raiz = inserir(x, raiz);
    }

    // ----------------------INSERIR PASSANDO O PAI
    public void inserirPaiREC(T x, NoBin<T> i, NoBin<T> pai) throws Exception {
        if (i == null) {
            if (pai.compareTo(x) < 0) {
                pai.setEsq(new NoBin<>(x));
            } else {
                pai.setDir(new NoBin<>(x));
            }
        } else if (i.compareTo(x) < 0) {
            inserirPaiREC(x, i.getEsq(), i);
        } else if (i.compareTo(x) > 0) {
            inserirPaiREC(x, i.getDir(), i);
        } else {
            throw new Exception("Nao se pode inserir elementos repetidos em uma BST");
        }
    }

    public void inserirPai(T x) throws Exception {
        if (raiz == null) {
            raiz = new NoBin<>(x);
        } else if (x.compareTo(raiz.getElemento()) < 0) {
            inserirPaiREC(x, raiz.getEsq(), raiz);
        } else if (x.compareTo(raiz.getElemento()) > 0) {
            inserirPaiREC(x, raiz.getDir(), raiz);
        } else {
            throw new Exception("Nao se pode inserir elementos repetidos em uma BST");
        }
    }

    // ----------------------MOSTRAR A ALTURA DO NO
    private int getAltura(T x, NoBin<T> i, int count) throws Exception {
        if (i.compareTo(x) == 0) {
            return count;
        } else if (i.compareTo(x) < 0) {
            return getAltura(x, i.getEsq(), count + 1);
        } else if (i.compareTo(x) > 0) {
            return getAltura(x, i.getDir(), count + 1);
        } else {
            throw new Exception("Elemento nao encontrado.");
        }
    }

    public int getAltura(T x) throws Exception {
        return getAltura(x, raiz, 0);
    }

    // ----------------------PEGAR MAIOR NO DA SUB ARVORE A ESQUERDA
    public NoBin<T> getMaiorNo(NoBin<T> subRoot) {
        NoBin<T> i = subRoot;
        if (i.getDir() == null) {
            return i;
        }
        NoBin<T> pai = subRoot;
        for (; i != null; pai = i, i = i.getDir())
            ;
        return pai;
    }

    // ----------------------------------------------------REMOVER
    public void remover(T x) throws Exception {
        raiz = remover(x, raiz);
    }

    public NoBin<T> remover(T x, NoBin<T> i) throws Exception {
        if (i == null) {
            throw new Exception("Nao ha elementos a serem removidos");
        } else if (i.compareTo(x) < 0) {
            i.setEsq(remover(x, i.getEsq()));
        } else if (i.compareTo(x) > 0) {
            i.setDir(remover(x, i.getDir()));
        } else if (i.getDir() == null) {
            i = i.getEsq();
        } else if (i.getEsq() == null) {
            i = i.getDir();
        } else {
            i.setDir(doisFilhosMaiorEsq(i, i.getDir()));
        }
        return i;
    }

    private NoBin<T> doisFilhosMaiorEsq(NoBin<T> i, NoBin<T> j) {
        if (j.getDir() == null) {
            i.setElemento(j.getElemento());
            j = j.getEsq();
        } else {
            j.setDir(doisFilhosMaiorEsq(i, j.getDir()));
        }
        return j;
    }

    // -----------------------------------------------------------
    public static void main(String[] args) throws Exception {
        BST<Integer> bst = new BST<>();
        bst.inserir(1);
        bst.inserir(2);
        bst.inserir(3);
        bst.inserir(4);
        bst.inserir(0);
        bst.inOrdem();
        bst.mostrarPropiedades();
    }

}
