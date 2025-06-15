package robo;

import ambiente.Ambiente;
import arquivos.Arquivo;
import enums.EstadoRobo;
import missao.*;
import subsistemas.*;

public abstract class AgenteInteligente extends Robo {
    
    // Robô autônomo capaz de realizar missões e que contém subsistemas internos
    protected Missao missao;
    protected ControleMovimento controleMovimento;
    protected GerenciadorSensores gerenciadorSensores;
    protected ModuloComunicacao moduloComunicacao;

    // Construtor.
    public AgenteInteligente(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ, ControleMovimento controleMovimento, GerenciadorSensores gerenciadorSensores, ModuloComunicacao moduloComunicacao){ 
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
        this.controleMovimento = controleMovimento;
        this.gerenciadorSensores = gerenciadorSensores;
        this.moduloComunicacao = moduloComunicacao;
    }

    // Obtém o controle de movimento do robô.
    public ControleMovimento getControleMovimento(){
        return controleMovimento;
    }

    // Obtém o gerenciador de sensores do robô.
    public GerenciadorSensores getGerenciadorSensores(){
        return gerenciadorSensores;
    }

    // Obtém o módulo de comunicação do robô.
    public ModuloComunicacao getModuloComunicacao(){
        return moduloComunicacao;
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
    public void excluirMissao(AgenteInteligente robo){
        if (missao instanceof MissaoSeguranca missaoSeguranca)
            missaoSeguranca.encerrarMissao(robo);
        missao = null;
    }

    // Método abstrato que permite a realização de uma missão.
    public abstract void executarMissao(Ambiente ambiente, Arquivo arquivo);

    // Método que imprime uma mensagem sobre o progresso para o usuário e também guarda essa informação em um arquivo.
    public void arquivarEPrintar(String mensagem, Arquivo arquivo){
        arquivo.escreverNoArquivo(mensagem);
        System.out.println(mensagem);
    }
}
