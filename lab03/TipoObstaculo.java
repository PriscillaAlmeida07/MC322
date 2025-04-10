public enum TipoObstaculo {

    // Cada tipo de obstáculo é definido por suas dimensões (largura, comprimento, altura)
    ARVORE(2, 2, 10),
    BLOCO(5, 5, 5),
    NUVEM(5, 5, 2),
    BURACO(1, 1, 0); // Profundidade do buraco começa em 0, mas pode ser alterada.

    private final int largura;
    private final int comprimento;
    private int altura;

    TipoObstaculo(int largura, int comprimento, int altura) {
        this.largura = largura;
        this.comprimento = comprimento;
        this.altura = altura;
    }

    public int getLargura(int largura){
        return largura;
    }

    public int getComprimento(int comprimento){
        return comprimento;
    }

    public int getAltura(int altura){
        return altura;
    }

    public void setAltura(int altura){
        this.altura = altura;
    }

}
