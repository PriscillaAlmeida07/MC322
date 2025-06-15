package missao;

import ambiente.Ambiente;
import arquivos.Arquivo;
import java.util.ArrayList;
import robo.AgenteInteligente;
import robo.Robo;

public class MissaoContactarCuradores implements Missao {
    
    // Alerta todos os robôs curadores dentro de um raio de alcançe de 20 metros sobre os robôs com pouca vida nesse mesmo raio.
    @Override
    public void executar(AgenteInteligente robo, Ambiente ambiente, Arquivo arquivo){
        String mensagem;
        ArrayList<Robo> robosCuradores = robo.getGerenciadorSensores().getRobosCuradores();
        ArrayList<Robo> robosFracos = robo.getGerenciadorSensores().getRobosFracos();

        // Contacta os curadores e registra os robôs fracos
        for (int i = 0; i < robosFracos.size(); i++){
            mensagem = "O robô " + robosFracos.get(i).getNome() + " que está na posição (" + robosFracos.get(i).getX() + "," + robosFracos.get(i).getY() + "," + robosFracos.get(i).getZ() + ") está com apenas " + robosFracos.get(i).getVida() + "/10 de vida, por favor ajude-o";
            robo.getModuloComunicacao().comunicarCuradores(ambiente.getCentralComunicacao(), robosCuradores, robo, mensagem);

            mensagem = robosFracos.get(i).getNome() + " com " + robosFracos.get(i).getVida() + "/10 de vida";
            robo.arquivarEPrintar(mensagem, arquivo);
        }
    }
    
}
