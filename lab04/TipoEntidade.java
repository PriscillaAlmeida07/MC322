public enum TipoEntidade {

    // Cada tipo de entidade possui uma representação característica (em formato de 1 caractere).
    VAZIO('_'),
    ROBO('R'),
    OBSTACULO('O'),
    TAPETE('T'),
    DESCONHECIDO('?'); // acho q nao precisará disso

    private final char caractere;

    // Construtor.
    private TipoEntidade(char caractere){
        this.caractere = caractere;
    }

    // Obtém o caractere do tipo de entidade.
    public char getCaractere(){
        return  caractere;
    }
}