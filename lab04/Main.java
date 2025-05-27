import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        // Instânciamento do ambiente.
        Ambiente ambiente1 = new Ambiente();

        // Instânciamento de alguns obstáculos.
        Obstaculo arvore1 = new Obstaculo(TipoObstaculo.ARVORE, 5, 5, 0);
        Obstaculo arvore2 = new Obstaculo(TipoObstaculo.ARVORE, 5, 45, 0);
        Obstaculo arvore3 = new Obstaculo(TipoObstaculo.ARVORE, 45, 5, 0);
        Obstaculo arvore4 = new Obstaculo(TipoObstaculo.ARVORE, 45, 45, 0);

        // Adicionando obstáculos ao ambiente.
        ambiente1.adicionarEntidade(arvore1);
        ambiente1.adicionarEntidade(arvore2);
        ambiente1.adicionarEntidade(arvore3);
        ambiente1.adicionarEntidade(arvore4);

        // Instânciamento de pontos de reposição.
        TapeteReposicao tapete1 = new TapeteReposicao(10, 10);
        TapeteReposicao tapete2 = new TapeteReposicao(30,30);
        TapeteReposicao tapete3 = new TapeteReposicao(30, 10);
        TapeteReposicao tapete4 = new TapeteReposicao(10, 30);

        // Adicionando pontos de reposição ao ambiente.
        ambiente1.adicionarEntidade(tapete1);
        ambiente1.adicionarEntidade(tapete2);
        ambiente1.adicionarEntidade(tapete3);
        ambiente1.adicionarEntidade(tapete4);

        // Instânciamento de todos os robôs.
        RoboCavador roboCavador1 = new RoboCavador("roboCavador1", "RC01", EstadoRobo.LIGADO, 22, 25, 0);
        RoboObstaculoTerrestre roboObstaculoTerrestre1 = new RoboObstaculoTerrestre("roboObstaculoTerrestre1", "ROT01", EstadoRobo.LIGADO, 23, 25, 0);
        RoboFlutuador roboFlutuador1 = new RoboFlutuador("roboFlutuador1", "RF01", EstadoRobo.LIGADO, 24, 25, 0);
        RoboObstaculoAereo roboObstaculoAereo1 = new RoboObstaculoAereo("roboObstaculoAereo1", "ROA01", EstadoRobo.LIGADO, 25, 25, 0);
        RoboCavador roboCavador2 = new RoboCavador("roboCavador2", "RC02", EstadoRobo.DESLIGADO, 26, 25, 0);
        RoboObstaculoTerrestre roboObstaculoTerrestre2 = new RoboObstaculoTerrestre("roboObstaculoTerrestre2", "ROT02", EstadoRobo.DESLIGADO, 27, 25, 0);
        RoboFlutuador roboFlutuador2 = new RoboFlutuador("roboFlutuador2", "RF02", EstadoRobo.DESLIGADO, 28, 25, 0);
        RoboObstaculoAereo roboObstaculoAereo2 = new RoboObstaculoAereo("roboObstaculoAereo2", "ROA02", EstadoRobo.DESLIGADO, 29, 25, 0);

        // Criamos esse robô apenas para testar o metodo removerEntidade
        RoboObstaculoAereo roboObstaculoAereo3 = new RoboObstaculoAereo("roboObstaculoAereo3", "ROA03", EstadoRobo.LIGADO, 30, 25, 0);
        System.out.print("\n");

        // Adicionando robôs ao ambiente.
        ambiente1.adicionarEntidade(roboCavador1);
        ambiente1.adicionarEntidade(roboObstaculoTerrestre1);
        ambiente1.adicionarEntidade(roboFlutuador1);
        ambiente1.adicionarEntidade(roboObstaculoAereo1);
        ambiente1.adicionarEntidade(roboCavador2);
        ambiente1.adicionarEntidade(roboObstaculoTerrestre2);
        ambiente1.adicionarEntidade(roboFlutuador2);
        ambiente1.adicionarEntidade(roboObstaculoAereo2);
        
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
                   // cavar(entrada, robo, ambiente);
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
                    try{
                        robo.executarTarefa(entrada, ambiente, 0, 0, 0, 0);
                    }catch (ForaDosLimitesException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                    catch(ColisaoException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
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
                    try{
                        robo.executarTarefa(entrada, ambiente, 0, 0, 0, 0);
                    }catch (ForaDosLimitesException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                    catch(ColisaoException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
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
            System.out.println("O robô foi desligado\n");
        } else {
            robo.ligar();
            System.out.println("O robô foi ligado\n");
        }
    }

    // Movimento do robô cavador.
    private static void movimentarCavador(Scanner entrada, RoboCavador robo, Ambiente ambiente1){

        while (true) {
            try {
                if(robo.getEstadoRobo() == EstadoRobo.DESLIGADO)
                    throw new RoboDesligadoException();
                System.out.print("Informe quantos metros o robô Cavador irá mover:\n" +"Na direção x: ");
                int deltaX = entrada.nextInt();

                System.out.print("Na direção y: ");
                int deltaY = entrada.nextInt();

                System.out.print("Informe a velocidade: ");
                int velocidade = entrada.nextInt();

                robo.setVelocidade(velocidade);
                robo.moverPara(deltaX, deltaY, 0, ambiente1);
                break; // movimento válido, sai do loop

            } 
            catch (RoboDesligadoException e){
                System.err.println("Erro: " + e.getMessage());
                break;
            }
            catch (ForaDosLimitesException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");
            }
            catch(ColisaoException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");  
            }
            catch (VelocidadeMaxException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");
            }
            catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Digite números inteiros.");
                entrada.nextLine(); // limpar buffer
            }
        }
    }
    

    // Movimento do robô obstáculo terrestre.
    private static void movimentarObstaculoTerrestre(Scanner entrada, RoboObstaculoTerrestre robo, Ambiente ambiente1){

        while (true) {
            try {
                if(robo.getEstadoRobo() == EstadoRobo.DESLIGADO)
                    throw new RoboDesligadoException();
                System.out.print("Informe quantos metros o robô Obstáculo Terrestre irá mover:\n" +"Na direção x: ");
                int deltaX = entrada.nextInt();

                System.out.print("Na direção y: ");
                int deltaY = entrada.nextInt();

                System.out.print("Informe a velocidade: ");
                int velocidade = entrada.nextInt();

                robo.setVelocidade(velocidade);
                robo.moverPara(deltaX, deltaY, 0, ambiente1);
                break; // movimento válido, sai do loop

            } 
            catch (RoboDesligadoException e){
                System.err.println("Erro: " + e.getMessage());
                break;
            }
            catch (ForaDosLimitesException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");
            }
            catch(ColisaoException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");  
            }
            catch (VelocidadeMaxException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");
            }
            catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Digite números inteiros.");
                entrada.nextLine(); // limpar buffer
            }
        }

    }
