package robo;

import ambiente.Ambiente;
import enums.EstadoRobo;
import missao.MissaoSeguranca;

public class AgenteSeguranca extends AgenteInteligente {
    
    public AgenteSeguranca(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
    }

    public void executarMissao(Ambiente ambiente){
        Missao missao = new MissaoSeguranca();

        definirMissao(missao);
        missao.executar(this, ambiente);
        System.out.println("O robo esta patrulhando\n");
    }

}