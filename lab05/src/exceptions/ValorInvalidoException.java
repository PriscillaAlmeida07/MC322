package exceptions;

public class ValorInvalidoException extends Exception {

    // Excessão lançada quando um usuário digita um valor não permitido.
    public ValorInvalidoException(){
        super("Valor inválido, você só pode digitar 1 ou 2");
    }
}