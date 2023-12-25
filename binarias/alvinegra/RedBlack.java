package arvores.binarias.alvinegra;

import java.util.Scanner;

import arvores.binarias.ArvoreBinaria;

public class RedBlack<T extends Comparable<T>> extends ArvoreBinaria<T> {

    public void mostrarPropiedades() {
        mostrarPropiedades((NoRB<T>) raiz);
    }

    private void mostrarPropiedades(NoRB<T> no) {
        if (no != null) {
            System.out.print(no.getElemento() + ((no.getCor()) ? "(p) " : "(b) "));
            mostrarPropiedades((NoRB<T>) no.getEsq());
            mostrarPropiedades((NoRB<T>) no.getDir());
        }
    }

    public void inserir(T x) throws Exception {

        if (raiz == null) {
            raiz = new NoRB<>(x);
            System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.getElemento() + ").");

        } else if (raiz.getEsq() == null && raiz.getDir() == null) {
            if (raiz.compareTo(x) < 0) {
                raiz.setEsq(new NoRB<>(x));
                System.out.println(
                        "Antes, um elemento. Agora, raiz(" + raiz.getElemento() + ") e esq("
                                + raiz.getEsq().getElemento() + ").");
            } else {
                raiz.setDir(new NoRB<>(x));
                System.out.println(
                        "Antes, um elemento. Agora, raiz(" + raiz.getElemento() + ") e dir("
                                + raiz.getDir().getElemento() + ").");
            }

        } else if (raiz.getEsq() == null) {
            if (raiz.compareTo(x) < 0) {
                raiz.setEsq(new NoRB<>(x));
                System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.getElemento() + "), esq ("
                        + raiz.getEsq().getElemento() + ") e dir(" + raiz.getDir().getElemento() + ").");

            } else if (raiz.getDir().compareTo(x) < 0) {
                raiz.setEsq(new NoRB<>(raiz.getElemento()));
                raiz.setElemento(x);
                System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.getElemento() + "), esq ("
                        + raiz.getEsq().getElemento() + ") e dir(" + raiz.getDir().getElemento() + ").");

            } else {
                raiz.setEsq(new NoRB<>(raiz.getElemento()));
                raiz.setElemento(raiz.getDir().getElemento());
                raiz.getDir().setElemento(x);
                System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.getElemento() + "), esq ("
                        + raiz.getEsq().getElemento() + ") e dir(" + raiz.getDir().getElemento() + ").");
            }
            ((NoRB<T>) raiz.getEsq()).setCor(false);
            ((NoRB<T>) raiz.getDir()).setCor(false);

        } else if (raiz.getDir() == null) {
            if (raiz.compareTo(x) > 0) {
                raiz.setDir(new NoRB<>(x));
                System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.getElemento() + "), esq ("
                        + raiz.getEsq().getElemento() + ") e dir(" + raiz.getDir().getElemento() + ").");

            } else if (raiz.getEsq().compareTo(x) < 0) {
                raiz.setDir(new NoRB<>(raiz.getElemento()));
                raiz.setElemento(x);
                System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.getElemento() + "), esq ("
                        + raiz.getEsq().getElemento() + ") e dir(" + raiz.getDir().getElemento() + ").");

            } else {
                raiz.setDir(new NoRB<>(raiz.getElemento()));
                raiz.setElemento(raiz.getEsq().getElemento());
                raiz.getEsq().setElemento(x);
                System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.getElemento() + "), esq ("
                        + raiz.getEsq().getElemento() + ") e dir(" + raiz.getDir().getElemento() + ").");
            }
            ((NoRB<T>) raiz.getEsq()).setCor(false);
            ((NoRB<T>) raiz.getDir()).setCor(false);

        } else {
            System.out.println("Arvore com tres ou mais elementos...");
            inserir(x, null, null, null, ((NoRB<T>) raiz));
        }
        ((NoRB<T>) raiz).setCor(false);
    }

    private void inserir(T x, NoRB<T> bisavo, NoRB<T> avo, NoRB<T> pai, NoRB<T> i) throws Exception {
        if (i == null) {
            if (pai.compareTo(x) < 0) {
                pai.setEsq(new NoRB<>(x, true));
                i = pai;
            } else {
                pai.setDir(new NoRB<>(x, true));
                i = pai;
            }
            if (pai.getCor() == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {

            if (i.getEsq() != null && i.getDir() != null && ((NoRB<T>) i.getEsq()).getCor() == true
                    && ((NoRB<T>) i.getDir()).getCor() == true) {
                i.setCor(true);
                ((NoRB<T>) i.getEsq()).setCor(false);
                ((NoRB<T>) i.getDir()).setCor(false);

                if (i == raiz) {
                    i.setCor(false);
                } else if (pai.getCor() == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (i.compareTo(x) < 0) {
                inserir(x, avo, pai, i, ((NoRB<T>) i.getEsq()));
            } else if (i.compareTo(x) > 0) {
                inserir(x, avo, pai, i, ((NoRB<T>) i.getDir()));
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private void balancear(NoRB<T> bisavo, NoRB<T> avo, NoRB<T> pai, NoRB<T> i) {

        if (pai.getCor() == true) {
            if (pai.compareTo(avo) > 0) {
                if (i.compareTo(pai) > 0) {
                    avo = ((NoRB<T>) rotEsq(avo));
                } else {
                    avo = ((NoRB<T>) rotDirEsq(avo));
                }
            } else {
                if (i.compareTo(pai) < 0) {
                    avo = ((NoRB<T>) rotDir(avo));
                } else {
                    avo = ((NoRB<T>) rotEsqDir(avo));
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.compareTo(bisavo) < 0) {
                bisavo.setEsq(avo);
            } else {
                bisavo.setDir(avo);
            }

            avo.setCor(false);
            ((NoRB<T>) avo.getEsq()).setCor(true);
            ((NoRB<T>) avo.getDir()).setCor(true);
            System.out.println("Reestabeler cores: avo(" + avo.getElemento() + "->branco) e avo.esq / avo.dir("
                    + avo.getEsq().getElemento() + "," + avo.getDir().getElemento() + "-> pretos)");
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        RedBlack<Integer> rb = new RedBlack<>();

        int[] arr = { 4, 35, 10, 13, 3, 30, 15, 12, 7, 40, 20 };
        System.out.println();

        for(int num : arr){
            rb.inserir(num);
        }

        System.out.println();

        rb.mostrarPropiedades();
        System.out.println();
        rb.inOrdem();
        sc.close();
    }
}
