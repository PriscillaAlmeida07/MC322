
import java.util.ArrayList;
import java.util.Scanner;

public class RoboObstaculoAereo extends RoboAereo implements  Atacante {
        
    // Robô capaz de criar obstáculos posicionando nuvens no céu.
    private int numNuvens;
    private final int dano;

    // Construtor.
    public RoboObstaculoAereo(String nome, String id, EstadoRobo estado){
        super(nome, id, estado);
        numNuvens = 3;
        dano = 2;
    }
    // Descrição do Robo Obstáculo Aéreo:
    @Override
    public String getDescricao(){return "Robo aéreo capaz de criar obstáculos posicionando nuvens no céu";}

    public void  executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY)throws ForaDosLimitesException{
        int deltaZ = 0;
        System.out.print("O robo subirá (digite 1) ou descerá (digite 2): ");
        int voo = entrada.nextInt();
        if ((voo == 1) || (voo == 2)) {
            System.out.print("Quantos metros: ");
            deltaZ = entrada.nextInt();
        }
        
        if (voo == 1){
            super.subir(ambiente, deltaX, deltaY, deltaZ);
        } else if (voo == 2){
            super.descer(ambiente, deltaX, deltaY, deltaZ);
        }

    }

    // Posiciona uma nuvem em sua posição, criando um obstáculo para outros robôs. Adicionaremos ao ambiente na main
    public Obstaculo soltarNuvens(int posicaoX, int posicaoY, int posicaoZ){
        if ((numNuvens == 0) || ((posicaoX > 96) || (posicaoY > 96) || (posicaoZ > 99))){
            System.out.print("Não há mais nuvens disponíveis e/ou posição inválida para adicionar uma nuvem ao ambiente");
            return null;
        } else { // Ainda tem nuvens e a posição é válida.
            Obstaculo nuvem = criarNuvem(posicaoX, posicaoY, posicaoZ);
            numNuvens--;
            return nuvem;
        }
    }

    // Cria uma nova nuvem na posição.
    private Obstaculo criarNuvem(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo nuvem = new Obstaculo(TipoObstaculo.NUVEM, posicaoX, posicaoY, posicaoZ);
        return nuvem;
    }
/* 
    // Conserta a altitude do robô caso ele tenha tentado ir para uma posição inadequada.
    public void setAltitude(int deltaZ, int caso){
        if (caso == 1){ // Ele tentou subir, mas descerá para retornar a posição anterior
            super.setAltitude(deltaZ);

        } else { // (caso == 2) Ele tentou descer, mas subirá para retornar a posição anterior
            super.setAltitude(-deltaZ);
        }
    }
*/
    @Override
    public int getDano(){
        return dano;
    }

    @Override
    public void atacar(Ambiente ambiente){
        Sensor sensor = getSensorRobos();
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = sensor.monitorar(ambiente, vetorPosicao, 1);

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo) {
                robo.setVida(-dano);
            }
        }
    }
}

/*
         System.out.print("O robô soltará uma nuvem na posição?\n" + "Se sim, digite 1, se não digite 0: ");
        int condicao = entrada.nextInt();

        if (condicao == 1){
            int[] posicao = this.getPosicao();
            Obstaculo nuvem = this.soltarNuvens(posicao[0], posicao[1], posicao[2]);

            if(nuvem != null){
                ambiente.adicionarObstaculo(nuvem);
                System.out.print("O robô soltou uma nuvem");
            }

        } else if ((condicao != 1) && (condicao != 0)) {
            System.out.print("Valor inválido inserido, o robô não soltará um bloco");
        }
        System.out.println("\n");
 */