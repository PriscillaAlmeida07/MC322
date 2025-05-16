
import java.util.ArrayList;

public class CentralComunicacao {
    private final ArrayList<String> mensagens;

    public CentralComunicacao() {
        mensagens = new ArrayList<>();
    }

    public void registrarMensagens(String remetente, String mensagem){
        mensagens.add(remetente + ": " + mensagem);

    }

    public void exibirMensagens(){
        for(int i=0; i< mensagens.size(); i++){
            System.out.println(mensagens.get(i));
        }
    }

    
}
