package arvores;

public interface Arvore<T> {
    void inserir(T elemento) throws Exception;
    Boolean pesquisar(T elemento) throws Exception;
    void mostrarPropiedades();
}
