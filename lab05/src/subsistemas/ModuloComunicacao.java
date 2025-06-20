package subsistemas;

import comunicacao.CentralComunicacao;
import exceptions.ErroComunicacaoException;
import java.util.ArrayList;
import robo.Robo;

public class ModuloComunicacao {

    // Subsistema que auxilia os agentes no envio de mensagens padronizadas de qualquer natureza.
    
    // Comunica todos os robôs que foram revividos por uma missão reviver.
    public void comunicarRevividos(CentralComunicacao centralComunicacao, ArrayList<Robo> robosRevividos, Robo robo){
        String mensagem;

        for (int i = 0; i < robosRevividos.size(); i++){
            try {
                mensagem = "Você " + robosRevividos.get(i).getNome() + " reviveu por causa do Agente Vida";
                robo.enviarMensagem(centralComunicacao, robosRevividos.get(i), mensagem);
            } catch (ErroComunicacaoException e){
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }

    // Comunica todos os curadores sobre um robô fraco próximo.
    public void comunicarCuradores(CentralComunicacao centralComunicacao, ArrayList<Robo> robosCuradores, Robo robo, String mensagem){
        for (int i = 0; i < robosCuradores.size(); i++){
            try {
                robo.enviarMensagem(centralComunicacao, robosCuradores.get(i), mensagem);
            } catch (ErroComunicacaoException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }

    // Comunica todos os robôs que estão sendo protegidos por uma missão segurança.
    public void comunicarProtegidos(CentralComunicacao centralComunicacao, ArrayList<Robo> robosProtegidos, Robo robo){
        String mensagem;

        for (int i = 0; i < robosProtegidos.size(); i++){
            try {
                mensagem = "Você " + robosProtegidos.get(i).getNome() + " está sendo protegido pelo Agente Segurança";
                robo.enviarMensagem(centralComunicacao, robosProtegidos.get(i), mensagem);
            } catch (ErroComunicacaoException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }
}
