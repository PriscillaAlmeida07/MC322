
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

        roboCavador1.setDirecao(deltaX, deltaY);
        roboCavador1.setVelocidade(velocidade);
        roboCavador1.mover(deltaX, deltaY);
                
        System.out.print("Informe quantos metros o robo cavará:");
        int deltaZ = entrada.nextInt();
        roboCavador1.cavar(deltaZ);

        validarMovimentoCavador(ambiente1, roboCavador1, deltaX, deltaY, deltaZ);

        System.out.println("\n");
    }

    // Verifica se o RoboCavador está dentro dos limites, se não estiver, move ele para a sua posição válida anterior
    private static void validarMovimentoCavador(Ambiente ambiente1, RoboCavador robo, int deltaX, int deltaY, int deltaZ){

        int[] posicao = new int[3];
        posicao = robo.getPosicao();

        if ((ambiente1.dentroDosLimites(posicao[0], posicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(posicao[0], posicao[1]) == 1)){
            System.out.println("O robô movimentado está atualmente na posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ")\n");
        
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O robô movimentado tentou sair do ambiente, ou colidiu com algum obstáculo, logo ele retornou para a posição: ");
            robo.mover(-deltaX, -deltaY);
            robo.setProfundidade(deltaZ); // Redefine a profundidade, pois ele nao vai conseguir perfurar
            posicao = robo.getPosicao();
            System.out.println("(" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ")\n");
        }
    }

    private static void movimentarObstaculoTerrestre(Scanner entrada, RoboObstaculoTerrestre roboObstaculoTerrestre1, Ambiente ambiente1){

        System.out.print("Informe quantos metros o robo Obstaculo Terrestre irá mover:\n" +"Na direcao x:");
        int deltaX = entrada.nextInt();

        System.out.print("Na direcao y:");
        int deltaY = entrada.nextInt();

        System.out.print("Informe a velocidade:");
        int velocidade = entrada.nextInt();

        roboObstaculoTerrestre1.setDirecao(deltaX, deltaY);
        roboObstaculoTerrestre1.setVelocidade(velocidade);
        roboObstaculoTerrestre1.mover(deltaX, deltaY);

        if(validarMovimentoObstaculoTerrestre(ambiente1, roboObstaculoTerrestre1, deltaX, deltaY) == 1){
            System.out.print("O robo soltará um bloco na posição?\nSe sim, digite 1, se não digite 0: ");
            int condicao = entrada.nextInt();
            if(condicao == 1){
                roboObstaculoTerrestre1.soltarBlocos();
                System.out.print("O robo soltou um bloco");
            }
            System.out.println("\n");
        }
    }
    // Esse metodo verifica se o roboObstaculoTerrestre1 esta dentro dos limites, se não estiver move o robo para a ultima posição que ele estava
    private static int validarMovimentoObstaculoTerrestre(Ambiente ambiente1, RoboObstaculoTerrestre robo, int deltaX, int deltaY){

        int[] posicao = new int[2];
        posicao = robo.getPosicao();

        if ((ambiente1.dentroDosLimites(posicao[0], posicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(posicao[0], posicao[1]) == 1)){
            System.out.println("O robô movimentado está atualmente na posição: (" + posicao[0] + "," + posicao[1] + ")\n");
            return 1;
       
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O robô movimentado tentou sair do ambiente, ou colidiu com algum obstáculo, logo ele retornou para a posição: ");
            robo.mover(-deltaX, -deltaY);
            posicao = robo.getPosicao();
            System.out.print("(" + posicao[0] + "," + posicao[1] + ")\n\n");
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

        if(voo == 1){
            roboObstaculoAereo1.subir(deltaZ);
        } else if(voo ==2) {
            roboObstaculoAereo1.descer(deltaZ);
        }

        roboObstaculoAereo1.setDirecao(deltaX, deltaY);
        roboObstaculoAereo1.mover(deltaX, deltaY);

        if (validarMovimentoObstaculoAereo(ambiente1, roboObstaculoAereo1, deltaX, deltaY, deltaZ) == 1){
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
    private static int validarMovimentoObstaculoAereo(Ambiente ambiente1, RoboObstaculoAereo robo, int deltaX, int deltaY, int deltaZ){

        int[] posicao = new int[3];
        posicao = robo.getPosicao();

        if ((ambiente1.dentroDosLimites(posicao[0], posicao[1]) == 1) && (ambiente1.existeObstaculoAereos(posicao[0], posicao[1], posicao[2]) == 1)){
            System.out.println("O robô movimentado está atualmente na posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ")\n");
            return 1;

        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O robô movimentado tentou sair do ambiente, ou colidiu com algum obstáculo, logo ele retornou para a posição: ");
            robo.mover(-deltaX, -deltaY);
            robo.setAltitude(deltaZ); //Aqui tem algum erro, no teste que eu fiz nao voltou para a posicao em z
            posicao = robo.getPosicao();
            System.out.print("(" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ")\n\n");
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
        if(voo == 1){
            roboFlutuador1.subir(deltaZ);
        }
        else if(voo ==2){
            roboFlutuador1.descer(deltaZ);
        }
        roboFlutuador1.setDirecao(deltaX, deltaY);
        roboFlutuador1.mover(deltaX, deltaY);
        System.out.println("\n");
    }

    private static void mostrarRobos(Ambiente ambiente1){
        ambiente1.getTodosRobos();
    }

    private static void mostrarObstaculos(Ambiente ambiente1){
        ambiente1.getTodosObstaculos();
    }

    private static void sensores(){
        
    }
      

}



// LEMBRAR QUE ESTÁ FALTANDO CONFERIR OS OBSTÁCULOS!!!!!!!!