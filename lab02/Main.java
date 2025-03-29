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
        
        
        // Testes do roboCavador1:
        nomeRobo = roboCavador1.getNome();
        direcao = "Leste";
        roboCavador1.setDirecao(direcao);
        vetorPosicao = roboCavador1.getPosicao();
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");


        // Teste 1: Robo está dentro dos limites, com velocidade válida e a profundidade perfurada é válida.
        int deltaX = 50;
        int deltaY = 50;
        int deltaZ = 50;

        System.out.print("Teste de movimento: (50,50,50)\n");
        roboCavador1.setVelocidade(20);
        roboCavador1.mover(deltaX, deltaY);
        System.out.println("20 é um valor válido de velocidade");

        roboCavador1.cavar(deltaZ); // Perfuração válida.
        vetorPosicao = roboCavador1.getPosicao();

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta na direcao " + direcao + " e na nova posição: ");
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente, logo ele retornou para a posição: ");
            roboCavador1.mover(-deltaX, -deltaY);
            vetorPosicao = roboCavador1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        // Teste 2: Robo não está dentro dos limites, velocidade superior a permitida e a profundidade perfurada não é válida.
        deltaX = 1;
        deltaY = 1;
        deltaZ = 51;

        System.out.print("Teste de movimento: (1,1,51)\n");
        roboCavador1.setVelocidade(50);
        roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Perfuração inválida.
        vetorPosicao = roboCavador1.getPosicao();

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta na direcao " + direcao + " e na nova posição: ");
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente, logo ele retornou para a posição: ");
            roboCavador1.mover(-deltaX, -deltaY);
            vetorPosicao = roboCavador1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        }

        // Testes do roboObstaculoTerrestre1:
        nomeRobo = roboObstaculoTerrestre1.getNome();
        direcao = "Oeste";
        roboObstaculoTerrestre1.setDirecao(direcao);
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");

        // Teste 1: Deposito de um bloco em uma posição válida
        roboObstaculoTerrestre1.soltarBlocos();
        ambiente1.adicionarBloco(vetorPosicao);
        System.out.println("Um bloco foi depositado na posição (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")");

        // Teste 2: Robo está dentro dos limites, com velocidade válida e se move para uma posição válida sem obstáculos
        deltaX = 20;
        deltaY = 20;

        System.out.print("Teste de movimento: (20,20)\n");
        roboObstaculoTerrestre1.setVelocidade(25);
        roboObstaculoTerrestre1.mover(deltaX, deltaY);
        System.out.println("25 é um valor válido de velocidade");
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta na direcao " + direcao + " e na nova posição: ");
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            roboObstaculoTerrestre1.mover(-deltaX, -deltaY);
            vetorPosicao = roboObstaculoTerrestre1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        // Teste 3: Robo está dentro dos limites, com velocidade válida e se move para uma posição inválida com obstáculos
        deltaX = -20;
        deltaY = -20;

        System.out.print("Teste de movimento: (-20,-20)\n");
        roboObstaculoTerrestre1.mover(deltaX, deltaY);
        System.out.println("25 é um valor válido de velocidade");
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta na direcao " + direcao + " e na nova posição: ");
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            roboObstaculoTerrestre1.mover(-deltaX, -deltaY);
            vetorPosicao = roboObstaculoTerrestre1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        }


        // Testes do roboObstaculoAereo1
        nomeRobo = roboObstaculoAereo1.getNome();
        direcao = "Sul";
        roboCavador1.setDirecao(direcao);
        vetorPosicao = roboObstaculoAereo1.getPosicao();
        vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");


        // Teste 1: Robo está dentro dos limites e sobe para uma posição válida sem obstáculos
        deltaX = 20;
        deltaY = 15;
        deltaZ = 35;

        System.out.print("Teste de movimento: (20,15,35)\n");
        roboObstaculoAereo1.mover(deltaX, deltaY);
        roboObstaculoAereo1.subir(deltaZ);
        vetorPosicao = roboObstaculoAereo1.getPosicao();
        vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoAereos(vetorPosicao[0], vetorPosicao[1], vetorPosicao[2]) == 1)){
            System.out.print("O " + nomeRobo + " esta na direcao " + direcao + " e na nova posição: ");
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            roboObstaculoAereo1.mover(-deltaX, -deltaY);
            roboObstaculoAereo1.setAltitude(-deltaZ);
            vetorPosicao = roboObstaculoAereo1.getPosicao();
            vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        }

        // Teste 2: Robo deposita uma nuvem em sua posicao
        roboObstaculoAereo1.soltarNuvens();
        ambiente1.adicionarNuvem(vetorPosicao);
        System.out.println("Uma nuvem foi depositada na posição (" + vetorPosicao[0] + ", " + vetorPosicao[1] +  ", " + vetorPosicao[2] + ")");

        // Teste 3: Robo está dentro dos limites e sobe para uma posição válida sem obstáculos
        deltaX = 30;
        deltaY = 30;
        deltaZ = 65;

        System.out.print("Teste de movimento: (30,30,65)\n");
        roboObstaculoAereo1.mover(deltaX, deltaY);
        roboObstaculoAereo1.subir(deltaZ);
        vetorPosicao = roboObstaculoAereo1.getPosicao();
        vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoAereos(vetorPosicao[0], vetorPosicao[1], vetorPosicao[2]) == 1)){
            System.out.print("O " + nomeRobo + " esta na direcao " + direcao + " e na nova posição: ");
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            roboObstaculoAereo1.mover(-deltaX, -deltaY);
            roboObstaculoAereo1.setAltitude(-deltaZ);
            vetorPosicao = roboObstaculoAereo1.getPosicao();
            vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        }

        // Teste 4: Robo esta dentro dos limites e desce para colidir com a nuvem
        deltaX = -30;
        deltaY = -30;
        deltaZ = 65; // O valor é positivo pois há um metodo distinto para descer

        System.out.print("Teste de movimento: (-30,-30,-65)\n");
        roboObstaculoAereo1.mover(deltaX, deltaY);
        roboObstaculoAereo1.descer(deltaZ);
        vetorPosicao = roboObstaculoAereo1.getPosicao();
        vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoAereos(vetorPosicao[0], vetorPosicao[1], vetorPosicao[2]) == 1)){
            System.out.print("O " + nomeRobo + " esta na direcao " + direcao + " e na nova posição: ");
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            roboObstaculoAereo1.mover(-deltaX, -deltaY);
            roboObstaculoAereo1.setAltitude(deltaZ);
            vetorPosicao = roboObstaculoAereo1.getPosicao();
            vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        }
        
        // Testes do roboFlutuador1


        // Testes do robo 


    
    }
}