package robo;

import ambiente.Ambiente;
import comunicacao.CentralComunicacao;
import enums.EstadoRobo;
import missao.Missao;

public abstract class AgenteInteligente extends Robo {
    
    protected Missao missao;

    // Precisava mesmo do construtor kkkkk
    public AgenteInteligente(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){ 
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
    }
    
    public void definirMissao(Missao missao){
        this.missao = missao;
    }

    public boolean temMissao() {
        return missao != null;
    }

    public void excluirMissao(){
        missao = null;
    }

    public Missao getMissao(){
        return missao;
    }

    public abstract void executarMissao(Ambiente ambiente, CentralComunicacao centralComunicacao);
}
