package missao;

import ambiente.Ambiente;
import arquivos.Arquivo;
import java.util.ArrayList;
import robo.AgenteInteligente;
import robo.Robo;

public class MissaoReviver implements Missao {


    @Override
    public void executar(AgenteInteligente robo, Ambiente ambiente, ArrayList<Robo> robosAfetados, Arquivo arquivo){
        String mensagem;

        for (int i = 0; i < robosAfetados.size(); i++){
        
            robosAfetados.get(i).setVida(1);
            mensagem = "O agente reviveu o " + robosAfetados.get(i).getNome() + " que agora está com vida " + robosAfetados.get(i).getVida() + "/10\n";
            robo.arquivarEPrintar(mensagem, arquivo);
        }
    }
}



//  Se preciso voltar a usar
    // String mensagem = "Você é o robô curador mais próximo para encontrar o robo morto na posição (" + robosAfetados.get(i).getX() + "," + robosAfetados.get(i).getY() + "," + robosAfetados.get(i).getZ() + ")";
    // try {
    //     robo.enviarMensagem(centralComunicacao, roboProximo, mensagem);
    //     System.out.println("O robo curador " + roboProximo.getNome() + "recebeu uma mensagem sobre um robô morto próximo");
    // } catch (ErroComunicacaoException e) {
    //     System.err.println("Erro: " + e.getMessage());
    // }