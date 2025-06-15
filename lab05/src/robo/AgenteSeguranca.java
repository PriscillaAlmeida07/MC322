package robo;

import ambiente.Ambiente;
import arquivos.Arquivo;
import enums.EstadoRobo;
import exceptions.*;
import interfaces.Entidade;
import java.util.ArrayList;
import java.util.Scanner;
import missao.*;
import subsistemas.*;

public class AgenteSeguranca extends AgenteInteligente {
    
    // Construtor.
    public AgenteSeguranca(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ, new ControleMovimento(), new GerenciadorSensores(), new ModuloComunicacao());
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
        ArrayList<Entidade> robosEmAlcance = gerenciadorSensores.utilizarSensorRobos(ambiente, this, 10);


        if (missao == null){
            mensagem = "Nenhuma missão foi atribuida ao agente, por favor tente novamente após realizar uma atribuição\n";
            arquivarEPrintar(mensagem, arquivo);

        } else if (missao instanceof MissaoSeguranca){
           
            System.out.println("O robô esta protegendo os seguintes robôs:\n");
            ArrayList<Robo> robosProtegidos = gerenciadorSensores.protegidos(robosEmAlcance);

            if (!robosProtegidos.isEmpty()){
                missao.executar(this, ambiente, arquivo);
                moduloComunicacao.comunicarProtegidos(ambiente.getCentralComunicacao(), robosProtegidos, this);

            } else { // robosProtegidos está vazio
                mensagem = "Nenhum robô está no raio de segurança do agente, logo nenhum robô pode ser protegido\n";
                arquivarEPrintar(mensagem, arquivo);
            }

        } else { 
            // Aqui tera um analogo ao missacontactar?
        }
    }
}

