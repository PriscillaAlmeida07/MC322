package robo;

import ambiente.Ambiente;
import enums.*;
import exceptions.*;
import interfaces.*;
import java.util.ArrayList;
import java.util.Scanner;
import missao.MissaoSeguranca;
import obstaculos_tapetes.Obstaculo;
import sensores.Sensor;

public class RoboCavador extends RoboTerrestre implements Atacante, DestroiObstaculo { 

    // Robo capaz de ir abaixo do nível do solo.
    private int profundidade;
    private final int dano;

    // Construtor.
    public RoboCavador(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){ 
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
        profundidade = 0;
        dano = 3;
    }

    // Obtém a descrição do robô
    @Override
    public String getDescricao(){return "Robô terrestre que consegue perfurar o solo";}

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

    // A tarefa específica do robô cavador é cavar.
    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException{
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O " + this.getNome() + " está desligado");
        cavar(entrada, ambiente);
    }

    // Move o robô cavador negativamente no eixo Z.
    public void cavar(Scanner entrada, Ambiente ambiente) throws ForaDosLimitesException, ColisaoException{
        System.out.print("Quantos metros o robô cavará? ");
        int deltaZ = entrada.nextInt();

        if (deltaZ < 0){
            System.out.println("Valor invalido digitado");

        } else {
            // Testará se o robô cavará sem ultrapassar o limite do ambiente
            ambiente.dentroDosLimites(getX(), getY(), getZ() - deltaZ, "Tentativa de cavar além do que o solo permite");

            // Se forem válidas, veremos se já há algum buraco na posição
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

    // Verificamos qual é o obstaculo mais próximo ao robô cavador que será destruido.
    @Override
    public void getObstaculoMaisProx(Ambiente ambiente) throws RoboDesligadoException {
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O robô está desligado\n");

        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> obstaculos = getSensorObstaculos().monitorar(ambiente, vetorPosicao, 1);

        if (obstaculos.isEmpty()) // Se nenhum obstaculo estiver prox (dentro do raio)
            System.out.println("Nenhum obstáculo encontrado próximo ao destruidor\n");

        else {
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

    // Ataca todos os robôs próximos (menos ele mesmo).
    @Override
    public void atacar(Ambiente ambiente) throws RoboDesligadoException, VidaNulaException, AreaProtegidaException {
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O robô está desligado");
        if (this.getVida() == 0)
            throw new VidaNulaException("O " + this.getNome() + " está morto, portanto só poderá realizar ações quando for revivido por um agente");

        ArrayList<AgenteInteligente> segurancas = ambiente.getArraySeguranca();
        for (int i = 0; i < segurancas.size(); i++){
            if (segurancas.get(i).getMissao() instanceof MissaoSeguranca missaoSeguranca){
                if (Math.sqrt(Math.pow((segurancas.get(i).getX() - this.getX()), 2)) + (Math.pow((segurancas.get(i).getY() - this.getY()), 2)) + (Math.pow((segurancas.get(i).getZ() - this.getZ()), 2)) < missaoSeguranca.getRaio()){
                    throw new AreaProtegidaException("O " + this.getNome() + " está em uma área protegida pelo " + segurancas.get(i).getNome() + " e não pode atacar\n");
                }
            }
        }
            
        // Informações necessárias para o funcionamento da função:
        Sensor sensor = getSensorRobos();
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = sensor.monitorar(ambiente, vetorPosicao, 1);

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo && !(robo instanceof AgenteInteligente)){ // ja garante que nao atacara agentes inteligentes
                if (!robo.getID().equals(this.getID())){

                    if (robo instanceof AgenteInteligente){
                        System.out.println("O " + robo.getNome() + " está no raio de alcançe, porém agentes não podem ser curados/atacados");

                    } else if (robo.getVida() == 0) {
                        System.out.println("O " + robo.getNome() + " não pode ser atacado, pois já está morto");

                    } else if ((robo.getVida() - dano) <= 0){
                        robo.setVida(-robo.getVida());
                        robo.desligar(); // desligamos o robô quando ele morre
                        System.out.println("O " + this.getNome() + " matou o " + robo.getNome());
                        
                    } else {
                        robo.setVida(-dano);
                        System.out.println("O " + this.getNome() + " atacou o " + robo.getNome() + " que possui agora " + robo.getVida() + "/10 de vida");
                    }
                }
            }
        }

        if (robos.isEmpty()){
            System.out.println("Nenhum robô no raio de alcançe para o ataque");
        }
        System.out.print("\n");
    }
}
