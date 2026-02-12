
public class Main {
    public static void main(String[] args) {
        // Config
        final int ANCHO = 120;
        final int ALTO = 10;
        final int NUM_CAZADORES = (int) (Math.random()*10)+1;
        final int NUM_PRESAS = (int) (Math.random()*10)+1;;
        final int VELOCIDAD = 750;

        // Crear el tablero
        Tablero tablero = new Tablero(ANCHO, ALTO);

        System.out.println("Inicializando caza...\n");
        for (int i = 0; i < NUM_CAZADORES; i++) {
            int x = (int) (Math.random() * ANCHO);
            int y = (int) (Math.random() * ALTO);

            tablero.agregarCazador(new Cazador(x, y));
        }

        for (int i = 0; i < NUM_PRESAS; i++) {
            int x = (int) (Math.random() * ANCHO);
            int y = (int) (Math.random() * ALTO);
            tablero.agregarPresa(new Presa(x, y));
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("CORTE");
        }

        // Bucle principal
        boolean simulacionActiva = true;

        int turno = 0;
        while (simulacionActiva) {
            turno++;

            if (!tablero.quedanPresas()) {
                simulacionActiva = false;
                break;
            }

            // Mostrar el estado actual
            System.out.println("Turno " + turno);
            tablero.mostrar();

            // Menean ratas
            for (Presa presa : tablero.getPresas()) {
                presa.mover(tablero);
            }

            // Menean gatos
            for (Cazador cazador : tablero.getCazadores()) {
                cazador.mover(tablero);
            }

            tablero.verificarColisiones();

            try {
                Thread.sleep(VELOCIDAD);
            } catch (InterruptedException e) {
                System.out.println(" CORTE");
                break;
            }
        }

        System.out.println("Todos los ratones han sido pillados!!!");

    }
}
