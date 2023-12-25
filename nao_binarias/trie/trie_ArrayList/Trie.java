package arvores.nao_binarias.trie.trie_ArrayList;

class Trie {
    NoTrie root;

    public Trie() {
        root = new NoTrie();
    }

    public void inserir(String string) {
        inserir(string, root, 0);
    }

    private void inserir(String str, NoTrie no, int i) {
        if (i < str.length()) {
            char ch = str.charAt(i);

            if (no.getRef(ch) == null) {
                if (str.length() - 1 == i) {
                    no.setRef(ch, true);
                } else {
                    no.setRef(ch);
                }
            }
            inserir(str, no.getRef(ch), i + 1);
        }
    }

    public void mostrarPalavra(String palavra) {
        mostrarPalavra(palavra, root, 0);
    }

    private void mostrarPalavra(String str, NoTrie no, int i) {
        if (i < str.length() && no != null) {
            char ch = str.charAt(i);
            System.out.print(ch);
            mostrarPalavra(str, no.getRef(ch), i + 1);
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.inserir("bosta");
        trie.inserir("bostinha");

        trie.mostrarPalavra("bosta");
    }
}