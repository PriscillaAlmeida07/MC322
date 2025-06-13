package missao;

import java.util.ArrayList;

import ambiente.Ambiente;
import comunicacao.CentralComunicacao;
import exceptions.ErroComunicacaoException;
import interfaces.Curador;
import robo.Robo;

public class MissaoVida implements Missao {
    
    @Override
    public void executar(Robo robo, Ambiente ambiente, CentralComunicacao centralComunicacao){
        ArrayList<Robo> robos = ambiente.getArrayRobos();
        ArrayList<Robo> robosMortos = new ArrayList<>();
        ArrayList<Robo> curadores = new ArrayList<>();

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i).getVida() == 0){
                robosMortos.add(robos.get(i));
            } else if (robos.get(i) instanceof Curador){
                curadores.add(robos.get(i));
            }
        }

        Robo roboProximo = null;
        double distMin = 50;
        double distancia;
        for (int i = 0; i < robosMortos.size(); i++){
            for (int j= 0; j < curadores.size(); j++){
                distancia = Math.sqrt(Math.pow((robosMortos.get(i).getX() - curadores.get(j).getX()), 2)) + (Math.pow((robosMortos.get(i).getY() - curadores.get(j).getY()), 2)) + (Math.pow((robosMortos.get(i).getZ() - curadores.get(j).getZ()), 2));
                if (distancia < distMin){
                    distMin = distancia;
                    roboProximo = curadores.get(j);
                }
            }
            String mensagem = "Você é o robô curador mais próximo para encontrar o robo morto na posição (" + robosMortos.get(i).getX() + "," + robosMortos.get(i).getY() + "," + robosMortos.get(i).getZ() + ")";
            try {
                robo.enviarMensagem(centralComunicacao, roboProximo, mensagem);
                System.out.println("O robo curador " + roboProximo.getNome() + "recebeu uma mensagem sobre um robô morto próximo");
            } catch (ErroComunicacaoException e) {
                System.err.println("Erro: " + e.getMessage());
            }

            
        }
    }


}
