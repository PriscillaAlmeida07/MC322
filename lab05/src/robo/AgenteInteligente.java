package robo;

import ambiente.Ambiente;
import arquivos.Arquivo;
import comunicacao.CentralComunicacao;
import enums.EstadoRobo;
import missao.*;

public abstract class AgenteInteligente extends Robo {
    
    protected Missao missao;

    // Construtor.
    public AgenteInteligente(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){ 
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
    }

    // Verifica se o agente possui uma missão.
    public boolean temMissao(){
        return missao != null;
    }

    // Obtém a missão do agente.
    public Missao getMissao(){
        return missao;
    }

    // Define a missão do agente.
    public void definirMissao(Missao missao){
        this.missao = missao;
    }

    // Retira a missão de um agente.
    public void excluirMissao(){
        if (missao instanceof MissaoSeguranca missaoSeguranca)
            missaoSeguranca.encerrarMissao();
        missao = null;
    }

    // Método abstrato que permite a realização de uma missão.
    public abstract void executarMissao(Ambiente ambiente, CentralComunicacao centralComunicacao, Arquivo arquivo);

    public void arquivarEPrintar(String mensagem, Arquivo arquivo){
        arquivo.escreverNoArquivo(mensagem);
        System.out.println(mensagem);
    }
}
