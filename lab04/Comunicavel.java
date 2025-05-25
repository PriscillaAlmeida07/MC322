public interface Comunicavel {

    // Interface implementada por todos os robôs.

    void enviarMensagem(Comunicavel destinatario, String mensagem);
    // Lembrar da robô desligado exception

    void receberMensagem(String mensagem);

    
}
