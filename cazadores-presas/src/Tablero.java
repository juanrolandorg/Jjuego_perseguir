
import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private int ancho;
    private int alto;
    private List<Cazador> cazadores;
    private List<Presa> presas;

    public Tablero(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.cazadores = new ArrayList<>();
        this.presas = new ArrayList<>();
    }

    public void agregarCazador(Cazador cazador) {
        cazadores.add(cazador);
    }

    public void agregarPresa(Presa presa) {
        presas.add(presa);
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public List<Cazador> getCazadores() {
        return cazadores;
    }

    public List<Presa> getPresas() {
        return presas;
    }

    public void mostrar() {
        // NO TOCAR ESTO, ES PA LIMPIAR EL TERMINAL
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // represento y lleno tablero
        String [][] matriz = new String[alto][ancho];

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                matriz[i][j] = " ";
            }
        }

        for (Presa presa : presas) {
            if (presa.getX() >= 0 && presa.getX() < ancho
                    && presa.getY() >= 0 && presa.getY() < alto) {
                matriz[presa.getY()][presa.getX()] = presa.getSimbolo();
            }
        }

        for (Cazador cazador : cazadores) {
            if (cazador.getX() >= 0 && cazador.getX() < ancho
                    && cazador.getY() >= 0 && cazador.getY() < alto) {
                matriz[cazador.getY()][cazador.getX()] = cazador.getSimbolo();
            }
        }

        // Mostrar el tablero
        for (int i = 0; i < ancho; i++) {
            System.out.print("-");
        }
        System.out.println(); //

        for (int i = 0; i < alto; i++) {
            System.out.print("");
            for (int j = 0; j < ancho; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println("");
        }
        for (int i = 0; i < ancho; i++) {
            System.out.print("-");
        }
        System.out.println(); // para saltar de línea

        System.out.println("\n Gatos (G): " + cazadores.size() + " | Ratones (R): " + presas.size());

    }

    public void verificarColisiones() {
        List<Presa> presasCapturadas = new ArrayList<>();

        for (Cazador cazador : cazadores) {
            for (Presa presa : presas) {
                if (cazador.colisiona(presa)) {
                    presasCapturadas.add(presa);
                    System.out.println("\n¡Raton atrapado en (" + presa.getX() + ", " + presa.getY() + ")!");
                }
            }
        }

        // Eliminar presas capturadas
        presas.removeAll(presasCapturadas);
    }

    public boolean quedanPresas() {
        if (presas.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
