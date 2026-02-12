import java.util.List;

public class Cazador extends Entidad {
    
    public Cazador(int x, int y) {
        super(x, y, "\033[31mG\033[0m");
    }
    
    
    public void mover(Tablero tablero) {
        List<Presa> presas = tablero.getPresas();
        
        Presa presaCercana = encontrarPresaMasCercana(presas);
        
        if (presaCercana != null) {
            perseguirPresa(presaCercana, tablero);
        }
    }
    
    Presa encontrarPresaMasCercana(List<Presa> presas) {
        Presa masCercana = null;
        double distanciaMinima = Double.MAX_VALUE;
        
        for (Presa presa : presas) {
            double distancia = calcularDistancia(presa);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                masCercana = presa;
            }
        }
        
        return masCercana;
    }
    
    double calcularDistancia(Entidad otra) {
        int dx = this.x - otra.getX();
        int dy = this.y - otra.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    void perseguirPresa(Presa presa, Tablero tablero) {
        int dx = presa.getX() - this.x;
        int dy = presa.getY() - this.y;
        
        int nuevoX = this.x;
        int nuevoY = this.y;
        
        //movimiento hacia la presa
        if (Math.abs(dx) > Math.abs(dy)) {
            nuevoX = this.x + (dx > 0 ? 1 : -1);
        } else if (dy != 0) {
    
            nuevoY = this.y + (dy > 0 ? 1 : -1);
        }
        
        if (posicionValida(nuevoX, nuevoY, tablero)) {
            this.x = nuevoX;
            this.y = nuevoY;
        }
    }
}
