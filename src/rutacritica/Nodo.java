
package rutacritica;

import java.util.ArrayList;

public class Nodo {
    
    int llave;
    int tiempoTemprano;
    int tiempoTardio;
    
    ArrayList <NodoHijo> hijos = new ArrayList();
    
    Nodo(int llave, ArrayList <NodoHijo> hijos){
    
        this.llave = llave;
        this.hijos = hijos;
        
    }

    public int getTiempoTemprano() {
        return tiempoTemprano;
    }

    public void setTiempoTemprano(int tiempoTemprano) {
        this.tiempoTemprano = tiempoTemprano;
    }

    public int getTiempoTardio() {
        return tiempoTardio;
    }

    public void setTiempoTardio(int tiempoTardio) {
        this.tiempoTardio = tiempoTardio;
    }

    public void setLlave(int llave) {
        this.llave = llave;
    }

    public void setHijos(ArrayList<NodoHijo> hijos) {
        this.hijos = hijos;
    }
    
    public int getLlave() {
        return llave;
    }

    public ArrayList<NodoHijo> getHijos() {
        return hijos;
    }
    
}
