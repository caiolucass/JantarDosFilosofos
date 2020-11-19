
// controla o acesso a um recurso compartilhado por meio do uso de um contador
import java.util.concurrent.Semaphore;

/**
 * @author Caio Lucas F. dos Santos Matrícula: 604365
 */

public class Filosofo implements Runnable {
    private Mesa mesa; // mesa de jantar
    private int comeu, pensou; // contador de pensamentos e refeições
    private Semaphore garfoEsquerdo, garfoDireito; // gerenciador para ver se o garfo esta disponivel
    private String nome; // nome dos filósofos
    private boolean acao; // acção dos filosófos

    // Construtor
    public Filosofo(String nome, Mesa mesa, Semaphore esquerdo, Semaphore direito) {
        this.nome = nome;
        this.mesa = mesa;
        comeu = 0;
        pensou = 0;
        garfoEsquerdo = esquerdo;
        garfoDireito = direito;
        acao = true;
    }

    // obter o grafo esquerdo
    public Semaphore getGarfoEsquerdo() {
        return garfoEsquerdo;
    }

    // obter o grafo direito
    public Semaphore getGarfoDireito() {
        return garfoDireito;
    }

    // obter o numero de refeições
    public int getVezesComeu() {
        return comeu;
    }

    // obter o numero de pensamentos
    public int getVezesPensou() {
        return pensou;
    }

    // setar o valor da chave
    public void setAcao() {
        acao = false;
    }

    // obter o nome dos filosofos
    public String getNome() {
        return nome;
    }

    /*
     * Método para acionar a ação de comer com os garfos
     */
    @Override
    public void run() {
        while (acao) {
            // tenta pegar o garfo com a mão direita
            if (garfoDireito.tryAcquire()) {

                // tenta pegar o garfo com a mão esquerda
                if (garfoEsquerdo.tryAcquire()) {

                    // comendo com os dois garfos
                    mesa.comendo(nome);
                    comeu++;
                    garfoEsquerdo.release();
                    garfoDireito.release();
                } else {
                    // Soltar garfo da mão direita
                    garfoDireito.release();
                }
            } else {
                // Garfos ocupados
                mesa.pensando(nome);
                pensou++;
            }
        }
    }

    /*
     * Método para inicar a classe dos filosofos
     */
    void Iniciar() {
        throw new UnsupportedOperationException("Erro. Não suportado!");
    }

}
