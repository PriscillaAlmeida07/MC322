import java.util.ArrayList;

public class CentralComunicacao {

    private final ArrayList<String> mensagens;

    // Construtor.
    public CentralComunicacao() {
        mensagens = new ArrayList<>();
    }

    // Registra uma nova mensagem trocada.
    public void registrarMensagens(String remetente, String mensagem){
        mensagens.add(remetente + ": " + mensagem);
    }

    // Lista todas as mensagens trocadas.
    public void exibirMensagens(){
        for(int i=0; i< mensagens.size(); i++){
            System.out.println(mensagens.get(i));
        }
        System.out.print("\n");
    }
}
