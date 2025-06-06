package interfaces;

import comunicacao.CentralComunicacao;
import exceptions.ErroComunicacaoException;

public interface Comunicavel {

    // Interface implementada por todos os robôs.
    void enviarMensagem(CentralComunicacao centralComunicacao, Comunicavel destinatario, String mensagem) throws ErroComunicacaoException;
    void receberMensagem(String mensagem);
    void visualizarMensagens();

}
