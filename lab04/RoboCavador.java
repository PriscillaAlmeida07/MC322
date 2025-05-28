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

    public void cavar(Scanner entrada, Ambiente ambiente) throws ForaDosLimitesException, ColisaoException{
        System.out.print("Quantos metros o robo cavará:");
        int deltaZ = entrada.nextInt();
        if(deltaZ < 0){
            System.out.println("Valor invalido digitado");
        } else {
            // Colocamos a profundidade igual a 0 (pegando ela com getZ()) apenas para testar se a posição x e y são validas
            ambiente.dentroDosLimites(getX(), getY(), getZ(), "Erro: Tentativa de cavar em uma posição inválida");
            // Se forem validas, veremos se já ha algum buraco na posição
            ambiente.estaOcupado(getX(), getY(), getZ() - deltaZ, "Erro: Tentativa de cavar o solo já perfurado anteriormente");
        Obstaculo buraco = criarBuraco(getX(), getY(), getZ() - deltaZ);
        ambiente.adicionarEntidade(buraco);
        System.out.println("O buraco foi escavado: (" + buraco.getX() + "," + buraco.getY() +"," + buraco.getZ() + ")");
        }
    }

    // Cria um novo buraco na posição.
    public Obstaculo criarBuraco(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo buraco = new Obstaculo(TipoObstaculo.BURACO, posicaoX, posicaoY, posicaoZ);
        // Define a profundidade do buraco para poder imprimir corretamente no mapa 
        buraco.getTipoObstaculo().setAltura(posicaoZ);
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
