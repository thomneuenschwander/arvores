package arvores.binarias.avl;

import arvores.binarias.ArvoreBinaria;

class AVL<T extends Comparable<T>> extends ArvoreBinaria<T> {

    
    @Override
    public void mostrarPropiedades() {
        mostrarPropiedades((NoAvl<T>)raiz);
    }
    private void mostrarPropiedades(NoAvl<T> no) {
        if (no != null) {
			System.out.print(no.getElemento() + "(fator " + (NoAvl.getNivel((NoAvl<T>)no.getDir()) - NoAvl.getNivel((NoAvl<T>)no.getEsq())) + ") "); 
			mostrarPropiedades((NoAvl<T>)no.getEsq()); 
			mostrarPropiedades((NoAvl<T>)no.getDir()); 
		}
    }

    public void inserir(T x) throws Exception {
        raiz = inserir(x, (NoAvl<T>)raiz);
    }

    private NoAvl<T> inserir(T x, NoAvl<T> i) throws Exception {
        if (i == null) {
            i = new NoAvl<>(x);
        } else if (i.compareTo(x) < 0) {
            i.setEsq(inserir(x, ((NoAvl<T>)i.getEsq())));
        } else if (i.compareTo(x) > 0) {
            i.setDir(inserir(x, ((NoAvl<T>)i.getDir())));
        } else {
            throw new Exception("Erro ao inserir!");
        }
        return balancear(i);
    }

    public void remover(T x) throws Exception {
        raiz = remover(x, (NoAvl<T>)raiz);
    }

    private NoAvl<T> remover(T x, NoAvl<T> i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao remover!");
        } else if (i.compareTo(x) < 0) {
            i.setEsq(remover(x, ((NoAvl<T>)i.getEsq())));
        } else if (i.compareTo(x) > 0) {
            i.setDir(remover(x, ((NoAvl<T>)i.getDir())));

        } else if (i.getDir() == null) {
            i = (NoAvl<T>)i.getEsq();
  
        } else if (i.getEsq() == null) {
            i = (NoAvl<T>)i.getDir();

        } else {
            i.setEsq(maiorEsq(i, ((NoAvl<T>)i.getEsq())));
        }
        return balancear(i);
    }

    private NoAvl<T> maiorEsq(NoAvl<T> i, NoAvl<T> j) {
        
        if (j.getDir() == null) {
            i.setElemento(j.getElemento()); 
            j =  (NoAvl<T>)j.getEsq(); 

        } else {
            j.setDir(maiorEsq(i, ((NoAvl<T>)j.getDir())));
        }
        return j;
    }

    private NoAvl<T> balancear(NoAvl<T> no) throws Exception {
        if (no != null) {
            int fator = NoAvl.getNivel(((NoAvl<T>)no.getDir())) - NoAvl.getNivel(((NoAvl<T>)no.getEsq()));

            if (Math.abs(fator) <= 1) {
                no.setNivel();

            } else if (fator == 2) {
                int fatorFilhoDir = NoAvl.getNivel(((NoAvl<T>)no.getDir().getDir())) - NoAvl.getNivel(((NoAvl<T>)no.getDir().getEsq()));

                if (fatorFilhoDir == -1) {
                    no.setDir(rotacionarDir(((NoAvl<T>)no.getDir())));
                }
                no = rotacionarEsq(no);

            } else if (fator == -2) {
                int fatorFilhoEsq = NoAvl.getNivel((NoAvl<T>)no.getEsq().getDir()) - NoAvl.getNivel((NoAvl<T>)no.getEsq().getEsq());

                if (fatorFilhoEsq == 1) {
                    no.setEsq(rotacionarEsq(((NoAvl<T>)no.getEsq())));
                }
                no = rotacionarDir(no);
            } else {
                throw new Exception(
                        "Erro no No(" + no.getElemento() + ") com fator de balanceamento (" + fator + ") invalido!");
            }
        }
        return no;
    }

    private NoAvl<T> rotacionarDir(NoAvl<T> no) {
        System.out.println("Rotacionar DIR(" + no.getElemento() + ")");
        NoAvl<T> noEsq = (NoAvl<T>)no.getEsq();
        NoAvl<T> noEsqDir = (NoAvl<T>)noEsq.getDir();

        noEsq.setDir(no);
        no.setEsq(noEsqDir);
        no.setNivel();
        noEsq.setNivel();

        return noEsq;
    }

    private NoAvl<T> rotacionarEsq(NoAvl<T> no) {
        System.out.println("Rotacionar ESQ(" + no.getElemento() + ")");
        NoAvl<T> noDir = (NoAvl<T>) no.getDir();
        NoAvl<T> noDirEsq = (NoAvl<T>) noDir.getEsq();

        noDir.setEsq(no);
        no.setDir(noDirEsq);

        no.setNivel();
        noDir.setNivel();
        return noDir;
    }

    public static void main(String[] args) throws Exception {
        AVL<Integer> avl = new AVL<>();

        int[] arr = { 4, 35, 10, 13, 3, 30, 15, 12, 7, 40, 20 };

        for (int num : arr) {
            avl.inserir(num);
        }
        	
        avl.inOrdem();

        System.out.println(avl.pesquisar(5));
    }

}
