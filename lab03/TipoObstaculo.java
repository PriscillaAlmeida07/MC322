public enum TipoObstaculo {

    // Cada tipo de obstáculo é definido por suas dimensões (largura, comprimento, altura).
    ARVORE("árvore", 2, 2, 10),
    BLOCO("bloco",5, 5, 5),
    NUVEM("nuvem", 5, 5, 2),
    BURACO("buraco",1, 1, 0); // Profundidade do buraco começa em 0, mas pode ser alterada

    private final String nome;
    private final int largura;
    private final int comprimento;
    private int altura;

    // Construtor.
    TipoObstaculo(String nome, int largura, int comprimento, int altura){
        this.nome = nome;
        this.largura = largura;
        this.comprimento = comprimento;
        this.altura = altura;
    }

    // Obtém o nome do obstáculo.
    public String getNome(){
        return nome;
    }

    // Obtém a largura do obstáculo.
    public int getLargura(){
        return largura;
    }

    // Obtém o comprimento do obstáculo.
    public int getComprimento(){
        return comprimento;
    }

    // Obtém a altura do obstáculo.
    public int getAltura(){
        return altura;
    }

    // Define a profundidade do buraco.
    public void setAltura(int altura){
        this.altura = altura;
    }
}
