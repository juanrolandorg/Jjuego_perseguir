import java.util.List;
import java.util.Random;

public class Presa extends Entidad {
    private Random random;
    
    public Presa(int x, int y) {
        super(x, y, "\033[34mR\033[0m");
        this.random = new Random();
    }
    
    public void mover(Tablero tablero) {
        List<Cazador> cazadores = tablero.getCazadores();
        
        // Buscar el cazador más cercano
        Cazador cazadorCercano = encontrarCazadorMasCercano(cazadores);
        
        if (cazadorCercano != null) {
            huirDeCazador(cazadorCercano, tablero);
        } else {
            moverAleatoriamente(tablero);
        }
    }
    
    private Cazador encontrarCazadorMasCercano(List<Cazador> cazadores) {
        Cazador masCercano = null;
        double distanciaMinima = Double.MAX_VALUE;
        
        for (Cazador cazador : cazadores) {
            double distancia = calcularDistancia(cazador);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                masCercano = cazador;
            }
        }
        
        return masCercano;
    }
    
    private double calcularDistancia(Entidad otra) {
        int dx = this.x - otra.getX();
        int dy = this.y - otra.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    private void huirDeCazador(Cazador cazador, Tablero tablero) {
        int dx = this.x - cazador.getX();
        int dy = this.y - cazador.getY();
        
        // Se va en direcc contraria
        int nuevoX = this.x;
        int nuevoY = this.y;
        
        if (dx != 0) {
            nuevoX = this.x + (dx > 0 ? 1 : -1);
        }
        if (dy != 0) {
            nuevoY = this.y + (dy > 0 ? 1 : -1);
        }
        
        if (posicionValida(nuevoX, nuevoY, tablero)) {
            this.x = nuevoX;
            this.y = nuevoY;
        } else {
            // Si no puede huir en esa dirección,  random
            moverAleatoriamente(tablero);
        }
    }
    
    public void moverAleatoriamente(Tablero tablero) {
        int direccion = random.nextInt(4);
        int nuevoX = this.x;
        int nuevoY = this.y;
        
        switch (direccion) {
            case 0: nuevoY--; break; // Arriba
            case 1: nuevoY++; break; // Abajo
            case 2: nuevoX--; break; // Izquierda
            case 3: nuevoX++; break; // Derecha
        }
        
        if (posicionValida(nuevoX, nuevoY, tablero)) {
            this.x = nuevoX;
            this.y = nuevoY;
        }
    }
}
