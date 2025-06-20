package robo;

import ambiente.Ambiente;
import comunicacao.CentralComunicacao;
import enums.*;
import exceptions.*;
import interfaces.*;
import java.util.ArrayList;
import java.util.Scanner;
import sensores.*;

public abstract class Robo implements Entidade, Sensoreavel, Comunicavel {

    // Identificação do robô.
    private final String nome, id;

    // Posicionamento do robô.
    private int posicaoX, posicaoY, posicaoZ;
    private String direcao;

    // Outras características.
    private final TipoEntidade tipoEntidade;
    private boolean protegido;
    private EstadoRobo estado;
    private int vida;
    private final ArrayList<Sensor> sensores;
    private final ArrayList<String> mensagens;

    // Construtor.
    public Robo(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){ 

        this.id = id; this.nome = nome; this.estado = estado;
        this.posicaoX = posicaoX; this.posicaoY = posicaoY; this.posicaoZ = posicaoZ;
        direcao = "Norte";
        vida = 10;
        protegido = false;

        if (this instanceof AgenteInteligente)
            tipoEntidade = TipoEntidade.AGENTE;
        else
            tipoEntidade = TipoEntidade.ROBO;
            
        mensagens = new ArrayList<>();

        // Cria o ArrayList de sensores do robô e adiciona os sensores comuns a todos os robôs: obstáculos e robôs
        sensores = new ArrayList<>();
        sensores.add(new SensorObstaculos(40, TipoSensor.OBSTACULOS));
        sensores.add(new SensorRobos(4, TipoSensor.ROBOS));
    }

    // Adiciona um sensor de reposição de blocos, se o robô o possuir.
    public void adicionaSensorReporBlocos(SensorReporBlocos sensorReporBlocos){
        sensores.add(sensorReporBlocos);
    }

    // Obtém o nome do robô.
    public String getNome(){
        return nome;
    }

    // Obtém o ID do robô
    public String getID(){
        return id;
    }

    // Obtém o estado do robô (ligado/desligado).
    public EstadoRobo getEstadoRobo(){
        return estado;
    }

    // Liga o robô.
    public void ligar(){
        estado = EstadoRobo.LIGADO;
    }

    // Desliga o robô.
    public void desligar(){
        estado = EstadoRobo.DESLIGADO;
    }

    // Descobre se o robô está ou não sendo protegido
    public boolean getProtegido(){
        return protegido;
    }

    // Altera o estado de proteção do robô.
    public void setProtegido(){
        protegido = !protegido;
    }

    // Obtém a vida atual do robô.
    public int getVida(){
        return vida;
    }

    // Define a vida do robô.
    public void setVida(int deltaVida){
        vida += deltaVida;
    }

    // Imprime os status atuais do robô.
    public void vizualizarStatus(){
        System.out.println("Informações sobre o " + getNome() + ":\n" +
                            "Estado: " + getEstadoRobo().getString() + "\n" +
                            "Posição: (" + getX() + "," + getY() + "," + getZUsuario() + ")" +
                            "Vida: " + getVida() + "/10\n");
    }

    // Obtém a direção do robô.
    public String getDirecao(){
        return direcao;
    }

    // Obtém a posição (x,y,z) do robô.
    public int[] getPosicao(){
        int[] vetor = new int[3];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;
        vetor[2] = posicaoZ;

        return vetor;
    }
        
    // Descobre e define a direção do robô.
    public void setDirecao(int deltaX, int deltaY){
        if (deltaX > 0){
            if (deltaY > 0){
                direcao = "Nordeste";
            } else if (deltaY == 0){
                direcao = "Leste";
            } else { // deltaY < 0
                direcao = "Sudeste";
            }

        } else if (deltaX == 0){
            if (deltaY > 0){
                direcao = "Norte";
            } else if (deltaY < 0){
                direcao = "Sul";
            }
            
        } else if (deltaX < 0){
            if (deltaY > 0){
                direcao = "Noroeste";
            } else if (deltaY == 0){
                direcao = "Oeste";
            } else { // deltaY < 0
                direcao = "Sudoeste";
            }
        }
    }

