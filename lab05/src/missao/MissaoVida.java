package missao;

import java.util.ArrayList;

import ambiente.Ambiente;
import arquivos.Arquivo;
import interfaces.Entidade;
import robo.AgenteInteligente;
import robo.Robo;

public class MissaoVida implements Missao {


    @Override
    public ArrayList<Robo> executar(Robo robo, Ambiente ambiente, ArrayList<Entidade> robosProx, Arquivo arquivo){

        String conteudo;
        ArrayList<Robo> robosMortos = new ArrayList<>();

        for (int i = 0; i < robosProx.size(); i++){
            if(robosProx.get(i) instanceof Robo roboProx && !(roboProx instanceof AgenteInteligente)){
                if (roboProx.getVida() == 0){
                    robosMortos.add(roboProx);
                    conteudo = "O Agente Vida reviveu o "  + roboProx.getNome() + "\n";
                    arquivo.escreverNoArquivo(conteudo);
                }
            }
        }
        return robosMortos;
    }
}



//  Se preciso voltar a usar
    // String mensagem = "Você é o robô curador mais próximo para encontrar o robo morto na posição (" + robosMortos.get(i).getX() + "," + robosMortos.get(i).getY() + "," + robosMortos.get(i).getZ() + ")";
    // try {
    //     robo.enviarMensagem(centralComunicacao, roboProximo, mensagem);
    //     System.out.println("O robo curador " + roboProximo.getNome() + "recebeu uma mensagem sobre um robô morto próximo");
    // } catch (ErroComunicacaoException e) {
    //     System.err.println("Erro: " + e.getMessage());
    // }