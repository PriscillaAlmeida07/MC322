package robo;

import ambiente.Ambiente;
import arquivos.Arquivo;
import enums.EstadoRobo;
import exceptions.*;
import interfaces.*;
import java.util.ArrayList;
import java.util.Scanner;
import missao.*;
import subsistemas.*;

public class AgenteVida extends AgenteInteligente {

    // Construtor.
    public AgenteVida(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ, ControleMovimento controleMovimento, GerenciadorSensores gerenciadorSensores, ModuloComunicacao moduloComunicacao){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ, controleMovimento, gerenciadorSensores, moduloComunicacao);
    }

    // Obtém a descrição desse robô.
    @Override
    public String getDescricao(){return "Agente (imortal) que atua na recuperação da vida de outros robôs, pode realizar missões do tipo MissaoReviver e MissaoContactarCuradores";}

    // Atribui uma nova missão ao robô.
    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException, VidaNulaException{
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O " + this.getNome() + " está desligado");
        
        // Se a excessão não for lançada, executa a tarefa
        Missao novaMissao;
        if (caso == 1){ // Missao = MissaoReviver
            novaMissao = new MissaoReviver();
        } else { // Missao = MissaoContactarCuradores
            novaMissao = new MissaoContactarCuradores();
        }
        definirMissao(novaMissao);
    }

    // Realiza a missão já atribuida ao robô.
    @Override
    public void executarMissao(Ambiente ambiente, Arquivo arquivo, Scanner entrada){
        String mensagem;
        ArrayList<Entidade> robosEmAlcance = gerenciadorSensores.utilizarSensorRobos(ambiente, this, 30);

        if (missao == null){
            mensagem = "Nenhuma missão foi atribuida ao agente, por favor tente novamente após realizar uma atribuição\n";
            arquivarEPrintar(mensagem, arquivo);

        } else if (missao instanceof MissaoReviver){
            System.out.println("O robô está procurando robôs mortos para revivê-los\n");

            ArrayList<Robo> robosMortos = gerenciadorSensores.encontrarRobosMortos(robosEmAlcance);
     
            if (!robosMortos.isEmpty()){
                missao.executar(this, ambiente, arquivo, entrada);
                moduloComunicacao.comunicarRevividos(ambiente.getCentralComunicacao(), robosMortos, this);

            } else { // robosMortos está vazio
                mensagem = "Não há robôs mortos no raio de alcançe\n";
                arquivarEPrintar(mensagem, arquivo);
            }

        } else { // missao instanceof MissaoContactarCuradores
            System.out.println("O robô está procurando robôs com pouca vida e irá comunicar as suas posições para os curadores próximos\n");

            ArrayList<Robo> robosFracos = gerenciadorSensores.encontrarRobosFracos(robosEmAlcance);
            ArrayList<Robo> robosCuradores = gerenciadorSensores.encontrarRobosCuradores(robosEmAlcance);

            if ((!robosFracos.isEmpty()) && (!robosCuradores.isEmpty())){
                mensagem = "Os robôs curadores próximos foram alertados sobre os seguintes robôs com pouca vida:";
                arquivarEPrintar(mensagem, arquivo);
                missao.executar(this, ambiente, arquivo, entrada);

            } else if (robosFracos.isEmpty()){
                mensagem = "Não há robôs com pouca vida no raio de alcançe\n";
                arquivarEPrintar(mensagem, arquivo);

            } else { // robosCuradores está vazio
                mensagem = "Não há robôs curadores para serem alertados no raio de alcançe\n";
                arquivarEPrintar(mensagem, arquivo);
            }
        }
    } 
}