package robo;

import java.util.ArrayList;
import java.util.Scanner;

import ambiente.Ambiente;
import enums.EstadoRobo;
import enums.TipoObstaculo;
import exceptions.ColisaoException;
import exceptions.ForaDosLimitesException;
import exceptions.RoboDesligadoException;
import exceptions.VidaNulaException;
import interfaces.Atacante;
import interfaces.DestroiObstaculo;
import interfaces.Entidade;
import obstaculos_tapetes.Obstaculo;
import sensores.Sensor;

public class RoboCavador extends RoboTerrestre implements Atacante, DestroiObstaculo { 

    // Atributos:
    // retirei a profundidade maxima pois nao é mais necessaria pq ela é a profundidade max do ambiente
    private int profundidade;
    private final int dano;

    // Construtor.
    public RoboCavador(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){ 
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
        profundidade = 0;
        dano = 3;
    }
    
    // Verificamos qual é o obstaculo mais proximo ao robo cavador que será destruido
    @Override
    public void getObstaculoMaisProx(Ambiente ambiente) throws RoboDesligadoException{
        if(this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O robô está desligado\n");
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> obstaculos = getSensorObstaculos().monitorar(ambiente, vetorPosicao, 1);
        if(obstaculos.isEmpty()) // Se nenhum obstaculo estiver prox (dentro do raio)
            System.out.println("Nenhum obstáculo encontrado próximo ao destruidor\n");
        else{
            Obstaculo obstaculoMaisProx = null;
            double menorDist = 200;
            for (int i = 0; i < obstaculos.size(); i++){
                if (obstaculos.get(i) instanceof Obstaculo obstaculo){
                    int dx = obstaculo.getX() - this.getX();
                    int dy = obstaculo.getY() - this.getY();
                    double distancia = Math.sqrt(dx * dx + dy * dy);
                    if (distancia < menorDist) {
                        menorDist = distancia;
                        obstaculoMaisProx = obstaculo;
                    }
                }
            }

            if (obstaculoMaisProx != null) {
                System.out.println("O obstáculo mais próximo ("+ obstaculoMaisProx.getTipoObstaculo().getNome() + ") foi destruído\n");
                ambiente.removerEntidade(obstaculoMaisProx);
            }
        }
    }
    // Obtém a profundidade do robô.
    public int getProfundidade(){
        return profundidade;
    }

    // Define a profundidade do robô.
    public void setProfundidade(int deltaZ){
        profundidade -= deltaZ;
    }

    // Obtém o dano que o robô cavador é capaz de realizar.
    @Override
    public int getDano(){
        return dano;
    }

    // Executa uma tarefa inerente ao Robô Cavador, no caso cavar
    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException{
        if(this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O " + this.getNome() + " está desligado");
        cavar(entrada, ambiente);
    }

    public void cavar(Scanner entrada, Ambiente ambiente) throws ForaDosLimitesException, ColisaoException{
        System.out.print("Quantos metros o robo cavará:");
        int deltaZ = entrada.nextInt();
        if(deltaZ < 0){
            System.out.println("Valor invalido digitado");
        } else {
            // Testará se o robô cavará sem ultrapassar o limite do ambiente
            ambiente.dentroDosLimites(getX(), getY(), getZ() - deltaZ, "Tentativa de cavar além do que o solo permite");
            // Se forem validas, veremos se já ha algum buraco na posição
            ambiente.verificarColisoes(getX(), getY(), getZ() - deltaZ, "Tentativa de cavar o solo já perfurado anteriormente");
            Obstaculo buraco = criarBuraco(getX(), getY(), getZ() - deltaZ);
            ambiente.adicionarEntidade(buraco);
            System.out.println("O buraco foi escavado: (" + buraco.getX() + "," + buraco.getY() +"," + buraco.getZObstaculo() + ")");
        }
    }

    // Cria um novo buraco na posição.
    public Obstaculo criarBuraco(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo buraco = new Obstaculo(TipoObstaculo.BURACO, posicaoX, posicaoY, posicaoZ);
        // Define a profundidade do buraco para poder imprimir corretamente no mapa 
        buraco.getTipoObstaculo().setAltura(posicaoZ);
        return buraco;
    }

    // Ataca todos os robôs próximos (menos ele mesmo).
    @Override
    public void atacar(Ambiente ambiente) throws RoboDesligadoException, VidaNulaException {
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O robô está desligado");
        if (this.getVida() == 0)
            throw new VidaNulaException("O " + this.getNome() + " está morto, portanto só poderá realizar ações quando for curado por outro robô");

        // Informações necessárias para o funcionamento da função:
        Sensor sensor = getSensorRobos();
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = sensor.monitorar(ambiente, vetorPosicao, 1);

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo){
                if (!robo.getID().equals(this.getID())){

                    if (robo.getVida() == 0) {
                        System.out.println("O " + robo.getNome() + " não pode ser atacado, pois já está morto");
                    } else if ((robo.getVida() - dano) <= 0){
                        robo.setVida(-robo.getVida());
                        System.out.println("O " + this.getNome() + " matou o " + robo.getNome());
                    } else {
                        robo.setVida(-dano);
                        System.out.println("O " + this.getNome() + " atacou o " + robo.getNome() + " que possui agora " + robo.getVida() + "/10 de vida");
                    }
                }
            }
        }
        System.out.print("\n");
    }

    // Descrição do Robo Cavador:
    @Override
    public String getDescricao(){return "Robô terrestre que consegue perfurar o solo";}

}
