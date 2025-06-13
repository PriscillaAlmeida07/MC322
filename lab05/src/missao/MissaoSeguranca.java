package missao;

import ambiente.Ambiente;

public class MissaoSeguranca implements Missao{
    
    private final double raio;

    public MissaoSeguranca(){
        raio = 10;
    }

    public void executar(Robo robo, Ambiente ambiente){
        ambiente.adicionarMissao(this);
    }

    public double getRaio(){
        return raio;
    }

}