    // Realiza um movimento do robô no ambiente.
    public void moverPara(int deltaX, int deltaY, int deltaZ, Ambiente ambiente) throws ForaDosLimitesException, ColisaoException {

        // Primeiro testamos para ver se é uma movimentação valida, ou seja, dentro dos limites e para uma posição não ocupada
        ambiente.dentroDosLimites(deltaX + posicaoX, deltaY + posicaoY, deltaZ + posicaoZ, "O robô tentou sair do ambiente");
        ambiente.verificarColisoes(deltaX + posicaoX, deltaY + posicaoY, deltaZ + posicaoZ, "Esta posição já está ocupada");

        // Se foraDosLimites ou verificarColisoes lançar uma exceção não serão executadas as linhas abaixo
        int[] posicaoAnterior = getPosicao();

        posicaoX += deltaX;
        posicaoY += deltaY;
        posicaoZ += deltaZ;

        // Movendo a entidade no ambiente
        ambiente.moverEntidade(this, posicaoAnterior);
        System.out.println("O "+ this.getNome() + " está na posição: (" + this.getX() + "," + this.getY() + "," + this.getZUsuario() + ")");
    }

    // Todos os robôs devem implementar a função "executarTarefa"
    public abstract void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException, VidaNulaException;

    // Encontra o sensor de robôs no ArrayList de sensores
    public Sensor getSensorRobos(){
        for (int i=0; i< sensores.size(); i++ ){
            if (sensores.get(i).getTipo() == TipoSensor.ROBOS)
                return sensores.get(i);
        }
        return null;
    }

    // Encontra o sensor de obstáculos no ArrayList de sensores
    public Sensor getSensorObstaculos(){
        for (int i=0; i< sensores.size(); i++ ){
            if (sensores.get(i).getTipo() == TipoSensor.OBSTACULOS)
                return sensores.get(i);
        }
        return null;
    }

    // Utiliza e imprime o resultado de todos os sensores do robô.
    @Override
    public void acionarSensores(Ambiente ambiente, int caso) throws RoboDesligadoException, VidaNulaException {
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O " + this.getNome() + " está desligado");
        if (this.getVida() == 0)
            throw new VidaNulaException("O " + this.getNome() + " está morto, portanto só poderá realizar ações quando for revivido por um agente");

        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> resultado;

        for (int i=0; i< sensores.size(); i++ ){
            resultado = sensores.get(i).monitorar(ambiente, vetorPosicao, caso);
            sensores.get(i).imprimirResultado(resultado, vetorPosicao);
        }
    }

    // Envia uma mensagem para outro robô.
    @Override
    public void enviarMensagem(CentralComunicacao centralComunicacao, Comunicavel destinatario, String mensagem) throws ErroComunicacaoException{
        if (destinatario == this)
            throw new ErroComunicacaoException("Tentativa de se comunicar com o próprio robo");

        destinatario.receberMensagem(this.getNome() + ": " + mensagem);
        centralComunicacao.registrarMensagens(this.getNome(), mensagem);
    }

    // Recebe uma mensagem de outro robô (se não estiver desligado).
    @Override
    public void receberMensagem(String mensagem){
        mensagens.add(mensagem);
    }

    // Visualiza as mensagens recebidas.
    @Override
    public void visualizarMensagens(){
        if (mensagens.isEmpty()){
            System.out.println("O robô ainda não recebeu nenhuma mensagem");
        } else { // O robô já recebeu mensagens
            System.out.println("Mensagens recebidas:");
            for (int i = 0; i < mensagens.size(); i++){
                System.out.println(mensagens.get(i));
            }
        }
        System.out.print("\n");
    }

    // Obtém posições do robô:

    @Override
    public int getX(){return posicaoX;}
    @Override
    public int getY(){return posicaoY;}
    @Override
    public int getZ(){return posicaoZ;}

    // Obtém a posição Z para impressão. Ou seja, se o robô estiver no solo será imprimido z= 0
    public int getZUsuario(){
        return (this.getZ() - 25);
    }

    // Obtém informações sobre o robô:

    @Override
    public TipoEntidade getTipo(){return tipoEntidade;}
    @Override
    public abstract String getDescricao();
    @Override
    public char getRepresentacao(){return 'R';}
}
