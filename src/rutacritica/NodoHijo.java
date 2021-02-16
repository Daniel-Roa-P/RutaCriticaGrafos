
package rutacritica;

public class NodoHijo {
    
    int llave;
    int actividad;
    int coste;
    
    NodoHijo(int llave, int actividad, int coste){
        
        this.llave = llave;
        this.actividad = actividad;
        this.coste = coste;
        
    }

    public int getLlave() {
        return llave;
    }

    public int getActividad() {
        return actividad;
    }

    public int getCoste() {
        return coste;
    }
    
    
    
}
