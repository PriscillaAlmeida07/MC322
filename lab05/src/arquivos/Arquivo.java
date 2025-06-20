package arquivos;
import java.io.*;

public class Arquivo {
    File arquivo;

    // Construtor.
    public Arquivo(String caminho){
        
        // Vamos verificar se o arquivo existe, se não existir, criaremos:
        arquivo = new File(caminho);
        try {
            if (arquivo.createNewFile()) {
                System.out.print("Arquivo criado: " + arquivo.getPath() );
                System.out.print("\n");
            } else {
                System.out.print("O arquivo já existe: " + arquivo.getPath());
                System.out.print("\n");
            }

        } catch (IOException e) {
            System.out.print("Erro ao criar ou escrever no arquivo: " + e.getMessage());
            System.out.print("\n");
        }

    }

    // Função para gravar dados no arquivo.
    public void escreverNoArquivo(String conteudo){

        // FileWrite:  Novos dados serão adicionados ao final do arquivo.
        // BufferedWriter: Armazena o conteudo na memória antes de realmente gravar no arquivo. Isso deixa a escrita mais eficiente, principalmente escrevermos várias linhas.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            writer.write(conteudo);
            writer.newLine();
        } catch (IOException e) {
            System.out.print("Erro ao escrever no arquivo: " + e.getMessage());
            System.out.print("\n");
        }
    }

    // Função para ler dados do arquivo.
    public void lerArquivo(){

        // FileReader: le cada caracter do arquivo
        // BufferedReader: Armazena temporariamente partes do arquivo na memória e fornece métodos mais fáceis, como .readLine().
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            // readLine(): Pega uma linha do arquivo e só para quando nao tiver mais linha (ou seja ser = null)
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
            System.out.print("\n");
        } catch (IOException e) {
            System.out.print("Erro ao ler o arquivo: " + e.getMessage());
            System.out.print("\n");
        }
    }
}