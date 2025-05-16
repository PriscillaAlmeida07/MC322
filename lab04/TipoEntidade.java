public enum TipoEntidade {
    VAZIO('V'),
    ROBO('R'),
    OBSTACULO('O'),
    TAPETE('T'),
    DESCONHECIDO('D'); 

    private final char caractere;

    private TipoEntidade(char caractere) {
        this.caractere = caractere;
    }

    public char getCaractere() {
        return  caractere;
    }
}