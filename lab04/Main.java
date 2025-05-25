import java.util.InputMismatchException;
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
        ambiente1.adicionarEntidade(arvore1);
        ambiente1.adicionarEntidade(arvore2);
        ambiente1.adicionarEntidade(arvore3);
        ambiente1.adicionarEntidade(arvore4);

        // Instânciamento de pontos de reposição.
        TapeteReposicao tapete1 = new TapeteReposicao(20, 20);
        TapeteReposicao tapete2 = new TapeteReposicao(80,80);
        TapeteReposicao tapete3 = new TapeteReposicao(80, 20);
        TapeteReposicao tapete4 = new TapeteReposicao(20, 80);

        // Adicionando pontos de reposição ao ambiente.
        ambiente1.adicionarEntidade(tapete1);
        ambiente1.adicionarEntidade(tapete2);
        ambiente1.adicionarEntidade(tapete3);
        ambiente1.adicionarEntidade(tapete4);

        // Instânciamento de todos os robôs.
        RoboCavador roboCavador1 = new RoboCavador("roboCavador1", "RC01", EstadoRobo.LIGADO);
        RoboObstaculoTerrestre roboObstaculoTerrestre1 = new RoboObstaculoTerrestre("roboObstaculoTerrestre1", "ROT01", EstadoRobo.LIGADO);
        RoboFlutuador roboFlutuador1 = new RoboFlutuador("roboFlutuador1", "RF01", EstadoRobo.LIGADO);
        RoboObstaculoAereo roboObstaculoAereo1 = new RoboObstaculoAereo("roboObstaculoAereo1", "ROA01", EstadoRobo.LIGADO);
        RoboCavador roboCavador2 = new RoboCavador("roboCavador2", "RC02", EstadoRobo.DESLIGADO);
        RoboObstaculoTerrestre roboObstaculoTerrestre2 = new RoboObstaculoTerrestre("roboObstaculoTerrestre2", "ROT02", EstadoRobo.DESLIGADO);
        RoboFlutuador roboFlutuador2 = new RoboFlutuador("roboFlutuador2", "RF02", EstadoRobo.DESLIGADO);
        RoboObstaculoAereo roboObstaculoAereo2 = new RoboObstaculoAereo("roboObstaculoAereo2", "ROA02", EstadoRobo.DESLIGADO);

        // Criamos esse robô apenas para testar o metodo removerEntidade
        RoboObstaculoAereo roboObstaculoAereo3 = new RoboObstaculoAereo("roboObstaculoAereo3", "ROA03", EstadoRobo.LIGADO);
        System.out.print("\n");

        // Adicionando robôs ao ambiente.
        ambiente1.adicionarEntidade(roboCavador1);
        ambiente1.adicionarEntidade(roboObstaculoTerrestre1);
        ambiente1.adicionarEntidade(roboFlutuador1);
        ambiente1.adicionarEntidade(roboObstaculoAereo1);
        
        // Testando o metodo removerEntidade
        System.out.println("\nTeste de remoção de um robô:");
        ambiente1.adicionarEntidade(roboObstaculoAereo3);
        ambiente1.removerEntidade(roboObstaculoAereo3);

        // Variáveis de funcionamento do sistema.
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        // Loop de funcionamento do sistema:
        while(continuar){
            System.out.println("Selecione alguma das ações abaixo:\n" +
            "[1] - Selecionar um Robo (funções/interações)\n" +
            "[2] - Listar todos os Robos\n" +
            "[3] - Listar posição dos Objetos (não robôs)\n" +
            "[4] - Exibir plano xy atual do ambiente\n" +
            "[5] - Listar todas as mensagens trocadas\n" +
            "[0] - Sair do programa\n");
    
            int opcao = entrada.nextInt();
            switch (opcao){
                case 1:
                    selecionarRobo(entrada, ambiente1, roboCavador1, roboCavador2, roboObstaculoTerrestre1, roboObstaculoTerrestre2, roboObstaculoAereo1, roboObstaculoAereo2, roboFlutuador1, roboFlutuador2);
                    break;
                case 2:
                    ambiente1.listarTodosRobos();
                    break; 
                case 3:
                    ambiente1.listarObjetos();
                    break;  
                case 4:
                    ambiente1.visualizarAmbiente();
                    break; 
                case 5:
                    // ainda temos que fazer
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
    private static void selecionarRobo(Scanner entrada, Ambiente ambiente1, RoboCavador roboCavador1, RoboCavador roboCavador2, RoboObstaculoTerrestre roboObstaculoTerrestre1, RoboObstaculoTerrestre roboObstaculoTerrestre2, RoboObstaculoAereo roboObstaculoAereo1, RoboObstaculoAereo roboObstaculoAereo2, RoboFlutuador roboFlutuador1, RoboFlutuador roboFlutuador2){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algum dos Robos abaixo:\n" +
            "[1] - Robo Cavador 1\n" +
            "[2] - Robo Cavador 2\n" +
            "[3] - Robo Obstáculo Terrestre 1\n" +
            "[4] - Robo Obstáculo Terrestre 2\n" +
            "[5] - Robo Flutuador 1\n" +
            "[6] - Robo Flutuador 2\n" +
            "[7] - Robo Obstáculo Aéreo 1\n" +
            "[8] - Robo Obstáculo Aéreo 2\n" +
            "[0] - Voltar\n");
    
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    funcoesCavador(entrada, roboCavador1, ambiente1);
                    break;
                case 2:
                    funcoesCavador(entrada, roboCavador2, ambiente1);
                    break; 
                case 3:
                    funcoesObstaculoTerrestre(entrada, roboObstaculoTerrestre1, ambiente1);
                    break; 
                case 4:
                    funcoesObstaculoTerrestre(entrada, roboObstaculoTerrestre2, ambiente1);
                    break;  
                case 5:
                    funcoesFlutuador(entrada, roboFlutuador1, ambiente1);
                    break; 
                case 6:
                    funcoesFlutuador(entrada, roboFlutuador2, ambiente1);
                    break; 
                case 7:
                    funcoesObstaculoAereo(entrada, roboObstaculoAereo1, ambiente1);
                    break; 
                case 8:
                    funcoesObstaculoAereo(entrada, roboObstaculoAereo2, ambiente1);
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

    private static void funcoesCavador(Scanner entrada, RoboCavador robo, Ambiente ambiente){

        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algumas das acoes abaixo:\n" +
            "[1] - Ligar/Desligar\n" +
            "[2] - Movimentar\n" +
            "[3] - Cavar\n" +
            "[4] - Interagir\n" +
            "[5] - Atacar\n" +
            "[6] - Utilizar sensores\n" +
            "[0] - Voltar\n");
    
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    ligarDesligar(robo);
                    break;
                case 2:
                    movimentarCavador(entrada, robo, ambiente);
                    break;
                case 3:
                    cavar(entrada, robo, ambiente);
                    break; 
                case 4:
                    // interagir(entrada, robo, ambiente);
                    break;  
                case 5:
                    robo.atacar(ambiente);
                    break; 
                case 6:
                    robo.acionarSensores(ambiente, 1);
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

    private static void funcoesObstaculoTerrestre(Scanner entrada, RoboObstaculoTerrestre robo, Ambiente ambiente){

        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algumas das acoes abaixo:\n" +
            "[1] - Ligar/Desligar\n" +
            "[2] - Movimentar\n" +
            "[3] - Posicionar Bloco\n" +
            "[4] - Interagir\n" +
            "[5] - Curar\n" +
            "[6] - Utilizar sensores\n" +
            "[0] - Voltar\n");
    
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    ligarDesligar(robo);
                    break;
                case 2:
                    movimentarObstaculoTerrestre(entrada, robo, ambiente);
                    break;
                case 3:
                    posicionarBloco(ambiente, robo);
                    break; 
                case 4:
                    // interagir(entrada, robo, ambiente);
                    break;  
                case 5:
                    robo.curar(ambiente);
                    break; 
                case 6:
                    robo.acionarSensores(ambiente, 1);
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

    private static void funcoesFlutuador(Scanner entrada, RoboFlutuador robo, Ambiente ambiente){

        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algumas das acoes abaixo:\n" +
            "[1] - Ligar/Desligar\n" +
            "[2] - Movimentar\n" +
            "[3] - Interagir\n" +
            "[4] - Curar\n" +
            "[5] - Utilizar sensores\n" +
            "[0] - Voltar\n");
    
            int opcao = entrada.nextInt();
            switch (opcao) {
                            
                case 1:
                    ligarDesligar(robo);
                    break;
                case 2:
                    movimentarFlutuador(entrada, robo, ambiente);
                    break;
                case 3:
                    // interagir(entrada, robo, ambiente);
                    break;  
                case 4:
                    robo.curar(ambiente);
                    break; 
                case 5:
                    robo.acionarSensores(ambiente, 2);
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

    private static void funcoesObstaculoAereo(Scanner entrada, RoboObstaculoAereo robo, Ambiente ambiente){

        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algumas das acoes abaixo:\n" +
            "[1] - Ligar/Desligar\n" +
            "[2] - Movimentar\n" +
            "[3] - Posicionar Nuvem\n" +
            "[4] - Interagir\n" +
            "[5] - Atacar\n" +
            "[6] - Utilizar sensores\n" +
            "[0] - Voltar\n");
    
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    ligarDesligar(robo);
                    break;
                case 2:
                    movimentarObstaculoAereo(entrada, robo, ambiente);
                    break;
                case 3:
                    posicionarNuvem(ambiente, robo);
                    break; 
                case 4:
                    // interagir(entrada, robo, ambiente);
                    break;  
                case 5:
                    robo.atacar(ambiente);
                    break; 
                case 6:
                    robo.acionarSensores(ambiente, 2);
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

    public static void ligarDesligar(Robo robo){
        if(robo.getEstadoRobo() == EstadoRobo.LIGADO) {
            robo.desligar();
            System.out.println("O robô foi desligado");
        } else {
            robo.ligar();
            System.out.println("O robô foi ligado");
        }
    }

    // Movimento do robô cavador.
    private static void movimentarCavador(Scanner entrada, RoboCavador roboCavador1, Ambiente ambiente1){

        if (roboCavador1.getEstadoRobo() == EstadoRobo.LIGADO){
            // Poderia ser uma excecao tbm o robo estar desligado e pagariamos com o catch comentado
            // estará faltando o caso da velocidade que podemos fazer uma exceção no setVelocidade e pega-la com o catch comentado
            // assim nao precisaria do validar movimento
            while (true) {
            try {
                System.out.print("Informe quantos metros o robô Cavador irá mover:\n" +"Na direção x: ");
                int deltaX = entrada.nextInt();

                System.out.print("Na direção y: ");
                int deltaY = entrada.nextInt();

                System.out.print("Informe a velocidade: ");
                int velocidade = entrada.nextInt();

                roboCavador1.setVelocidade(velocidade);
                roboCavador1.moverPara(deltaX, deltaY, 0, ambiente1);
                break; // movimento válido, sai do loop

            } catch (ForaDosLimitesException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");
            }
            //catch (RoboDesligadoException e){}
            //catch (LimiteVelocidadeException e){}
            catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite números inteiros.");
                entrada.nextLine(); // limpar buffer
            }
        }
        } else {
            System.out.println("O robô está desligado");
        }

    }

    // Verifica se o RoboCavador está dentro dos limites, se não estiver, move ele para a sua posição válida anterior.
    private static void validarMovimentoCavador(Ambiente ambiente1, RoboCavador robo, int deltaX, int deltaY, int deltaZ, int velocidade){
        String direcao;
        int[] posicao = robo.getPosicao();

        // Primeiro verificamos se esta ligado
        if ((velocidade > robo.getVelocidadeMaxima()) || (velocidade < 0)){
            direcao = robo.getDirecao();
            System.out.println("O robô está atualmente na posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e virado para o " + direcao);

        } else if ((ambiente1.dentroDosLimites(posicao[0], posicao[1], posicao[2]) == 1) && (ambiente1.existeEntidade(posicao[0], posicao[1], posicao[2]) == 0)){
            robo.setDirecao(deltaX, deltaY);
            direcao = robo.getDirecao();
            System.out.println("O robô movimentado está atualmente na posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e virado para o " + direcao);
            

        } else { // Se sair do ambiente, volta para a posição inicial.
            robo.moverPara(-deltaX, -deltaY, 0);
            posicao = robo.getPosicao();
            direcao = robo.getDirecao();
            System.out.println("O robô tentou sair do ambiente ou colidiu com algum obstáculo, logo ele retornou para a posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e voltado para o " + direcao);
            
        }
        
    }

    public static void cavar( Scanner entrada, RoboCavador robo, Ambiente ambiente){
        System.out.print("Quantos metros o robo cavará:");
        int deltaZ = entrada.nextInt();
        if(deltaZ < 0){
            System.out.println("Valor invalido digitado");
        } else {
            robo.moverPara(0,0, -deltaZ);
            System.out.println("O robo cavou "+ deltaZ + " metros\n");
        }
    }

    // Movimento do robô obstáculo terrestre.
    private static void movimentarObstaculoTerrestre(Scanner entrada, RoboObstaculoTerrestre roboObstaculoTerrestre1, Ambiente ambiente1){

        if(roboObstaculoTerrestre1.getEstadoRobo() == EstadoRobo.LIGADO){
            System.out.print("Informe quantos metros o robô Obstáculo Terrestre irá mover:\n" +"Na direção x: ");
            int deltaX = entrada.nextInt();

            System.out.print("Na direção y: ");
            int deltaY = entrada.nextInt();

            System.out.print("Informe a velocidade: ");
            int velocidade = entrada.nextInt();

            roboObstaculoTerrestre1.setVelocidade(velocidade);
            roboObstaculoTerrestre1.moverPara(deltaX, deltaY, 0);

            validarMovimentoObstaculoTerrestre(ambiente1, roboObstaculoTerrestre1, deltaX, deltaY, velocidade);            
        } else{
            System.out.println("O robô está desligado");
        }
        System.out.println("\n");
    }
    
    // Verifica se o roboObstaculoTerrestre está dentro dos limites, se não estiver, move ele para a sua posição válida anterior.
    private static void validarMovimentoObstaculoTerrestre(Ambiente ambiente1, RoboObstaculoTerrestre robo, int deltaX, int deltaY, int velocidade){
        String direcao;
        int[] posicao = robo.getPosicao();

        if ((velocidade > robo.getVelocidadeMaxima()) || (velocidade < 0)){
            direcao = robo.getDirecao();
            System.out.println("O robô está atualmente na posição: (" + posicao[0] + "," + posicao[1] + ") e virado para o " + direcao + "\n");
            
        } else if ((ambiente1.dentroDosLimites(posicao[0], posicao[1], posicao[2]) == 1) && (ambiente1.existeEntidade(posicao[0], posicao[1], 100) == 1)){
            robo.setDirecao(deltaX, deltaY);
            direcao = robo.getDirecao();
            System.out.println("O robô movimentado está atualmente na posição: (" + posicao[0] + "," + posicao[1] + ") e virado para o " + direcao + "\n");
            if (ambiente1.existeTapete(posicao[0], posicao[1], posicao[2]) == 1) {
                robo.reporBlocos();
                System.out.println("O robô recarregou sua quantidade de blocos, pois está em um tapete de reposição");
            }
    
        } else { // Se sair do ambiente, volta para a posição inicial.
            robo.mover(-deltaX, -deltaY);
            posicao = robo.getPosicao();
            direcao = robo.getDirecao();
            System.out.println("O robô tentou sair do ambiente ou colidiu com algum obstáculo, logo ele retornou para a posição: (" + posicao[0] + "," + posicao[1] + ") e voltado para o " + direcao + "\n");
            
        }    
    }

    public static void posicionarBloco(Ambiente ambiente1, RoboObstaculoTerrestre robo){

        int[] posicao = robo.getPosicao();
        Obstaculo bloco = robo.soltarBlocos(posicao[0], posicao[1]);

        if (bloco != null){
            ambiente1.adicionarEntidade(bloco);
            System.out.print("O robô soltou um bloco\n");
        }
        System.out.print("\n");
    }

    // Movimento do robô flutuador.
    private static void movimentarFlutuador(Scanner entrada, RoboFlutuador roboFlutuador1, Ambiente ambiente1){
        
        if(roboFlutuador1.getEstadoRobo() == EstadoRobo.LIGADO){
        int deltaZ = 0;
        System.out.print("Informe quantos metros o robô Flutuador irá mover:\n" +"Na direção x: ");
        int deltaX = entrada.nextInt();

        System.out.print("Na direção y: ");
        int deltaY = entrada.nextInt();

        System.out.print("O robo subirá (digite 1) ou descerá (digite 2): ");
        int voo = entrada.nextInt();
        if ((voo == 1) || (voo == 2)) {
            System.out.print("Quantos metros: ");
            deltaZ = entrada.nextInt();
        }

        roboFlutuador1.moverPara(deltaX, deltaY, deltaZ);

        if (voo == 1){
            roboFlutuador1.subir(deltaZ);
        } else if (voo == 2){
            roboFlutuador1.descer(deltaZ);
        }

        validarMovimentoFlutuador(ambiente1, roboFlutuador1, deltaX, deltaY, deltaZ, voo);
        }
        System.out.print("\n");
    }

    // Verifica se o roboFlutuador está dentro dos limites, se não estiver, move ele para a sua posição válida anterior.
    private static void validarMovimentoFlutuador(Ambiente ambiente1, RoboFlutuador robo, int deltaX, int deltaY, int deltaZ, int caso){
        String direcao;
        int[] posicao = robo.getPosicao();

        // Verifica se foi digitado corretamente (1 ou 2), que significa que ele subiu ou desceu. Se não foi, o robô não se move
        if (caso != 1 && caso != 2){
            robo.moverPara(-deltaX, -deltaY);
            posicao = robo.getPosicao();
            System.out.println("Valor inválido inserido, logo o robô permanece na posição (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ")");

        } else if ((ambiente1.dentroDosLimites(posicao[0], posicao[1]) == 1) && (ambiente1.existeObstaculoAereos(posicao[0], posicao[1], posicao[2]) == 1)){
            robo.setDirecao(deltaX, deltaY);
            direcao = robo.getDirecao();
            System.out.println("O robô movimentado está atualmente na posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e virado para o " + direcao);
        
        } else { // Se sair do ambiente, volta para a posição inicial.
            robo.mover(-deltaX, -deltaY);
            robo.setAltitude(deltaZ, caso);
            posicao = robo.getPosicao();
            direcao = robo.getDirecao();
            System.out.println("O robô tentou sair do ambiente ou colidiu com algum obstáculo, logo ele retornou para a posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e voltado para o " + direcao);
        }
    }

    // Movimento do robô obstáculo aereo.
    private static void movimentarObstaculoAereo(Scanner entrada, RoboObstaculoAereo roboObstaculoAereo1, Ambiente ambiente1){
        
        if(roboObstaculoAereo1.getEstadoRobo() == EstadoRobo.LIGADO){
            int deltaZ = 0;

            System.out.print("Informe quantos metros o robô Obstaculo Aereo irá mover:\n" +"Na direção x: ");
            int deltaX = entrada.nextInt();

            System.out.print("Na direção y: ");
            int deltaY = entrada.nextInt();

            System.out.print("O robo subirá (digite 1) ou descerá (digite 2): ");
            int voo = entrada.nextInt();
            if ((voo == 1) || (voo == 2)) {
                System.out.print("Quantos metros: ");
                deltaZ = entrada.nextInt();
            }

            roboObstaculoAereo1.mover(deltaX, deltaY);

            if (voo == 1){
                roboObstaculoAereo1.subir(deltaZ);
            } else if (voo == 2){
                roboObstaculoAereo1.descer(deltaZ);
            }

            validarMovimentoObstaculoAereo(ambiente1, roboObstaculoAereo1, deltaX, deltaY, deltaZ, voo);
        } else {
            System.out.println("O robô está desligado");
        }
        System.out.println("\n");
    }

    // Verifica se o roboObstaculoAereo está dentro dos limites, se não estiver, move ele para a sua posição válida anterior.
    private static int validarMovimentoObstaculoAereo(Ambiente ambiente1, RoboObstaculoAereo robo, int deltaX, int deltaY, int deltaZ, int caso){
        String direcao;
        int[] posicao = robo.getPosicao();

        // Verifica se foi digitado corretamente (1 ou 2), que significa que ele subiu ou desceu. Se não foi, o robô não se move
        if (caso != 1 && caso != 2){
            robo.mover(-deltaX, -deltaY);
            posicao = robo.getPosicao();
            System.out.println("Valor inválido inserido, logo o robô permanece na posição (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ")\n");
            return 0;

        } else if ((ambiente1.dentroDosLimites(posicao[0], posicao[1]) == 1) && (ambiente1.existeObstaculoAereos(posicao[0], posicao[1], posicao[2]) == 1)){
            robo.setDirecao(deltaX, deltaY);
            direcao = robo.getDirecao();
            System.out.println("O robô movimentado está atualmente na posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e virado para o " + direcao + "\n");
            return 1;

        } else { // Se sair do ambiente, volta para a posição inicial.
            robo.mover(-deltaX, -deltaY);
            robo.setAltitude(deltaZ, caso); //Aqui tem algum erro, no teste que eu fiz nao voltou para a posicao em z
            posicao = robo.getPosicao();
            direcao = robo.getDirecao();
            System.out.println("O robô tentou sair do ambiente ou colidiu com algum obstáculo, logo ele retornou para a posição: (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e voltado para o " + direcao + "\n");
            return 0;
        }
    }

    public static void posicionarNuvem(Ambiente ambiente1, RoboObstaculoAereo robo){
        int[] posicao = robo.getPosicao();
        Obstaculo nuvem = robo.soltarNuvens(posicao[0], posicao[1], posicao[2]);

        if (nuvem != null){
            ambiente1.adicionarEntidade(nuvem);
            System.out.print("O robô soltou um nuvem\n");
        }
        System.out.print("\n");
    }

    // Escolhe qual robô irá utilizar os seus sensores e imprime o resultado obtido.
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

            switch (opcao){
                case 1:
                    vetorPosicao = roboCavador1.getPosicao();
                    roboCavador1.acionarSensores(ambiente1, 1);
                    break;
                case 2:
                    vetorPosicao = roboObstaculoTerrestre1.getPosicao();
                    roboObstaculoTerrestre1.acionarSensores(ambiente1, );
                    break; 
                case 3:
                    vetorPosicao = roboFlutuador1.getPosicao();
                    roboFlutuador1.acionarSensores(ambiente1, );
                    break;  
                case 4:
                    vetorPosicao = roboObstaculoAereo1.getPosicao();
                    roboObstaculoAereo1.acionarSensores(ambiente1, );               
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

// Adicionar sistema de reposição de blocos