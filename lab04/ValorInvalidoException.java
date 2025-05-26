public class ValorInvalidoException extends Exception {
    public ValorInvalidoException() {
        super("Valor inválido, você só pode digitar 1 ou 2");
    }
}