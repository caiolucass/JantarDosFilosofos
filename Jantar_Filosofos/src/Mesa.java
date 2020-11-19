import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

// controla o acesso a um recurso compartilhado por meio do uso de um contador
import java.util.concurrent.Semaphore;

/**
 * @author Caio Lucas F. dos Santos Matrícula: 604365
 */

public class Mesa {
    private Semaphore controlador;
    private Random gerador;

    // Construtor
    public Mesa() {
        controlador = new Semaphore(1);
        gerador = new Random();
    }

    /*
     * Método para retornar o numero de fílosofos que estão pesando
     */
    public boolean comendo(String mesa) {
        System.out.println("Filósofo " + mesa + " comendo...");

        // Inicia o tempo de refeição dos fílosofos
        try {
            int time = gerador.nextInt(3);
            time = time * 1000;
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /*
     * Método para retornar o numero de fílosofos que estão pesando
     */
    public boolean pensando(String mesa) {

        System.out.println("Filósofo " + mesa + " pensando...");

        // inica o tempo de pensamento dos filosofos
        try {
            int time = gerador.nextInt(6);
            time = time * 1000;
            Thread.sleep(time);
        } catch (InterruptedException error) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, error);
        }
        return true;
    }

}
