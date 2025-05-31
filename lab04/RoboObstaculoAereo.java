
import java.util.ArrayList;
import java.util.Scanner;

public class RoboObstaculoAereo extends RoboAereo implements  Atacante {
        
    // Robô capaz de criar obstáculos posicionando nuvens no céu.
    private int numNuvens;
    private final int dano;

    // Construtor.
    public RoboObstaculoAereo(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
        numNuvens = 3;
        dano = 2;
    }

    // Descrição do Robo Obstáculo Aéreo:
    @Override
    public String getDescricao(){return "Robo aéreo capaz de criar obstáculos posicionando nuvens no céu";}

    // A tarefa especifica do RobôObstaculoAereo é soltar nuvens
    public void  executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException{
        soltarNuvens(ambiente);
    }

    // Define a posição que a nuvem será posicionada de acordo com a direção do robô
    public void soltarNuvens(Ambiente ambiente) throws ForaDosLimitesException, ColisaoException{
        if (numNuvens == 0)
            System.out.print("Não há mais nuvens disponíveis");
        else {
            int x = getX();
            int y = getY();
            int z = getZ();

            switch (getDirecao()) {
                case "Norte":     
                    x += 1; 
                    break;
                case "Sul":       
                    x -= 1;
                    break;
                case "Leste":    
                    y += 1; 
                    break;
                case "Oeste":     
                    y -= 1; 
                    break;
                case "Nordeste":  
                    x += 1; y += 1; 
                    break;
                case "Noroeste":  
                    x += 1; y -= 1; 
                    break;
                case "Sudeste":   
                    x -= 1; y += 1; 
                    break;
                case "Sudoeste": 
                    x -= 1; y -= 1; 
                    break;
            }

            PosicionarNuvem(ambiente, x, y, z);
        }
    }

    // Metodo para posicionar a nuvem de acordo com a direção do robô
    private void PosicionarNuvem(Ambiente ambiente, int x, int y, int z) throws ForaDosLimitesException, ColisaoException{
        ambiente.dentroDosLimites(x, y, z, "Erro: Tentativa de colocar a nuvem fora do ambiente");
        ambiente.verificarColisoes(x, y, z, "Erro: Tentativa de colocar a nuvem em uma posição já ocupada");
        Obstaculo nuvem = criarNuvem(x, y, z);
        ambiente.adicionarEntidade(nuvem);
        numNuvens--;
        System.out.println("A nuvem está na posição: (" + nuvem.getX() + "," + nuvem.getY() +"," + nuvem.getZ() + ")");
    }

    // Cria uma nova nuvem na posição.
    private Obstaculo criarNuvem(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo nuvem = new Obstaculo(TipoObstaculo.NUVEM, posicaoX, posicaoY, posicaoZ);
        return nuvem;
    }

    @Override
    public int getDano(){
        return dano;
    }

    @Override
    public void atacar(Ambiente ambiente) throws RoboDesligadoException{
        if(this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O robô está desligado");
        Sensor sensor = getSensorRobos();
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = sensor.monitorar(ambiente, vetorPosicao, 1);

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
