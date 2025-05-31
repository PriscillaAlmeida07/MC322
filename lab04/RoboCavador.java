import java.util.ArrayList;
import java.util.Scanner;

public class RoboCavador extends RoboTerrestre implements Atacante, DestroiObstaculos { 

    // Descrição do Robo Cavador:
    @Override
    public String getDescricao(){return "Robô terrestre que consegue perfurar o solo";}

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
    
    @Override
    public void getObstaculoMaisProx(Ambiente ambiente) throws RoboDesligadoException{
        if(this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O robô está desligado\n");
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> obstaculos = getSensorObstaculos().monitorar(ambiente, vetorPosicao, 1);
        if(obstaculos.isEmpty())
            System.out.println("Nenhum obstáculo encontrado próximo ao destruidor");
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
                ambiente.removerEntidade(obstaculoMaisProx);
                System.out.println("O obstáculo mais próximo foi destruído");
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

    // Ataca todos os robôs próximos.
    @Override
    public void atacar(Ambiente ambiente) throws RoboDesligadoException{
        if(this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O robô está desligado\n");
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = getSensorRobos().monitorar(ambiente, vetorPosicao, 2);

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo){
                if(!robo.getID().equals(this.getID())){
                    robo.setVida(-dano);
                    System.out.println("O " + this.getNome() + " atacou o " + robo.getNome() + " que possui agora " + robo.getVida() + " vidas apenas");
                }
            }
        }
        System.out.print("\n");
    }
}
