package main;

import ambiente.*;
import arquivos.Arquivo;
import comunicacao.CentralComunicacao;
import enums.*;
import exceptions.*;
import missao.MissaoSeguranca;
import missao.MissaoVida;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import obstaculos_tapetes.Obstaculo;
import obstaculos_tapetes.TapeteReposicao;
import robo.*;

public class Main {
    public static void main(String[] args){

        // Caminho relativo - caminho referente ao diretorio que estamos, ou seja, como estamos rodando o programa a partir da pasta lab05, esse é o caminho relativo
        String caminhoRelativo = "src/arquivos/arquivo.txt";

        // Criando o arquivo
        Arquivo arquivo = new Arquivo(caminhoRelativo);

        // Adicionando data o hora de escrita no arquivo
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHoraFormatada = agora.format(formato);


        String conteudo = dataHoraFormatada + "\n" + "-------------------------------------" + "\n\n" + "Missões realizadas:\n";

        // metodo que escreve no arquivo (só precisa do conteudo a ser inserido)
        arquivo.escreverNoArquivo(conteudo);

        // Instânciamento do ambiente.
        Ambiente ambiente1 = new Ambiente();
        System.out.println("O ambiente foi inicializado, as suas dimensões são: largura = 50, comprimento = 50, altura = 50 (25 de profundidade e 25 de céu)");

        // Instânciamento da central de comunicação.
        CentralComunicacao centralComunicacao = new CentralComunicacao();

        // Instânciamento de alguns obstáculos.
        Obstaculo arvore1 = new Obstaculo(TipoObstaculo.ARVORE, 5, 5, 25);
        Obstaculo arvore2 = new Obstaculo(TipoObstaculo.ARVORE, 5, 45, 25);
        Obstaculo arvore3 = new Obstaculo(TipoObstaculo.ARVORE, 45, 5, 25);
        Obstaculo arvore4 = new Obstaculo(TipoObstaculo.ARVORE, 45, 45, 25);

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
        RoboCavador roboCavador1 = new RoboCavador("roboCavador1", "RC01", EstadoRobo.LIGADO, 22, 25, 25);
        RoboObstaculoTerrestre roboObstaculoTerrestre1 = new RoboObstaculoTerrestre("roboObstaculoTerrestre1", "ROT01", EstadoRobo.LIGADO, 23, 25, 25);
        RoboFlutuador roboFlutuador1 = new RoboFlutuador("roboFlutuador1", "RF01", EstadoRobo.LIGADO, 24, 25, 25);
        RoboObstaculoAereo roboObstaculoAereo1 = new RoboObstaculoAereo("roboObstaculoAereo1", "ROA01", EstadoRobo.LIGADO, 25, 25, 25);
        RoboCavador roboCavador2 = new RoboCavador("roboCavador2", "RC02", EstadoRobo.DESLIGADO, 26, 25, 25);
        RoboObstaculoTerrestre roboObstaculoTerrestre2 = new RoboObstaculoTerrestre("roboObstaculoTerrestre2", "ROT02", EstadoRobo.DESLIGADO, 27, 25, 25);
        RoboFlutuador roboFlutuador2 = new RoboFlutuador("roboFlutuador2", "RF02", EstadoRobo.DESLIGADO, 28, 25, 25);
        RoboObstaculoAereo roboObstaculoAereo2 = new RoboObstaculoAereo("roboObstaculoAereo2", "ROA02", EstadoRobo.DESLIGADO, 29, 25, 25);

        //Criação dos Agentes
        AgenteSeguranca agenteSeguranca1 = new AgenteSeguranca("agenteSeguranca1", "AI01", EstadoRobo.LIGADO, 30, 25, 25);
        AgenteVida agenteVida1 = new AgenteVida("agenteVida1", "AV01", EstadoRobo.LIGADO, 31, 25, 25);

        // Criamos esse robô apenas para testar o metodo removerEntidade.
        RoboObstaculoAereo roboObstaculoAereo3 = new RoboObstaculoAereo("roboObstaculoAereo3", "ROA03", EstadoRobo.LIGADO, 30, 25, 25);

        // Adicionando robôs ao ambiente.
        ambiente1.adicionarEntidade(roboCavador1);
        ambiente1.adicionarEntidade(roboObstaculoTerrestre1);
        ambiente1.adicionarEntidade(roboFlutuador1);
        ambiente1.adicionarEntidade(roboObstaculoAereo1);
        ambiente1.adicionarEntidade(roboCavador2);
        ambiente1.adicionarEntidade(roboObstaculoTerrestre2);
        ambiente1.adicionarEntidade(roboFlutuador2);
        ambiente1.adicionarEntidade(roboObstaculoAereo2);

        System.out.println("O ambiente foi inicializado com 8 robôs, dois de cada tipo criado: Robo Cavador, Robo Obstáculo Terrestre, Robo Flutuador e Robô Obstáculo Terrestre\n" +
                            "Inicialmente também existem quatro árvores e quatro tapetes de reposição por padrão\n");
        
        // Testando o metodo removerEntidade.
        System.out.println("Teste de remoção de um Robô:");
        ambiente1.adicionarEntidade(roboObstaculoAereo3);
        ambiente1.removerEntidade(roboObstaculoAereo3);
        System.out.println("O Robô Obstáculo Aéreo 3 foi removido do ambiente \n");

        // Variáveis de funcionamento do sistema.
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        // Loop de funcionamento do sistema:
        while(continuar){
            System.out.println("Selecione alguma das ações abaixo:\n" +
            "[1] - Selecionar um Robô (funções/interações)\n" +
            "[2] - Selecionar Agente \n" +
            "[3] - Listar todos os Robôs\n" +
            "[4] - Listar posição dos Objetos (não robôs)\n" +
            "[5] - Exibir plano xy atual do ambiente\n" +
            "[6] - Listar todas as mensagens trocadas\n" +
            "[0] - Sair do programa\n");
    
            int opcao = entrada.nextInt();
            switch (opcao){
                case 1:
                    selecionarRobo(entrada, ambiente1, roboCavador1, roboCavador2, roboObstaculoTerrestre1, roboObstaculoTerrestre2, roboObstaculoAereo1, roboObstaculoAereo2, roboFlutuador1, roboFlutuador2, centralComunicacao);
                    break;
                case 2:
                    selecionarAgente(entrada, ambiente1, agenteSeguranca1, agenteVida1, centralComunicacao);
                    break;
                case 3:
                    listarRobos(entrada, ambiente1);
                    break; 
                case 4:
                    ambiente1.listarObjetos();
                    break;  
                case 5:
                    ambiente1.visualizarAmbiente();
                    break; 
                case 6:
                    centralComunicacao.exibirMensagens();
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

    private static void selecionarAgente(Scanner entrada, Ambiente ambiente1, AgenteSeguranca agenteSeguranca1, AgenteVida agenteVida1, CentralComunicacao centralComunicacao){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algum dos Agentes Inteligentes abaixo:\n" +
            "[1] - Agente Segurança1\n" +
            "[2] - Agente Vida1\n" + 
            "[0] - Voltar\n");

            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    selecionarMissaoSeguranca(entrada, ambiente1, agenteSeguranca1, centralComunicacao);
                    break;
                case 2:
                    selecionarMissaoVida(entrada, ambiente1, agenteVida1, centralComunicacao);
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

    private static void selecionarMissaoSeguranca(Scanner entrada, Ambiente ambiente1, AgenteSeguranca agenteSeguranca1, CentralComunicacao centralComunicacao){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algumas das opcoes para missoes:\n" +
            "[1] - Executar missão Segurança\n" +
            "[2] - Executar missão Buscar ponto\n" + //uma missao mais basica so para ter 2 missoes pelo menos para cada
            "[3] - Encerrada missão que esta em execução\n" +
            "[0] - Voltar\n");

            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                // So podera ser feita uma missao por vez (pois no pdf do lab a missao é uma variavel nao array na classe agente inteligente)
                    if(agenteSeguranca1.temMissao() == false){
                        MissaoSeguranca missaoSeguranca = new MissaoSeguranca();
                        agenteSeguranca1.definirMissao(missaoSeguranca);
                        agenteSeguranca1.executarMissao(ambiente1, centralComunicacao);
                    } else {
                        System.out.println("O robo ja esta realizando uma missao"); // podemos trocar por exception se for necessario
                    }
                    break;
                case 2:
                    // vamos criar uma missao mais basica so para ter duas, mas sera a mesma logica
                    // if(agenteSeguranca1.temMissao() == false){
                    //     MissaoSeguranca missaoSeguranca = new MissaoSeguranca();
                    //     agenteSeguranca1.definirMissao(missaoSeguranca);
                    //     agenteSeguranca1.executarMissao(ambiente1);
                    // } else {
                    //     System.out.println("O robo ja esta realizando uma missao"); // podemos trocar por exception se for necessario
                    // }
                    break;
                case 3:
                    agenteSeguranca1.excluirMissao();
                    // nao dara erro pois se nao tiver missao ainda vai mudar para null (mesmo sendo null, mas nao dara problema)
                    System.out.println("A missão atual foi encerrada");
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

    private static void selecionarMissaoVida(Scanner entrada, Ambiente ambiente1, AgenteVida agenteVida1, CentralComunicacao centralComunicacao){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algumas das opcoes para missoes:\n" +
            "[1] - Executar missão Vida\n" +
            "[2] - Executar missão Buscar ponto\n" + //uma missao mais basica so para ter 2 missoes pelo menos para cada
            "[3] - Encerrar missão que esta em execução\n" +
            "[0] - Voltar\n");

            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                // So podera ser feita uma missao por vez (pois no pdf do lab a missao é uma variavel nao array na classe agente inteligente)
                    if(agenteVida1.temMissao() == false){
                        MissaoVida missaoVida = new MissaoVida();
                        agenteVida1.definirMissao(missaoVida);
                        agenteVida1.executarMissao(ambiente1, centralComunicacao);
                    } else {
                        System.out.println("O robo ja esta realizando uma missao"); // podemos trocar por exception se for necessario
                    }
                    break;
                case 2:
                    // vamos criar uma missao mais basica so para ter duas
                    // MissaoBasica missaoBasica = new MissaoBasica();
                    // agenteSeguranca1.definirMissao(missaoBasica);
                    // agenteSeguranca1.executarMissao(ambiente1);
                    break;
                case 3:
                    agenteVida1.excluirMissao();
                    // nao dara erro pois se nao tiver missao ainda vai mudar para null (mesmo sendo null, mas nao dara problema)
                    System.out.println("A missão atual foi encerrada");
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

    // Função que lista determinada característica para todos os robôs que estão no ambiente.
    private static void listarRobos(Scanner entrada, Ambiente ambiente1){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione o que deseja visualizar sobre os robôs no ambiente:\n" +
            "[1] - Descrição\n" +
            "[2] - Estado (ligado/desligado)\n" +
            "[3] - Posição\n" +
            "[4] - Vida\n" +
            "[0] - Voltar\n");

            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    ambiente1.listarDescricoesRobos();
                    break;
                case 2:
                    ambiente1.listarEstadosRobos();
                    break;
                case 3:
                    ambiente1.listarPosicaoRobos();
                    break;
                case 4:
                    ambiente1.listarVidaRobos();
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

    // Escolhemos movimentar algum dos robôs, agora precisamos decidir qual deles:
    private static void selecionarRobo(Scanner entrada, Ambiente ambiente1, RoboCavador roboCavador1, RoboCavador roboCavador2, RoboObstaculoTerrestre roboObstaculoTerrestre1, RoboObstaculoTerrestre roboObstaculoTerrestre2, RoboObstaculoAereo roboObstaculoAereo1, RoboObstaculoAereo roboObstaculoAereo2, RoboFlutuador roboFlutuador1, RoboFlutuador roboFlutuador2, CentralComunicacao centralComunicacao){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algum dos robôs abaixo:\n" +
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
                    funcoesCavador(entrada, roboCavador1, ambiente1, centralComunicacao);
                    break;
                case 2:
                    funcoesCavador(entrada, roboCavador2, ambiente1, centralComunicacao);
                    break; 
                case 3:
                    funcoesObstaculoTerrestre(entrada, roboObstaculoTerrestre1, ambiente1, centralComunicacao);
                    break; 
                case 4:
                    funcoesObstaculoTerrestre(entrada, roboObstaculoTerrestre2, ambiente1, centralComunicacao);
                    break;  
                case 5:
                    funcoesFlutuador(entrada, roboFlutuador1, ambiente1, centralComunicacao);
                    break; 
                case 6:
                    funcoesFlutuador(entrada, roboFlutuador2, ambiente1, centralComunicacao);
                    break; 
                case 7:
                    funcoesObstaculoAereo(entrada, roboObstaculoAereo1, ambiente1, centralComunicacao);
                    break; 
                case 8:
                    funcoesObstaculoAereo(entrada, roboObstaculoAereo2, ambiente1, centralComunicacao);
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

    // Função que realiza a comunicação entre robôs.
    private static void trocarMensagens(Scanner entrada, CentralComunicacao centralComunicacao, Robo remetente, Ambiente ambiente){
        while(true){
            try {
                if(remetente.getEstadoRobo() == EstadoRobo.DESLIGADO)
                    throw new RoboDesligadoException("O " + remetente.getNome() + " está desligado");
                System.out.println("Com qual robô você quer interagir?\n" + 
                "[1] - Robo Cavador 1\n" +
                "[2] - Robo Cavador 2\n" +
                "[3] - Robo Obstáculo Terrestre 1\n" +
                "[4] - Robo Obstáculo Terrestre 2\n" +
                "[5] - Robo Flutuador 1\n" +
                "[6] - Robo Flutuador 2\n" +
                "[7] - Robo Obstáculo Aéreo 1\n" +
                "[8] - Robo Obstáculo Aéreo 2\n");
                int opcao = entrada.nextInt();
                entrada.nextLine();
                Robo destinatario = null;
                switch (opcao) {
                    case 1:
                        destinatario = ambiente.getDestinatario("RC01");
                        break;
                    case 2:
                        destinatario = ambiente.getDestinatario("RC02");
                        break; 
                    case 3:
                        destinatario = ambiente.getDestinatario("ROT01");
                        break; 
                    case 4:
                        destinatario = ambiente.getDestinatario("ROT02");
                        break;  
                    case 5:
                        destinatario = ambiente.getDestinatario("RF01");
                        break; 
                    case 6:
                        destinatario = ambiente.getDestinatario("RF02");
                        break; 
                    case 7:
                        destinatario = ambiente.getDestinatario("ROA01");
                        break; 
                    case 8:
                        destinatario = ambiente.getDestinatario("ROA02");
                        break;    

                    default:
                    System.out.println("Valor inválido, digite novamente\n");
                        break;
                }
            
                System.out.println("Digite a mensagem");
                String mensagem = entrada.nextLine();
                if(destinatario.getEstadoRobo() == EstadoRobo.DESLIGADO)
                    throw new RoboDesligadoException("O " + destinatario.getNome() + " (destinatário) está desligado e não pode receber a mensagem");
                remetente.enviarMensagem(centralComunicacao, destinatario, mensagem);
                break;
            } catch (ErroComunicacaoException e){
                System.err.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente.\n");
            } catch(RoboDesligadoException e){
                System.err.println("Erro: " + e.getMessage()+ "\n");
                break;
            }
        }
    }

    // Permite a escolha de qualquer ação do robô cavador
    private static void funcoesCavador(Scanner entrada, RoboCavador robo, Ambiente ambiente, CentralComunicacao centralComunicacao){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algumas das acoes abaixo:\n" +
            "[1] - Visualizar status (estado, posição e vida)\n" +
            "[2] - Ligar/Desligar\n" +
            "[3] - Movimentar\n" +
            "[4] - Cavar\n" +
            "[5] - Enviar mensagem para um Robô\n" +
            "[6] - Visualizar mensagens recebidas\n" +
            "[7] - Atacar\n" +
            "[8] - Destruir o obstáculo mais próximo ao robô \n" +
            "[9] - Utilizar sensores\n" +
            "[0] - Voltar\n");
    
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    robo.vizualizarStatus();
                    break;
                case 2:
                    ligarDesligar(robo);
                    break;
                case 3:
                    movimentarCavador(entrada, robo, ambiente);
                    break;
                case 4:
                    try{
                        robo.executarTarefa(entrada, ambiente, 0, 0, 0, 0);
                    }catch (ForaDosLimitesException e){
                        System.err.println("Erro: " + e.getMessage());
                    }catch(ColisaoException e){
                        System.err.println("Erro: " + e.getMessage());
                    } catch(RoboDesligadoException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                    break; 
                case 5:
                    trocarMensagens(entrada, centralComunicacao, robo, ambiente);
                    break; 
                case 6:
                    robo.visualizarMensagens();
                    break;  
                case 7:
                    try{
                        robo.atacar(ambiente);
                    } catch (RoboDesligadoException | VidaNulaException | AreaProtegidaException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                    break; 
                case 8:
                    try{
                        robo.getObstaculoMaisProx(ambiente);
                    }catch (RoboDesligadoException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                    break;
                case 9:
                    try{
                        robo.acionarSensores(ambiente, 1);
                    } catch (RoboDesligadoException | VidaNulaException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
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

    // Permite a escolha de qualquer ação do robô obstáculo terrestre.
    private static void funcoesObstaculoTerrestre(Scanner entrada, RoboObstaculoTerrestre robo, Ambiente ambiente, CentralComunicacao centralComunicacao){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algumas das acoes abaixo:\n" +
            "[1] - Visualizar status (estado, posição e vida)\n" +
            "[2] - Ligar/Desligar\n" +
            "[3] - Movimentar\n" +
            "[4] - Posicionar Bloco\n" +
            "[5] - Enviar mensagem para um Robô\n" +
            "[6] - Visualizar mensagens recebidas\n" +
            "[7] - Curar\n" +
            "[8] - Utilizar sensores\n" +
            "[0] - Voltar\n");
    
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    robo.vizualizarStatus();
                    break;
                case 2:
                    ligarDesligar(robo);
                    break;
                case 3:
                    movimentarObstaculoTerrestre(entrada, robo, ambiente);
                    break;
                case 4:
                    try{
                        robo.executarTarefa(entrada, ambiente, 0, 0, 0, 0);
                    }catch (ForaDosLimitesException e){
                        System.err.println("Erro: " + e.getMessage());
                    } catch(ColisaoException e){
                        System.err.println("Erro: " + e.getMessage());
                    } catch(RoboDesligadoException | VidaNulaException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                    break; 
                case 5:
                    trocarMensagens(entrada, centralComunicacao, robo, ambiente);
                    break; 
                case 6:
                    robo.visualizarMensagens();
                    break;
                case 7:
                    try{
                        robo.curar(ambiente);
                    } catch (RoboDesligadoException | VidaNulaException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                    break; 
                case 8:
                    try{
                        robo.acionarSensores(ambiente, 1);
                    } catch (RoboDesligadoException | VidaNulaException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
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

    // Permite a escolha de qualquer ação do robô flutuador.
    private static void funcoesFlutuador(Scanner entrada, RoboFlutuador robo, Ambiente ambiente, CentralComunicacao centralComunicacao){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algumas das acoes abaixo:\n" +
            "[1] - Visualizar status (estado, posição e vida)\n" +
            "[2] - Ligar/Desligar\n" +
            "[3] - Movimentar\n" +
            "[4] - Enviar mensagem para um Robô\n" +
            "[5] - Visualizar mensagens recebidas\n" +
            "[6] - Curar\n" +
            "[7] - Utilizar sensores\n" +
            "[0] - Voltar\n");
    
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    robo.vizualizarStatus();
                    break;
                case 2:
                    ligarDesligar(robo);
                    break;
                case 3:
                    movimentarFlutuador(entrada, robo, ambiente);
                    break;
                case 4:
                    trocarMensagens(entrada, centralComunicacao, robo, ambiente);
                    break;  
                case 5:
                    robo.visualizarMensagens();
                    break;
                case 6:
                    try{
                        robo.curar(ambiente);
                    } catch (RoboDesligadoException | VidaNulaException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                    break; 
                case 7:
                    try{
                        robo.acionarSensores(ambiente, 2);
                    } catch (RoboDesligadoException | VidaNulaException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
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

    // Permite a escolha de qualquer ação do robô obstáculo aéreo.
    private static void funcoesObstaculoAereo(Scanner entrada, RoboObstaculoAereo robo, Ambiente ambiente, CentralComunicacao centralComunicacao){
        boolean continuar = true;

        while(continuar){
            System.out.println("Selecione algumas das acoes abaixo:\n" +
            "[1] - Visualizar status (estado, posição e vida)\n" +
            "[2] - Ligar/Desligar\n" +
            "[3] - Movimentar\n" +
            "[4] - Posicionar Nuvem\n" +
            "[5] - Enviar mensagem para um Robô\n" +
            "[6] - Visualizar mensagens recebidas\n" +
            "[7] - Atacar\n" +
            "[8] - Utilizar sensores\n" +
            "[0] - Voltar\n");
    
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    robo.vizualizarStatus();
                    break;
                case 2:
                    ligarDesligar(robo);
                    break;
                case 3:
                    movimentarObstaculoAereo(entrada, robo, ambiente);
                    break;
                case 4:
                    try{
                        robo.executarTarefa(entrada, ambiente, 0, 0, 0, 0);
                    }catch (ForaDosLimitesException e){
                        System.err.println("Erro: " + e.getMessage());
                    } catch(ColisaoException e){
                        System.err.println("Erro: " + e.getMessage());
                    } catch(RoboDesligadoException | VidaNulaException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                    break; 
                case 5:
                    trocarMensagens(entrada, centralComunicacao, robo, ambiente);
                    break; 
                case 6:
                    robo.visualizarMensagens();
                    break;
                case 7:
                    try{
                        robo.atacar(ambiente);
                    } catch (RoboDesligadoException | VidaNulaException | AreaProtegidaException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
                    break; 
                case 8:
                    try{
                        robo.acionarSensores(ambiente, 2);
                    } catch (RoboDesligadoException | VidaNulaException e){
                        System.err.println("Erro: " + e.getMessage());
                    }
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

    // Liga ou desliga um robô.
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
                    throw new RoboDesligadoException("O " + robo.getNome() + " está desligado");
                System.out.print("Informe quantos metros o robô Cavador irá mover:\n" +"Na direção x: ");
                int deltaX = entrada.nextInt();

                System.out.print("Na direção y: ");
                int deltaY = entrada.nextInt();

                System.out.print("Informe a velocidade: ");
                int velocidade = entrada.nextInt();

                robo.conferirVelocidade(velocidade);
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
                    throw new RoboDesligadoException("O " + robo.getNome() + " está desligado");
                System.out.print("Informe quantos metros o robô Obstáculo Terrestre irá mover:\n" +"Na direção x: ");
                int deltaX = entrada.nextInt();

                System.out.print("Na direção y: ");
                int deltaY = entrada.nextInt();

                System.out.print("Informe a velocidade: ");
                int velocidade = entrada.nextInt();

                robo.conferirVelocidade(velocidade);
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

    // Movimento do robô flutuador.
    private static void movimentarFlutuador(Scanner entrada, RoboFlutuador robo, Ambiente ambiente1){
        while (true) {
            try {
                if(robo.getEstadoRobo() == EstadoRobo.DESLIGADO)
                    throw new RoboDesligadoException("O " + robo.getNome() + " está desligado");
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

                robo.executarTarefa(entrada, ambiente1, deltaX, deltaY, deltaZ, voo);
                break; // movimento válido, sai do loop

            } 
            catch (RoboDesligadoException | VidaNulaException e){
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
                    throw new RoboDesligadoException("O " + robo.getNome() + " está desligado");
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
