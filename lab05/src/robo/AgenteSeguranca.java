package robo;

import ambiente.Ambiente;
import arquivos.Arquivo;
import comunicacao.CentralComunicacao;
import enums.EstadoRobo;
import exceptions.*;
import interfaces.Entidade;
import java.util.ArrayList;
import java.util.Scanner;
import missao.*;
import sensores.Sensor;
import subsistemas.*;

public class AgenteSeguranca extends AgenteInteligente {
    
    // Construtor.
    public AgenteSeguranca(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ, ControleMovimento controleMovimento, GerenciadorSensores gerenciadorSensores, ModuloComunicacao moduloComunicacao){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ, controleMovimento, gerenciadorSensores, moduloComunicacao);
    }

    // Obtém a descrição desse robô.
    @Override
    public String getDescricao(){return "Agente (imortal) que pode se movimentar atua na segurança de outros robôs (impedindo ataques em áreas protegidas), pode realizar missões do tipo MissaoSeguranca e MissaoBuscarPonto";}

    // Atribui uma nova missão ao robô.
    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException, VidaNulaException{
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O " + this.getNome() + " está desligado");
        
        // Se a excessão não for lançada, executa a tarefa
        Missao novaMissao;
        if (caso == 1){ // Missao = MissaoSeguranca
            novaMissao = new MissaoSeguranca();
        } else { // Missao = MissaoBuscarPonto
            novaMissao = new MissaoBuscarPonto();
        }
        definirMissao(novaMissao);
    }

    // Realiza a missão já atribuida ao robô.
    @Override
    public void executarMissao(Ambiente ambiente, Arquivo arquivo){
        String mensagem;

        if (missao == null){
            mensagem = "Nenhuma missão foi atribuida ao agente, por favor tente novamente após realizar uma atribuição\n";
            arquivarEPrintar(mensagem, arquivo);

        } else if (missao instanceof MissaoSeguranca){
            System.out.println("O robô esta protegendo os seguintes robôs:\n");

            ArrayList<Entidade> robosEmAlcance = gerenciadorSensores.utilizarSensorRobos(ambiente, this, 10);


            ArrayList<Entidade> robosProx = sensor.monitorar(ambiente, vetorPosicao, 1);
            ArrayList<Robo> robosProtegidos = missao.executar(this, ambiente, robosProx, arquivo);

            if (!robosProtegidos.isEmpty()){
                for (int i=0; i< robosProtegidos.size(); i++){
                    try {
                        System.out.println("O " + robosProtegidos.get(i).getNome() + " está sendo protegido.");
                        enviarMensagem(centralComunicacao, robosProtegidos.get(i), "Você " + robosProtegidos.get(i).getNome() + " está sendo protegido pelo Agente Vida"); //sempre sera valido mas temos q tratar com try msm assim
                    }  catch (ErroComunicacaoException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                }

            } else { // robosEmAlcance está vazio
                mensagem = "Nenhum robô está no raio de segurança do agente, logo nenhum robô pode ser protegido\n";
                arquivarEPrintar(mensagem, arquivo);
            }

        } else { // missao instanceof MissaoBuscarPonto

        }
    }
}

        // if(getMissao() = missaoVida)
        // Missao missao = new MissaoSeguranca();

        // definirMissao(missao);
        // missao.executar(this, ambiente);
        // System.out.println("O robo esta patrulhando\n");