package arvores.nao_binarias.trie.trie_hash;

import java.util.ArrayList;
import java.util.List;

class TrieHash {

    public No root;

    public TrieHash() {
        root = new No();
    }

    public void inserir(String s) throws Exception {
        inserir(s, root, 0);
    }

    private void inserir(String s, No no, int i) throws Exception {
        
        if(no.tabela[s.charAt(i)] == null){

            no.tabela[s.charAt(i)] = new No(s.charAt(i));

            if(i == s.length() - 1){

                no.tabela[s.charAt(i)].isFolha = true;
            }else{
                inserir(s, no.tabela[s.charAt(i)], i + 1);
            }

        } else if (no.tabela[s.charAt(i)].isFolha == false && i < s.length() - 1){
            inserir(s, no.tabela[s.charAt(i)], i + 1);

        } else {
            throw new Exception("Erro ao inserir!");
        } 
    }

    public void mostrar() {
        mostrar("", root);
    }

    private void mostrar(String s, No no) {
        if (no.isFolha == true) {
            System.out.println("Palavra: " + (s + no.letra));
        } else {
            for (int i = 0; i < no.tabela.length; i++) {
                if (no.tabela[i] != null) {

                    mostrar(s + no.letra, no.tabela[i]);
                }
            }
        }
    }
    public int contarA(){
        int resp = 0;
        if(root != null){
            resp = contarA(root, 0);
        }
        return resp;
    }

    public int contarA(No i, int count){
        
        if(i.letra == 'a' || i.letra == 'A'){
            count++;
        }

        for(int j = 0; j < i.qntRefs; j++){
            if(i.tabela[j] != null){
                count = contarA(i.tabela[j], count);
            }
        }

        return count;
    }
    public List<String> autoComplete(String palavra){
        List<String> res = new ArrayList<>();

        autoComplete(palavra, res, root, 0);

        return res;
    }
    private void autoComplete(String prefix, List<String> lista, No i, int pos){
        if(pos < prefix.length() && i != null){
            autoComplete(prefix, lista, i.tabela[No.hash(prefix.charAt(pos))], pos+1);
        }else{
            for(int j = 0; j < i.qntRefs; j++){
                if(i.tabela[j] != null){
                    addPalavra(i.tabela[j], lista, prefix);
                }
            }
        }
    }
    private void addPalavra(No i, List<String> lista, String S){
        if(i.isFolha){
            System.out.println(i.letra);
            lista.add(S);
        }else{
            for(int j = 0; j < i.qntRefs; j++){
                if(i.tabela[j] != null){
                    addPalavra(i.tabela[j], lista, S + i.letra);
                }
            }
        }
    }
    public int contarAs(){
        int resp = 0;
        if(root != null){
            resp = contarAs(root);
        }
        return resp;
    }

    public int contarAs(No no) {
        int resp = (no.letra == 'A') ? 1 : 0;

        if(no.isFolha == false){
            for(int i = 0; i < no.tabela.length; i++){
                if(no.tabela[i] != null){
                    resp += contarAs(no.tabela[i]);
                }
            }
        }
        return resp;
    }

    public int contarPalavras(){
        return contarPalavras(root, 0);
    }

    private int contarPalavras(No curr, int count){
        if(curr != null)
        {
            if(curr.isFolha){
                count++;
            }
            for(No ref : curr.tabela){
                count = contarPalavras(ref, count);
            }
        }    
        return count;
    }

    public static void main(String[] args) throws Exception {
        TrieHash trie = new TrieHash();
        trie.inserir("batatak");
        trie.inserir("oooo");
        trie.inserir("batatinhak");
        trie.inserir("aaa");

        System.out.println(trie.contarPalavras());
        // List<String> lista = trie.autoComplete("bata");
        // for(String s : lista){
        //     System.out.println(s);
        // }
    }
}