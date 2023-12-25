package arvores.nao_binarias.trie.trie_ArrayList;

import java.util.ArrayList;
import java.util.List;

class NoTrie {
    public char letra;
    public List<NoTrie> refCollection;
    public boolean isFolha;

    public NoTrie() {
        letra = ' ';
        refCollection = new ArrayList<>();
        isFolha = false;
    }

    public NoTrie(char ch) {
        letra = ch;
        refCollection = new ArrayList<>();
        isFolha = false;
    }

    public NoTrie getRef(char ch) {
        for (NoTrie i : refCollection) {
            if (i.letra == ch) {
                return i;
            }
        }

        return null;
    }

    public void setRef(char ch) {
        NoTrie no = new NoTrie(ch);
        refCollection.add(no);
    }

    public void setRef(char ch, boolean isFolha) {
        NoTrie no = new NoTrie(ch);
        no.isFolha = isFolha;
        refCollection.add(no);
    }

    public NoTrie getRef(int index) {
        return refCollection.get(index);
    }
}
