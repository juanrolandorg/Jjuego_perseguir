public abstract class Entidad {
    int x;
    int y;
    String simbolo;
    
    public Entidad(int x, int y, String simbolo) {
        this.x = x;
        this.y = y;
        this.simbolo = simbolo;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public String getSimbolo() {
        return simbolo;
    }
    
    public abstract void mover(Tablero tablero);
    
    public boolean colisiona(Entidad otra) {
        return this.x == otra.x && this.y == otra.y;
    }
    
    protected boolean posicionValida(int nuevoX, int nuevoY, Tablero tablero) {
        return nuevoX >= 0 && nuevoX < tablero.getAncho() 
            && nuevoY >= 0 && nuevoY < tablero.getAlto();
    }
}
