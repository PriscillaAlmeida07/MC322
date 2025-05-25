public interface Comunicavel {

    void enviarMensagem(Comunicavel destinatario, String mensagem);
    // Lembrar da robô desligado exception

    void receberMensagem(String mensagem);

    
}
