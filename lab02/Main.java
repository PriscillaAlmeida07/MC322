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
        vetorPosicao = roboCavador1.getPosicao();
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");


        // Teste 1: Robo está dentro dos limites, com velocidade válida e a profundidade perfurada é válida.
        System.out.print("Teste de movimento: (+50,+50,-50)\n");
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

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente, logo ele retornou para a posição: ");
            roboCavador1.mover(-deltaX, -deltaY);
            vetorPosicao = roboCavador1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        // Teste 2: Robo não está dentro dos limites, velocidade superior a permitida e a profundidade perfurada não é válida.
        System.out.print("Teste de movimento: (+1,+1,-51)\n");
        deltaX = 1;
        deltaY = 1;
        deltaZ = 51;

        roboCavador1.setVelocidade(51);

        roboCavador1.setDirecao(deltaX, deltaY);
        direcao = roboCavador1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Perfuração inválida.
        vetorPosicao = roboCavador1.getPosicao();

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");

        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente, logo ele retornou para a posição: ");
            roboCavador1.mover(-deltaX, -deltaY);
            vetorPosicao = roboCavador1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        }

        // Testes do roboObstaculoTerrestre1:
        nomeRobo = roboObstaculoTerrestre1.getNome();
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");

        // Teste 1: Deposito de um bloco em uma posição válida
        roboObstaculoTerrestre1.soltarBlocos();
        ambiente1.adicionarBloco(vetorPosicao);
        System.out.println("Um bloco foi depositado na posição (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")");

        // Teste 2: Robo está dentro dos limites, com velocidade válida e se move para uma posição válida sem obstáculos
        System.out.print("Teste de movimento: (+20,+20)\n");
        deltaX = 20;
        deltaY = 20;

        roboObstaculoTerrestre1.setVelocidade(25);
        System.out.println("25 é um valor válido de velocidade");

        roboObstaculoTerrestre1.setDirecao(deltaX, deltaY);
        direcao = roboObstaculoTerrestre1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboObstaculoTerrestre1.mover(deltaX, deltaY);
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
       
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            roboObstaculoTerrestre1.mover(-deltaX, -deltaY);
            vetorPosicao = roboObstaculoTerrestre1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        // Teste 3: Robo está dentro dos limites, com velocidade válida e se move para uma posição inválida com obstáculos
        System.out.print("Teste de movimento: (-20,-20)\n");
        deltaX = -20;
        deltaY = -20;

        System.out.println("25 é um valor válido de velocidade");

        roboObstaculoTerrestre1.setDirecao(deltaX, deltaY);
        direcao = roboObstaculoTerrestre1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboObstaculoTerrestre1.mover(deltaX, deltaY);
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoTerrestres(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            roboObstaculoTerrestre1.mover(-deltaX, -deltaY);
            vetorPosicao = roboObstaculoTerrestre1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        }


        // Testes do roboObstaculoAereo1
        nomeRobo = roboObstaculoAereo1.getNome();
        vetorPosicao = roboObstaculoAereo1.getPosicao();
        vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");


        // Teste 1: Robo está dentro dos limites e sobe para uma posição válida sem obstáculos
        System.out.print("Teste de movimento: (+20,+15,+15)\n");
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

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoAereos(vetorPosicao[0], vetorPosicao[1], vetorPosicao[2]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        
        } else { // Se sair do ambiente, volta para a posição inicial.
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
        System.out.print("Teste de movimento: (+30,+30,+65)\n");
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

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoAereos(vetorPosicao[0], vetorPosicao[1], vetorPosicao[2]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            roboObstaculoAereo1.mover(-deltaX, -deltaY);
            roboObstaculoAereo1.setAltitude(-deltaZ);
            vetorPosicao = roboObstaculoAereo1.getPosicao();
            vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        }

        // Teste 4: Robo esta dentro dos limites e desce para colidir com a nuvem
        System.out.print("Teste de movimento: (-30,-30,-65)\n");
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

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoAereos(vetorPosicao[0], vetorPosicao[1], vetorPosicao[2]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n\n");
    
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            roboObstaculoAereo1.mover(-deltaX, -deltaY);
            roboObstaculoAereo1.setAltitude(deltaZ);
            vetorPosicao = roboObstaculoAereo1.getPosicao();
            vetorPosicao = roboObstaculoAereo1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n\n");
        }
        
        // Testes do roboFlutuador1
        nomeRobo = roboFlutuador1.getNome();
        vetorPosicao = roboFlutuador1.getPosicao();
        vetorPosicao = roboFlutuador1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");

        // Teste 1: Robo está dentro dos limites, com velocidade de subida válida e sobirá para uma posição válida sem obstáculos
        System.out.print("Teste de movimento: (+10,+20,+3)\n");
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

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoAereos(vetorPosicao[0], vetorPosicao[1], vetorPosicao[2]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            roboFlutuador1.mover(-deltaX, -deltaY);
            roboFlutuador1.setAltitude(-deltaZ);
            vetorPosicao = roboFlutuador1.getPosicao();
            vetorPosicao = roboFlutuador1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        }

        // Teste 2: Robo está dentro dos limites, com velocidade de subida inválida e sobirá para uma posição inválida com obstáculos
        System.out.print("Teste de movimento: (+60,+45,+12)\n");
        deltaX = 60;
        deltaY = 45;
        deltaZ = 12;

        roboFlutuador1.setDirecao(deltaX, deltaY);
        direcao = roboFlutuador1.getDirecao();
        System.out.println("O " + nomeRobo + " está tentando ir para o " + direcao);

        roboFlutuador1.mover(deltaX, deltaY);
        roboFlutuador1.subir(deltaZ);
        vetorPosicao = roboFlutuador1.getPosicao();
        vetorPosicao = roboFlutuador1.getPosicao(vetorPosicao[0], vetorPosicao[1]);

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculoAereos(vetorPosicao[0], vetorPosicao[1], vetorPosicao[2]) == 1)){
            System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        
        } else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele retornou para a posição: ");
            roboFlutuador1.mover(-deltaX, -deltaY);
            roboFlutuador1.setAltitude(-deltaZ);
            vetorPosicao = roboFlutuador1.getPosicao();
            vetorPosicao = roboFlutuador1.getPosicao(vetorPosicao[0], vetorPosicao[1]);
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + "," + vetorPosicao[2] + ")\n");
        }

    }
}