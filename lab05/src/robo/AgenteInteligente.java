package robo;

import ambiente.Ambiente;
import arquivos.Arquivo;
import comunicacao.CentralComunicacao;
import enums.EstadoRobo;
import missao.Missao;
import missao.MissaoSeguranca;

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
        if(missao instanceof MissaoSeguranca missaoSeguranca)
            missaoSeguranca.encerrarMissao();
        missao = null;
    }

    public Missao getMissao(){
        return missao;
    }

    public abstract void executarMissao(Ambiente ambiente, CentralComunicacao centralComunicacao, Arquivo arquivo);
}
