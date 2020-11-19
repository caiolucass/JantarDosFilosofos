import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * @author Caio Lucas F. dos Santos Matrícula: 604365
 */

public class App {
    public static void main(String[] args) throws InterruptedException {
        int i; // contador
        int numeroFilosofos = 3; // quantidade de filosófos

        System.out.println("Digite o número de filosofos que estão na mesa: ");
        Scanner teclado = new Scanner(System.in);
        numeroFilosofos = teclado.nextInt();

        ArrayList<Filosofo> lista = new ArrayList<>(); // cria um ArrayList para armazenar os filosófos
        Mesa mesa = new Mesa(); // cria um novo Objeto mesa

        // Inserir os filósofos à mesa
        for (int x = 0; x < numeroFilosofos; x++) {

            if (x == 0) {
                lista.add(new Filosofo(x + 1 + "", mesa, new Semaphore(1), new Semaphore(1)));
                System.out.println("Filosofo " + (x + 1));
            } else if (x == numeroFilosofos - 1) {
                lista.add(new Filosofo(x + 1 + "", mesa, lista.get(x - 1).getGarfoDireito(),
                        lista.get(0).getGarfoEsquerdo()));
                System.out.println("Filosofo " + (x + 1));
            } else {
                lista.add(new Filosofo(x + 1 + "", mesa, lista.get(x - 1).getGarfoDireito(), new Semaphore(1)));
                System.out.println("Filosofo " + (x + 1));
            }
        }
        System.out.println("-- Filosofos Criados com sucesso! --\n");

        System.out.println("Digite o tempo em segundos: ");
        int timer = teclado.nextInt();

        System.out.println("-- Jantar --");

        // Liberando os filosófos da mesa
        for (int j = 0; j < numeroFilosofos; j++) {
            new Thread(lista.get(j)).start();
        }
        // Setando o tempo em segundos
        Thread.sleep(timer * 1000);

        for (int j = 0; j < numeroFilosofos; j++) {
            lista.get(j).setAcao();
        }

        System.out.println("-- Jantar Finalizado com sucesso!--\n");

        // Tempo para setar as ações dos filósofos
        Thread.sleep(3000);

        System.out.println("-- Resultado do jantar dos fílosofos --");
        for (int j = 0; j < numeroFilosofos; j++) {
            System.out.println(
                    "Filosofo " + lista.get(j).getNome() + " comeu: " + lista.get(j).getVezesComeu() + " vez(es");
            System.out.println(
                    "Filosofo " + lista.get(j).getNome() + " pensou: " + lista.get(j).getVezesPensou() + " vez(es)");
        }

    }
}
