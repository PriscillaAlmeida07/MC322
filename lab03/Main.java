
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        // Instânciamento do ambiente.
        Ambiente ambiente1 = new Ambiente(); 

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

        // Essas variaveis serão usadas em todos os casos de teste:
        String nomeRobo;
        String direcao;
        int[] vetorPosicao;
        
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        while(continuar){
            System.out.print("Selecione alguma das ações abaixo: \n" +
            "[1] - Movimentar Robos \n" +
            "[2] - Exibir posição dos Robos \n" +
            "[3] - Exibir posição dos Obstáculos  \n" +
            "[4] - Utilizar sensores  \n" +
            "[0] - Sair do programa \n\n ");
    
            int opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    movimentar(entrada);
                    break;
                case 2:
                    mostrarRobos();
                    break; 
                case 3:
                    mostrarObstaculos();
                    break;  
                case 4:
                    sensores();
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


    /*  
        // Testes do roboCavador1:
        nomeRobo = roboCavador1.getNome();
        vetorPosicao = roboCavador1.getPosicao();
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ",0)\n\n");

        // Teste 1: Robo está dentro dos limites, com velocidade válida e a profundidade perfurada é válida.
        System.out.print("Teste de movimento: (+50,+50,-50)\n\n");
        int deltaX = 50; 
        int deltaY = 50; 
        int deltaZ = 50;

        roboCavador1.setVelocidade(20);
        System.out.println("20 é um valor válido de velocidade");

        roboCavador1.setDirecao(deltaX, deltaY);
        direcao = roboCavador1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);
        
        roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Perfuração válida
        vetorPosicao = roboCavador1.getPosicao();
        vetorPosicao = roboCavador1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        validarMovimentoCavador(ambiente1, vetorPosicao, nomeRobo, roboCavador1, deltaX, deltaY, deltaZ);

        // Teste 2: Robo está dentro dos limites, velocidade superior a permitida e a profundidade perfurada não é válida.
        System.out.print("Teste de movimento: (-1,-1,-51)\n\n");
        deltaX = -1;
        deltaY = -1;
        deltaZ = 51;

        roboCavador1.setVelocidade(51);
        System.out.println("50 é um valor inválido de velocidade");

        roboCavador1.setDirecao(deltaX, deltaY);
        direcao = roboCavador1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Perfuração inválida.
        vetorPosicao = roboCavador1.getPosicao();
        vetorPosicao = roboCavador1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        validarMovimentoCavador(ambiente1, vetorPosicao, nomeRobo, roboCavador1, deltaX, deltaY, deltaZ);
    
        // Teste 3: Robo não está dentro dos limites, velocidade válida e a profundidade perfurada é válida.
        System.out.print("Teste de movimento: (1,1,-30)\n\n");
        deltaX = 1;
        deltaY = 1;
        deltaZ = 30;

        roboCavador1.setVelocidade(25);
        System.out.println("25 é um valor válido de velocidade");

        roboCavador1.setDirecao(deltaX, deltaY);
        direcao = roboCavador1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Perfuração inválida.
        vetorPosicao = roboCavador1.getPosicao();
        vetorPosicao = roboCavador1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        validarMovimentoCavador(ambiente1, vetorPosicao, nomeRobo, roboCavador1, deltaX, deltaY, deltaZ);

        // Testes do roboObstaculoTerrestre1:
        nomeRobo = roboObstaculoTerrestre1.getNome();
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");

        // Teste 1: Deposito de um bloco em uma posição válida
        int bloco = roboObstaculoTerrestre1.soltarBlocos();
        existeBloco(ambiente1, vetorPosicao, bloco);

        // Teste 2: Robo não consegue depositar mais blocos, pois a quantitade foi esgotada.
        bloco = roboObstaculoTerrestre1.soltarBlocos();
        existeBloco(ambiente1, vetorPosicao, bloco);

        // Teste 3: Robo está dentro dos limites, com velocidade válida e se move para uma posição válida sem obstáculos
        System.out.print("Teste de movimento: (+20,+20)\n\n");
        deltaX = 20;
        deltaY = 20;

        roboObstaculoTerrestre1.setVelocidade(25);
        System.out.println("25 é um valor válido de velocidade");

        roboObstaculoTerrestre1.setDirecao(deltaX, deltaY);
        direcao = roboObstaculoTerrestre1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboObstaculoTerrestre1.mover(deltaX, deltaY);
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();

        validarMovimentoObstaculoTerrestre(ambiente1, vetorPosicao, nomeRobo, roboObstaculoTerrestre1, deltaX, deltaY);

        // Teste 4: Robo está dentro dos limites, com velocidade válida e se move para uma posição inválida com obstáculos
        System.out.print("Teste de movimento: (-20,-20)\n\n");
        deltaX = -20;
        deltaY = -20;

        System.out.println("25 é um valor válido de velocidade");

        roboObstaculoTerrestre1.setDirecao(deltaX, deltaY);
        direcao = roboObstaculoTerrestre1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboObstaculoTerrestre1.mover(deltaX, deltaY);
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();

        validarMovimentoObstaculoTerrestre(ambiente1, vetorPosicao, nomeRobo, roboObstaculoTerrestre1, deltaX, deltaY);

        // Testes do roboObstaculoAereo1
        nomeRobo = roboObstaculoAereo1.getNome();
        vetorPosicao = roboObstaculoAereo1.getPosicao();
        vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n\n");


        // Teste 1: Robo está dentro dos limites e sobe para uma posição válida sem obstáculos
        System.out.print("Teste de movimento: (+20,+15,+15)\n\n");
        deltaX = 20;
        deltaY = 15;
        deltaZ = 15;

        roboObstaculoAereo1.setDirecao(deltaX, deltaY);
        direcao = roboObstaculoAereo1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboObstaculoAereo1.mover(deltaX, deltaY);
        roboObstaculoAereo1.subir(deltaZ);
        vetorPosicao = roboObstaculoAereo1.getPosicao();
        vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        validarMovimentoObstaculoAereo(ambiente1, vetorPosicao, nomeRobo, roboObstaculoAereo1, deltaX, deltaY, deltaZ);

        // Teste 2: Robo deposita uma nuvem em sua posicao
        int nuvem = roboObstaculoAereo1.soltarNuvens();
        existeNuvem(ambiente1, vetorPosicao, nuvem);

        // Teste 3: Robo não consegue depositar mais nuvens, pois a quantitade foi esgotada.
        nuvem = roboObstaculoAereo1.soltarNuvens();
        existeNuvem(ambiente1, vetorPosicao, nuvem);

        // Teste 4: Robo está dentro dos limites e sobe para uma posição válida sem obstáculos
        System.out.print("Teste de movimento: (+30,+30,+65)\n\n");
        deltaX = 30;
        deltaY = 30;
        deltaZ = 65;

        roboObstaculoAereo1.setDirecao(deltaX, deltaY);
        direcao = roboObstaculoAereo1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboObstaculoAereo1.mover(deltaX, deltaY);
        roboObstaculoAereo1.subir(deltaZ);
        vetorPosicao = roboObstaculoAereo1.getPosicao();
        vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        validarMovimentoObstaculoAereo(ambiente1, vetorPosicao, nomeRobo, roboObstaculoAereo1, deltaX, deltaY, deltaZ);

        // Teste 5: Robo esta dentro dos limites e desce para colidir com a nuvem
        System.out.print("Teste de movimento: (-30,-30,-65)\n\n");
        deltaX = -30;
        deltaY = -30;
        deltaZ = 65; // O valor é positivo pois há um metodo distinto para descer

        roboObstaculoAereo1.setDirecao(deltaX, deltaY);
        direcao = roboObstaculoAereo1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboObstaculoAereo1.mover(deltaX, deltaY);
        roboObstaculoAereo1.descer(deltaZ);
        vetorPosicao = roboObstaculoAereo1.getPosicao();
        vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        validarMovimentoObstaculoAereo(ambiente1, vetorPosicao, nomeRobo, roboObstaculoAereo1, deltaX, deltaY, -deltaZ);
        
        // Testes do roboFlutuador1
        nomeRobo = roboFlutuador1.getNome();
        vetorPosicao = roboFlutuador1.getPosicao();
        vetorPosicao = roboFlutuador1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n\n");

        // Teste 1: Robo está dentro dos limites, com velocidade de subida válida e subirá para uma posição válida sem obstáculos
        System.out.print("Teste de movimento: (+10,+20,+3)\n\n");
        deltaX = 10;
        deltaY = 20;
        deltaZ = 3;

        roboFlutuador1.setDirecao(deltaX, deltaY);
        direcao = roboFlutuador1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboFlutuador1.mover(deltaX, deltaY);
        roboFlutuador1.subir(deltaZ);
        vetorPosicao = roboFlutuador1.getPosicao();
        vetorPosicao = roboFlutuador1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        validarMovimentoFlutuador(ambiente1, vetorPosicao, nomeRobo, roboFlutuador1, deltaX, deltaY, deltaZ, "subir");
        
        // Teste 2: Robo esta dentro dos limites, com velocidade de descida inválida e descerá para uma posição válida sem obstáculos
        System.out.print("Teste de movimento: (-60,-70,-23)\n\n");
        deltaX = -60;
        deltaY = -70;
        deltaZ = 23;  // O valor é positivo pois há um metodo distinto para descer

        roboFlutuador1.setDirecao(deltaX, deltaY);
        direcao = roboFlutuador1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboFlutuador1.mover(deltaX, deltaY);
        roboFlutuador1.descer(deltaZ);
        vetorPosicao = roboFlutuador1.getPosicao();
        vetorPosicao = roboFlutuador1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        validarMovimentoFlutuador(ambiente1, vetorPosicao, nomeRobo, roboFlutuador1, deltaX, deltaY, -deltaZ, "descer");

        // Teste 3: Robo está dentro dos limites, com velocidade de subida inválida e subirá para uma posição inválida sem obstáculos
        System.out.print("Teste de movimento: (+10,-5,+12)\n\n");
        deltaX = 10;
        deltaY = -5;
        deltaZ = 12;

        roboFlutuador1.setDirecao(deltaX, deltaY);
        direcao = roboFlutuador1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboFlutuador1.mover(deltaX, deltaY);
        roboFlutuador1.subir(deltaZ);
        vetorPosicao = roboFlutuador1.getPosicao();
        vetorPosicao = roboFlutuador1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        validarMovimentoFlutuador(ambiente1, vetorPosicao, nomeRobo, roboFlutuador1, deltaX, deltaY, deltaZ, "subir");
    }

    // Esse metodo verifica se o roboCavador1 esta dentro dos limites, se não estiver move o robo para a ultima posição que ele estava
    private static void validarMovimentoCavador(Ambiente ambiente1, int vetorPosicao[], String nomeRobo, RoboCavador robo, int deltaX, int deltaY, int deltaZ){
        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n\n");
        
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente, logo ele retornou para a posição: ");
            robo.mover(-deltaX, -deltaY);
            robo.setProfundidade(deltaZ); // Redefine a profundidade, pois ele nao vai conseguir perfurar pois está em uma posicao invalida
            vetorPosicao = robo.getPosicao();
            vetorPosicao = robo.getPosicao(vetorPosicao[0], vetorPosicao[1]);
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n\n");
        }
    }

    // Esse metodo verifica se o roboObstaculoTerrestre1 esta dentro dos limites, se não estiver move o robo para a ultima posição que ele estava
    private static void validarMovimentoObstaculoTerrestre(Ambiente ambiente1, int vetorPosicao[], String nomeRobo, RoboObstaculoTerrestre robo, int deltaX, int deltaY){
        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
       
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            robo.mover(-deltaX, -deltaY);
            vetorPosicao = robo.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        }
    }

    // Esse metodo verifica se o roboObstaculoAereo1 esta dentro dos limites, se não estiver move o robo para a ultima posição que ele estava
    private static void validarMovimentoObstaculoAereo(Ambiente ambiente1, int vetorPosicao[], String nomeRobo, RoboObstaculoAereo robo, int deltaX, int deltaY, int deltaZ){
        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoAereos(vetorPosicao[0], vetorPosicao[1], vetorPosicao[2]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n\n");
        
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            robo.mover(-deltaX, -deltaY);
            robo.setAltitude(deltaZ);
            vetorPosicao = robo.getPosicao();
            vetorPosicao = robo.getPosicao(vetorPosicao[0], vetorPosicao[1]);
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n\n");
        }
    }

    // Esse metodo verifica se o roboFlutuador1 esta dentro dos limites, se não estiver move o robo para a ultima posição que ele estava    
    private static void validarMovimentoFlutuador(Ambiente ambiente1, int vetorPosicao[], String nomeRobo, RoboFlutuador robo, int deltaX, int deltaY, int deltaZ, String caso){
        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoAereos(vetorPosicao[0], vetorPosicao[1], vetorPosicao[2]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n\n");
        
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            robo.mover(-deltaX, -deltaY);
            robo.setAltitude(deltaZ, caso);
            vetorPosicao = robo.getPosicao();
            vetorPosicao = robo.getPosicao(vetorPosicao[0], vetorPosicao[1]);
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n\n");
        }
    }

    // Esse metodo permite verificar se existe blocos disponiveis para serem adicionados ao ambiente e se nao ha blocos na posicao
    private static void existeBloco(Ambiente ambiente1,int vetorPosicao[], int bloco){
        if (bloco == 1 && (ambiente1.existeObstaculoTerrestres(vetorPosicao[0], vetorPosicao[1]) == 1)){
            ambiente1.adicionarBloco(vetorPosicao);
            System.out.println("Um bloco foi depositado na posição (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")");
        }
    }

    // Esse metodo permite verificar se existe nuvens disponiveis para serem adicionados ao ambiente e se nao ha nuvens na posicao
    private static void existeNuvem(Ambiente ambiente1,int vetorPosicao[], int nuvem){
        if (nuvem == 1 && (ambiente1.existeObstaculoAereos(vetorPosicao[0], vetorPosicao[1], vetorPosicao[2]) == 1)){
            ambiente1.adicionarNuvem(vetorPosicao);
            System.out.println("Uma nuvem foi depositada na posição (" + vetorPosicao[0] + ", " + vetorPosicao[1] +  ", " + vetorPosicao[2] + ")");
        } 
             */  
    }

    private static void movimentar(Scanner entrada){
        boolean continuar = true;

        while(continuar){
            System.out.print("Selecione alguma das ações abaixo: \n" +
            "[1] - Movimentar o Robo Cavador \n" +
            "[2] - Movimentar o Robo Obstáculo Terrestre \n" +
            "[3] - Movimentar o Robo Flutuador  \n" +
            "[4] - Movimentar o Robo Obstáculo Aéreo  \n" +
            "[0] - Voltar \n\n ");
    
            int opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    break; 
                case 3:
                    break;  
                case 4:
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

    private static void mostrarRobos(){
        
    }

    private static void mostrarObstaculos(){
        
    }

    private static void sensores(){
        
    }
        
}