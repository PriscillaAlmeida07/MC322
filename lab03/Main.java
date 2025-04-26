
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        // Instânciamento do ambiente.
        Ambiente ambiente1 = new Ambiente(); 

        // Instânciamento de alguns obstáculos.
        Obstaculo arvore1 = new Obstaculo(TipoObstaculo.ARVORE, 25, 25, 0);
        Obstaculo arvore2 = new Obstaculo(TipoObstaculo.ARVORE, 25, 75, 0);
        Obstaculo arvore3 = new Obstaculo(TipoObstaculo.ARVORE, 75, 25, 0);
        Obstaculo arvore4 = new Obstaculo(TipoObstaculo.ARVORE, 75, 75, 0);

        TapeteReposicao tapete1 = new TapeteReposicao(20, 20);
        TapeteReposicao tapete2 = new TapeteReposicao(80,80);
        TapeteReposicao tapete3 = new TapeteReposicao(80, 20);
        TapeteReposicao tapete4 = new TapeteReposicao(20, 80);

        ambiente1.adicionarTapete(tapete1);
        ambiente1.adicionarTapete(tapete2);
        ambiente1.adicionarTapete(tapete3);
        ambiente1.adicionarTapete(tapete4);

        // Adicionando obstáculos ao ambiente.
        ambiente1.adicionarObstaculo(arvore1);
        ambiente1.adicionarObstaculo(arvore2);
        ambiente1.adicionarObstaculo(arvore3);
        ambiente1.adicionarObstaculo(arvore4);

        // Instânciamento de todos os robôs.
        RoboCavador roboCavador1 = new RoboCavador("roboCavador1");
        RoboObstaculoTerrestre roboObstaculoTerrestre1 = new RoboObstaculoTerrestre("roboObstaculoTerrestre1");
        RoboObstaculoAereo roboObstaculoAereo1 = new RoboObstaculoAereo("roboObstaculoAereo1");
        RoboFlutuador roboFlutuador1 = new RoboFlutuador("roboFlutuador1");

        // Adicionando robôs ao ambiente.
        ambiente1.adicionarRobo(roboCavador1);
        ambiente1.adicionarRobo(roboObstaculoTerrestre1);
        ambiente1.adicionarRobo(roboObstaculoAereo1);
        ambiente1.adicionarRobo(roboFlutuador1);
        
        // Variáveis de funcionamento do sistema.
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        // Loop de funcionamento do sistema:
        while(continuar){
            System.out.println("Selecione alguma das ações abaixo:\n" +
            "[1] - Movimentar Robos\n" +
            "[2] - Exibir posição dos Robos\n" +
            "[3] - Exibir posição dos Obstáculos\n" +
            "[4] - Utilizar sensores\n" +
            "[0] - Sair do programa\n");
    
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    movimentar(entrada, ambiente1, roboCavador1, roboObstaculoTerrestre1, roboObstaculoAereo1, roboFlutuador1);
                    break;
                case 2:
                    mostrarRobos(ambiente1);
                    break; 
                case 3:
                    mostrarObstaculos(ambiente1);
                    break;  
                case 4:
                    sensores(entrada, ambiente1, roboCavador1, roboObstaculoTerrestre1, roboObstaculoAereo1, roboFlutuador1);
                    break; 
                case 0:
                System.out.println("Saindo...");
                continuar = false;
                    break;    
                default:
                System.out.println("Valor inválido, digite novamente\n");
                    break;
            }
        }
        entrada.close();
    }

    // Escolhemos movimentar algum dos robôs, agora precisamos decidir qual deles:
    private static void movimentar(Scanner entrada, Ambiente ambiente1, RoboCavador roboCavador1, RoboObstaculoTerrestre roboObstaculoTerrestre1, RoboObstaculoAereo roboObstaculoAereo1, RoboFlutuador roboFlutuador1){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione alguma das ações abaixo:\n" +
            "[1] - Movimentar o Robo Cavador\n" +
            "[2] - Movimentar o Robo Obstáculo Terrestre\n" +
            "[3] - Movimentar o Robo Flutuador\n" +
            "[4] - Movimentar o Robo Obstáculo Aéreo\n" +
            "[0] - Voltar\n");
    
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    movimentarCavador(entrada, roboCavador1, ambiente1);
                    break;
                case 2:
                    movimentarObstaculoTerrestre(entrada, roboObstaculoTerrestre1, ambiente1);
                    break; 
                case 3:
                    movimentarFlutuador(entrada, roboFlutuador1, ambiente1);
                    break;  
                case 4:
                    movimentarObstaculoAereo(entrada, roboObstaculoAereo1, ambiente1);
                    break; 
                case 0:
                continuar = false;
                    break;    
                default:
                System.out.println("Valor inválido, digite novamente\n");
                    break;
            }
        }
    }

    // Movimento do robô cavador.
    private static void movimentarCavador(Scanner entrada, RoboCavador roboCavador1, Ambiente ambiente1){

        System.out.print("Informe quantos metros o robo Cavador irá mover:\n" +"Na direcao x: ");
        int deltaX = entrada.nextInt();

        System.out.print("Na direcao y: ");
        int deltaY = entrada.nextInt();

        System.out.print("Informe a velocidade:");
        int velocidade = entrada.nextInt();

        roboCavador1.setVelocidade(velocidade);
        roboCavador1.mover(deltaX, deltaY);
                
        System.out.print("Informe quantos metros o robo cavará:");
        int deltaZ = entrada.nextInt();
        roboCavador1.cavar(deltaZ);
        
        // Se o movimento foi válido o buraco será adicionado ao ambiente
        if (validarMovimentoCavador(ambiente1, roboCavador1, deltaX, deltaY, deltaZ) == 1){
            int[] posicao;
            posicao = roboCavador1.getPosicao();

            Obstaculo buraco = roboCavador1.criarBuraco(posicao[0], posicao[1], posicao[2]);
            if(buraco != null){
                ambiente1.adicionarObstaculo(buraco);
                System.out.print("O robo conseguiu cavar, pois a posição é válida");
            }
        } else{
            System.out.print("O robo não conseguiu cavar, pois a posição não é válida");
        }
        
        System.out.println("\n");
    }

    // Verifica se o RoboCavador está dentro dos limites, se não estiver, move ele para a sua posição válida anterior
    private static int validarMovimentoCavador(Ambiente ambiente1, RoboCavador robo, int deltaX, int deltaY, int deltaZ){

        String direcao;

        int[] posicao = new int[3];
        posicao = robo.getPosicao();

        if ((ambiente1.dentroDosLimites(posicao[0], posicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(posicao[0], posicao[1]) == 1)){
            robo.setDirecao(deltaX, deltaY);
            direcao = robo.getDirecao();
            System.out.println("O robô movimentado está atualmente na posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e virado para o " + direcao + "\n");
            return 1;

        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O robô movimentado tentou sair do ambiente, ou colidiu com algum obstáculo, logo ele retornou para a posição: ");
            robo.mover(-deltaX, -deltaY);
            robo.setProfundidade(deltaZ); // Redefine a profundidade, pois ele nao vai conseguir perfurar
            posicao = robo.getPosicao();
            direcao = robo.getDirecao();
            System.out.println("(" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e voltado para o " + direcao + "\n");
            return 0;
        }
    }

    private static void movimentarObstaculoTerrestre(Scanner entrada, RoboObstaculoTerrestre roboObstaculoTerrestre1, Ambiente ambiente1){

        System.out.print("Informe quantos metros o robo Obstaculo Terrestre irá mover:\n" +"Na direcao x:");
        int deltaX = entrada.nextInt();

        System.out.print("Na direcao y:");
        int deltaY = entrada.nextInt();

        System.out.print("Informe a velocidade:");
        int velocidade = entrada.nextInt();

        roboObstaculoTerrestre1.setVelocidade(velocidade);
        roboObstaculoTerrestre1.mover(deltaX, deltaY);

        if(validarMovimentoObstaculoTerrestre(ambiente1, roboObstaculoTerrestre1, deltaX, deltaY) == 1){
            System.out.print("O robo soltará um bloco na posição?\nSe sim, digite 1, se não digite 0: ");
            int condicao = entrada.nextInt();
            if(condicao == 1){
                int[] posicao;
                posicao = roboObstaculoTerrestre1.getPosicao();

                Obstaculo bloco = roboObstaculoTerrestre1.soltarBlocos(posicao[0], posicao[1]);
                if(bloco != null){
                    ambiente1.adicionarObstaculo(bloco);
                    System.out.print("O robo soltou um bloco");
                }
            }
            System.out.println("\n");
        }
    }
    
    // Esse metodo verifica se o roboObstaculoTerrestre1 esta dentro dos limites, se não estiver move o robo para a ultima posição que ele estava
    private static int validarMovimentoObstaculoTerrestre(Ambiente ambiente1, RoboObstaculoTerrestre robo, int deltaX, int deltaY){

        String direcao;

        int[] posicao = new int[2];
        posicao = robo.getPosicao();

        if ((ambiente1.dentroDosLimites(posicao[0], posicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(posicao[0], posicao[1]) == 1)){
            robo.setDirecao(deltaX, deltaY);
            direcao = robo.getDirecao();
            System.out.println("O robô movimentado está atualmente na posição: (" + posicao[0] + "," + posicao[1] + ") e virado para o " + direcao + "\n");
            return 1;
       
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O robô movimentado tentou sair do ambiente, ou colidiu com algum obstáculo, logo ele retornou para a posição: ");
            robo.mover(-deltaX, -deltaY);
            posicao = robo.getPosicao();
            direcao = robo.getDirecao();
            System.out.print("(" + posicao[0] + "," + posicao[1] + ") e voltado para o " + direcao + "\n\n");
            return 0;
        }
    }

    private static void movimentarObstaculoAereo(Scanner entrada, RoboObstaculoAereo roboObstaculoAereo1, Ambiente ambiente1){

        System.out.print("Informe quantos metros o robo Obstaculo Aereo irá mover:\n" +"Na direcao x:");
        int deltaX = entrada.nextInt();

        System.out.print("Na direcao y:");
        int deltaY = entrada.nextInt();

        System.out.print("O robo subirá (digite 1) ou descerá (digite 2):");
        int voo = entrada.nextInt();
        System.out.print("Quantos metros:");
        int deltaZ = entrada.nextInt();

        int caso = 0;
        if(voo == 1){
            roboObstaculoAereo1.subir(deltaZ);
            caso = 1;
        } 
        else if (voo == 2) {
            roboObstaculoAereo1.descer(deltaZ);
            caso = 2;
        }

        roboObstaculoAereo1.mover(deltaX, deltaY);

        if (validarMovimentoObstaculoAereo(ambiente1, roboObstaculoAereo1, deltaX, deltaY, deltaZ, caso) == 1){
            System.out.print("O robo soltará uma nuvem na posição?\nSe sim, digite 1, se não digite 0: ");
            int condicao = entrada.nextInt();

            if (condicao == 1){
                int[] posicao;
                posicao = roboObstaculoAereo1.getPosicao();

                Obstaculo nuvem = roboObstaculoAereo1.soltarNuvens(posicao[0], posicao[1], posicao[2]);
                if(nuvem != null){
                    ambiente1.adicionarObstaculo(nuvem);
                    System.out.print("O robo soltou uma nuvem");
                }

            }
            System.out.println("\n");
        }
    }

    // Esse metodo verifica se o roboObstaculoAereo1 esta dentro dos limites, se não estiver move o robo para a ultima posição que ele estava
    private static int validarMovimentoObstaculoAereo(Ambiente ambiente1, RoboObstaculoAereo robo, int deltaX, int deltaY, int deltaZ, int caso){

        String direcao;

        int[] posicao = new int[3];
        posicao = robo.getPosicao();

        // Verifica se foi digitado corretamente (1 ou 2), que significa que ele subiu ou desceu. Senão foi, o robo nao se move
        if (caso != 1 && caso != 2){
            robo.mover(-deltaX, -deltaY);
            posicao = robo.getPosicao();
            System.out.print("Você inseriu um valor inválido logo o robo permanece na posição (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ")\n\n");
            return 0;
        }

        else if ((ambiente1.dentroDosLimites(posicao[0], posicao[1]) == 1) && (ambiente1.existeObstaculoAereos(posicao[0], posicao[1], posicao[2]) == 1)){
            robo.setDirecao(deltaX, deltaY);
            direcao = robo.getDirecao();
            System.out.println("O robô movimentado está atualmente na posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e virado para o " + direcao + "\n");
            return 1;

        }         
        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O robô movimentado tentou sair do ambiente, ou colidiu com algum obstáculo, logo ele retornou para a posição: ");
            robo.mover(-deltaX, -deltaY);
            robo.setAltitude(deltaZ, caso); //Aqui tem algum erro, no teste que eu fiz nao voltou para a posicao em z
            posicao = robo.getPosicao();
            direcao = robo.getDirecao();
            System.out.print("(" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e voltado para o " + direcao + "\n\n");
            return 0;
        }
    }

    private static void movimentarFlutuador(Scanner entrada, RoboFlutuador roboFlutuador1, Ambiente ambiente1){

        System.out.print("Informe quantos metros o robo Flutuador irá mover:\n" +"Na direcao x:");
        int deltaX = entrada.nextInt();

        System.out.print("Na direcao y:");
        int deltaY = entrada.nextInt();

        System.out.print("O robo subirá (digite 1) ou descerá (digite 2):");
        int voo = entrada.nextInt();
        System.out.print("Quantos metros:");
        int deltaZ = entrada.nextInt();

        roboFlutuador1.setDirecao(deltaX, deltaY);
        roboFlutuador1.mover(deltaX, deltaY);

        int caso = 0;
        if(voo == 1){
            roboFlutuador1.subir(deltaZ);
            caso = 1;
           
        }
        else if(voo == 2){
            roboFlutuador1.descer(deltaZ);
            caso = 2;
        }

        validarMovimentoFlutuador(ambiente1, roboFlutuador1, deltaX, deltaY, deltaZ, caso);
        System.out.println("\n");
    }

    private static void validarMovimentoFlutuador(Ambiente ambiente1, RoboFlutuador robo, int deltaX, int deltaY, int deltaZ, int caso){

        String direcao;

        int[] posicao = new int[3];
        posicao = robo.getPosicao();

        // Verifica se foi digitado corretamente (1 ou 2), que significa que ele subiu ou desceu. Senão foi, o robo nao se move
        if (caso != 1 && caso != 2){
            robo.mover(-deltaX, -deltaY);
            posicao = robo.getPosicao();
            System.out.print("Você inseriu um valor inválido logo o robo permanece na posição (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ")\n\n");
        }

        if ((ambiente1.dentroDosLimites(posicao[0], posicao[1]) == 1) && (ambiente1.existeObstaculoAereos(posicao[0], posicao[1], posicao[2]) == 1)){
            robo.setDirecao(deltaX, deltaY);
            direcao = robo.getDirecao();
            System.out.println("O robô movimentado está atualmente na posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e virado para o " + direcao + "\n");
        
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O robô movimentado tentou sair do ambiente, ou colidiu com algum obstáculo, logo ele retornou para a posição: ");
            robo.mover(-deltaX, -deltaY);
            robo.setAltitude(deltaZ, caso);
            posicao = robo.getPosicao();
            direcao = robo.getDirecao();
            System.out.print("(" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e voltado para o " + direcao + "\n\n");
        }
    }

    private static void mostrarRobos(Ambiente ambiente1){
        ambiente1.getTodosRobos();
    }

    private static void mostrarObstaculos(Ambiente ambiente1){
        ambiente1.getTodosObstaculos();
    }

    private static void sensores(Scanner entrada, Ambiente ambiente1, RoboCavador roboCavador1, RoboObstaculoTerrestre roboObstaculoTerrestre1, RoboObstaculoAereo roboObstaculoAereo1, RoboFlutuador roboFlutuador1){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione o robô que você deseja monitorar:\n" +
            "[1] - Monitorar o Robo Cavador\n" +
            "[2] - Monitorar o Robo Obstáculo Terrestre\n" +
            "[3] - Monitorar o Robo Flutuador\n" +
            "[4] - Monitorar o Robo Obstáculo Aéreo\n" +
            "[0] - Voltar\n");
    
            int opcao = entrada.nextInt();
            int[] vetorPosicao;
            switch (opcao) {
                case 1:
                    vetorPosicao = roboCavador1.getPosicao();
                    roboCavador1.usarSensores(ambiente1, vetorPosicao);
                    break;
                case 2:
                    vetorPosicao = roboObstaculoTerrestre1.getPosicao();
                    roboObstaculoTerrestre1.usarSensores(ambiente1, vetorPosicao);
                    break; 
                case 3:
                    vetorPosicao = roboFlutuador1.getPosicao();
                    roboFlutuador1.usarSensores(ambiente1, vetorPosicao);
                    break;  
                case 4:
                    vetorPosicao = roboObstaculoAereo1.getPosicao();
                    roboObstaculoAereo1.usarSensores(ambiente1, vetorPosicao);               
                    break; 
                case 0:
                continuar = false;
                    break;    
                default:
                System.out.println("Valor inválido, digite novamente\n");
                    break;
            }
        }
    }
      
}



// LEMBRAR QUE ESTÁ FALTANDO CONFERIR OS OBSTÁCULOS!!!!!!!!
// Há algum problema com os robos aereos quando eles passam do ambiente, eles nao voltam para a posicao correta. (o setAltitude esta errado). Acho que acabei de consertar kkkkkk
// precisamos exibir as direcoes e atualiza-las caso ele nao se movemente