package subsistemas;

import java.util.ArrayList;

import comunicacao.CentralComunicacao;
import exceptions.ErroComunicacaoException;
import robo.Robo;

public class ModuloComunicacao {
    
    public void comunicarRevividos(CentralComunicacao centralComunicacao, ArrayList<Robo> robosRevividos, Robo robo){
        String mensagem;

        for (int i = 0; i < robosRevividos.size(); i++){
            try {
                mensagem = "Você " + robosRevividos.get(i).getNome() + " reviveu por causa do Agente Vida";
                robo.enviarMensagem(centralComunicacao, robosRevividos.get(i), mensagem);
            } catch (ErroComunicacaoException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }

    public void comunicarCuradores(CentralComunicacao centralComunicacao, ArrayList<Curador> robosCuradores, Robo robo, String mensagem){
        for (int i = 0; i < robosCuradores.size(); i++){
            try {
                robo.enviarMensagem(centralComunicacao, robosCuradores.get(i), mensagem);
            } catch (ErroComunicacaoException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }

}
