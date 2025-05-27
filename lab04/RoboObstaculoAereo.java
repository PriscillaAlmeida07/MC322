
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

    public void  executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException{
        // A tarefa especifica do RobôObstaculoAereo é soltar nuvens
        soltarNuvens(ambiente);
    }

    // Posiciona uma nuvem na proxima posição e mesma direção que a dele, criando um obstáculo para outros robôs. Adicionaremos ao ambiente na main
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

    private void PosicionarNuvem(Ambiente ambiente, int x, int y, int z) throws ForaDosLimitesException, ColisaoException{
        ambiente.dentroDosLimites(x, y, z, "Erro: Tentativa de colocar a nuvem fora do ambiente");
        ambiente.estaOcupado(x, y, z, "Erro: Tentativa de colocar a nuvem em uma posição já ocupada");
        Obstaculo nuvem = criarNuvem(x, y, z);
        ambiente.adicionarEntidade(nuvem);
        numNuvens--;
        System.out.println("O robo está na posição: (" + getX() + "," + getY() +"," + getZ() + ") e na direção:" + getDirecao() + " O obj esta na posição: (" + nuvem.getX() + "," + nuvem.getY() +"," + nuvem.getZ() + ")"  );
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
