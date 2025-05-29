public interface Comunicavel {

    // Interface implementada por todos os robôs.

    void enviarMensagem(CentralComunicacao centralComunicacao, Comunicavel destinatario, String mensagem) throws ErroComunicacaoException;
    // Lembrar da robô desligado exception

    void receberMensagem(String mensagem);

    void visualizarMensagens();

    
}
