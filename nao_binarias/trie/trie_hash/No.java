package arvores.nao_binarias.trie.trie_hash;

class No {
    public char letra;
    public No[] tabela;
    public boolean isFolha;
    public final int qntRefs = 255;

    public No() {
        this.letra = ' ';
        this.tabela = new No[qntRefs];

        for(int i = 0; i < qntRefs; i++){
            tabela[i] = null;
        }
        
        this.isFolha = false;
    }

    public No(char letra) {
        this.letra = letra;
        this.tabela = new No[qntRefs];

        for(int i = 0; i < qntRefs; i++){
            tabela[i] = null;
        }

        this.isFolha = false;
    }

    public static int hash(char ch){
        return (int)ch;
    }
}
