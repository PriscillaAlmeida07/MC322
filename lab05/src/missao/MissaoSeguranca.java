package missao;

import java.util.ArrayList;

import ambiente.Ambiente;
import arquivos.Arquivo;
import interfaces.Entidade;
import robo.AgenteInteligente;
import robo.Robo;

public class MissaoSeguranca implements Missao {
    
    // Armazenamos os robosProtegidos para ser possivel mudar o estado da variavel protegido quando executarmos a missao
    ArrayList<Robo> robosProtegidos;

    public MissaoSeguranca(){
        robosProtegidos = new ArrayList<>();
    }

    @Override
    public ArrayList<Robo> executar(Robo robo, Ambiente ambiente, ArrayList<Entidade> robosProx, Arquivo arquivo){
        // Vamos escrever no arquivo sobre a missao realizada
        String conteudo;

        for (int i = 0; i < robosProx.size(); i++){
            if(robosProx.get(i) instanceof Robo roboP && !(roboP instanceof AgenteInteligente)){
                roboP.setProtegido(); // vai mudar para true
                robosProtegidos.add(roboP);
                conteudo = "O Agente Segurança está protegendo o "  + roboP.getNome() + "\n";
                arquivo.escreverNoArquivo(conteudo);
            }
        }
        return robosProtegidos;
    }

    public void encerrarMissao(){
        for (int i = 0; i < robosProtegidos.size(); i++){
            if(robosProtegidos.get(i) instanceof Robo roboProtegido){
                roboProtegido.setProtegido(); // vai mudar para false
            }
        }
    }
}
