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

    // Obtém a descrição desse robô.
    @Override
    public String getDescricao(){return "Robo aéreo capaz de criar obstáculos posicionando nuvens no céu";}

    // Obtém o dano que o robô realiza ao atacar.
    @Override
    public int getDano(){
        return dano;
    }

    // A tarefa especifica do RobôObstaculoAereo é soltar nuvens.
    @Override
    public void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException, VidaNulaException {
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O " + this.getNome() + " está desligado");
        if (this.getVida() == 10)
            throw new VidaNulaException("O " + this.getNome() + " está morto, portanto só poderá realizar ações quando for revivido por um agente");

        // Realiza a tarefa
        posicaoNuvem(ambiente);
    }

    // Define a posição que a nuvem será posicionada de acordo com a direção do robô.
    public void posicaoNuvem(Ambiente ambiente) throws ForaDosLimitesException, ColisaoException {

        // Caso as nuvens já tenham acabado
        if (numNuvens == 0)
            System.out.println("Não há mais nuvens disponíveis\n");

        else { // Se as nuvens não acabaram
            int x = getX();
            int y = getY();
            int z = getZ();

            switch (getDirecao()) {
                case "Norte" -> x += 1;
                case "Sul" -> x -= 1;
                case "Leste" -> y += 1;
                case "Oeste" -> y -= 1;
                case "Nordeste" -> {
                    x += 1; y += 1;
                }
                case "Noroeste" -> {
                    x += 1; y -= 1;
                }
                case "Sudeste" -> {
                    x -= 1; y += 1;
                }
                case "Sudoeste" -> {
                    x -= 1; y -= 1;
                }
            }

            // Posiciona a nuvem no local encontrado
            soltarNuvem(ambiente, x, y, z);
        }
    }

    // Posiciona uma nuvem no ambiente.
    private void soltarNuvem(Ambiente ambiente, int x, int y, int z) throws ForaDosLimitesException, ColisaoException {
        ambiente.dentroDosLimites(x, y, z, "Erro: Tentativa de colocar a nuvem fora do ambiente");
        ambiente.verificarColisoes(x, y, z, "Erro: Tentativa de colocar a nuvem em uma posição já ocupada");

        Obstaculo nuvem = criarNuvem(x, y, z);
        ambiente.adicionarEntidade(nuvem);
        numNuvens--;
        System.out.println("A nuvem está na posição mínima (" + nuvem.getX() + "," + nuvem.getY() +"," + (nuvem.getZ() - 25) + ") e máxima (" +
                            (nuvem.getX() + nuvem.getTipoObstaculo().getLargura()) + "," + (nuvem.getY() + nuvem.getTipoObstaculo().getComprimento()) + "," + (nuvem.getZ() + nuvem.getTipoObstaculo().getAltura() - 25) + ")");
    }

    // Cria uma nova nuvem na posição.
    private Obstaculo criarNuvem(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo nuvem = new Obstaculo(TipoObstaculo.NUVEM, posicaoX, posicaoY, posicaoZ);
        return nuvem;
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
