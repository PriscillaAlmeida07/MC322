import java.util.ArrayList;
import java.util.Scanner;

public class RoboCavador extends RoboTerrestre implements Atacante { 

    // Descrição do Robo Cavador:
    @Override
    public String getDescricao(){return "Robô terrestre que consegue perfurar o solo";}

    // Atributos:
    private int profundidade;
    private final int profundidadeMaxima;
    private final int dano;

    // Construtor.
    public RoboCavador(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){ 
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);

        profundidade = 0;
        profundidadeMaxima = 50;
        dano = 3;
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

    // Executa uma tarefa inerente ao Robô Cavador.
    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException{
        cavar(entrada, ambiente);
    }

    public void cavar(Scanner entrada,Ambiente ambiente) throws ForaDosLimitesException, ColisaoException{
        System.out.print("Quantos metros o robo cavará:");
        int deltaZ = entrada.nextInt();
        if(deltaZ < 0){
            System.out.println("Valor invalido digitado");
        } else {
            moverPara(0,0, -deltaZ, ambiente);
            System.out.println("O robo cavou "+ deltaZ + " metros\n");
        }
    }

    /* 
    // Método que permite a movimentação abaixo do solo.
    public void cavar(int deltaZ){
        if (deltaZ < 0) {
            System.out.println("Valor inválido de perfuração inserido");
        } else if ((deltaZ + profundidade) > profundidadeMaxima){
            System.out.println(deltaZ + " é um valor inválido de perfuração, pois a profundidade máxima é: " + profundidadeMaxima);
        } else if (deltaZ == 0){
            System.out.println("O robô não cavou");
        } else { // Valor válido de perfuração
            profundidade += deltaZ;
            System.out.println(deltaZ + " é um valor válido de perfuração");
        }
    }
        */

    // Cria um novo buraco na posição.
    public Obstaculo criarBuraco(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo buraco = new Obstaculo(TipoObstaculo.BURACO, posicaoX, posicaoY, posicaoZ);
        return buraco;
    }

    // Ataca todos os robôs próximos.
    @Override
    public void atacar(Ambiente ambiente){
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = getSensorRobos().monitorar(ambiente, vetorPosicao, 2);

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo){
                robo.setVida(-dano);
            }
        }
    }
}