/* 

    public static void posicionarBloco(Ambiente ambiente1, RoboObstaculoTerrestre robo){

        int[] posicao = robo.getPosicao();
        Obstaculo bloco = robo.soltarBlocos(posicao[0], posicao[1]);

        if (bloco != null){
            ambiente1.adicionarEntidade(bloco);
            System.out.print("O robô soltou um bloco\n");
        }
        System.out.print("\n");
    }
*/
    // Movimento do robô flutuador.
    private static void movimentarFlutuador(Scanner entrada, RoboFlutuador robo, Ambiente ambiente1){
        while (true) {
            try {
                if(robo.getEstadoRobo() == EstadoRobo.DESLIGADO)
                    throw new RoboDesligadoException();
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
                } else {
                    throw new ValorInvalidoException();
                }

                if(voo == 1)
                    robo.subir(ambiente1, deltaX, deltaY, deltaZ);
                else
                    robo.descer(ambiente1, deltaX, deltaY, deltaZ);
                break; // movimento válido, sai do loop

            } 
            catch (RoboDesligadoException e){
                System.err.println("Erro: " + e.getMessage());
                break;
            }
            catch (ValorInvalidoException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");  
            }
            catch (ForaDosLimitesException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");
            }
            catch(ColisaoException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");  
            }
            catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Digite números inteiros.");
                entrada.nextLine(); // limpar buffer
            }
        }
        
    }

 
    // Movimento do robô obstáculo aereo.
    private static void movimentarObstaculoAereo(Scanner entrada, RoboObstaculoAereo robo, Ambiente ambiente1){
        
        while (true) {
            try {
                if(robo.getEstadoRobo() == EstadoRobo.DESLIGADO)
                    throw new RoboDesligadoException();
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
                } else {
                    throw new ValorInvalidoException();
                }

                if(voo == 1)
                    robo.subir(ambiente1, deltaX, deltaY, deltaZ);
                else
                    robo.descer(ambiente1, deltaX, deltaY, deltaZ);
                break; // movimento válido, sai do loop

            } 
            catch (RoboDesligadoException e){
                System.err.println("Erro: " + e.getMessage());
                break;
            }
            catch (ValorInvalidoException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");  
            }
            catch (ForaDosLimitesException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");
            }
            catch(ColisaoException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");  
            }
            catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Digite números inteiros.");
                entrada.nextLine(); // limpar buffer
            }
        }
    }
}
/* 
    public static void posicionarNuvem(Ambiente ambiente1, RoboObstaculoAereo robo){
        int[] posicao = robo.getPosicao();
        Obstaculo nuvem = robo.soltarNuvens(posicao[0], posicao[1], posicao[2]);

        if (nuvem != null){
            ambiente1.adicionarEntidade(nuvem);
            System.out.print("O robô soltou um nuvem\n");
        }
        System.out.print("\n");
    }
*//* 
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
*/
// Adicionar sistema de reposição de blocos