
package rutacritica;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class RutaCritica extends JFrame implements ActionListener{

    public JLabel label1 = new JLabel("Grafo:");
    public JLabel label2 = new JLabel("Lista de adyacencia:");
    public JLabel label3 = new JLabel("Informacion de la ruta critica:");
    
    JScrollPane scrollPane = new JScrollPane();
    JScrollPane scrollPane1 = new JScrollPane();
    
    JScrollPane scrollPane2 = new JScrollPane();
    JScrollPane scrollPane3 = new JScrollPane();
    
    JScrollPane scrollPane4 = new JScrollPane();
    JScrollPane scrollPane5 = new JScrollPane();
    
    public JButton botonIngresar = new JButton("Dibujar y Calcular");
    public JButton botonReiniciar = new JButton("Limpiar Grafo");
    
    JTextField[][] tabla;
    ArrayList<JLabel> numeros = new ArrayList<JLabel>(); 
    Nodo listaGrafo[];
    Nodo listaInvertida[];
    ArrayList <Nodo> rutaCritica = new ArrayList<>();
    ArrayList <Integer> rutaNumeros = new ArrayList<>();
    int cantidadNodos = 0;
    
    public static void main(String[] args) {

        RutaCritica rc = new RutaCritica(); 
        rc.setBounds(0, 0, 1000, 730);
        rc.setTitle("Ruta Critica");
        rc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        rc.setVisible(true);
        
    }

    RutaCritica(){
        
        Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(scrollPane1);
        c.add(scrollPane3);
        c.add(scrollPane5);
        
        c.add(botonIngresar);
        c.add(botonReiniciar);
        
        c.add(label1);
        c.add(label2);
        c.add(label3);
        
        botonIngresar.addActionListener(this);
        botonIngresar.setBackground(Color.CYAN);
        botonIngresar.setBounds(40, 40, 310, 40);
        botonReiniciar.addActionListener(this);
        botonReiniciar.setBackground(Color.YELLOW);
        botonReiniciar.setBounds(40, 100, 310, 40);
        
        label1.setBounds(400, 20, 200, 20);
        label2.setBounds(400, 350, 500, 20);
        label3.setBounds(40, 150, 500, 20);
        
        scrollPane.setBounds(400, 40, 2500, 2500);
        scrollPane.setPreferredSize(new Dimension(2500, 2500));  
        scrollPane.setBackground(Color.WHITE);
        
        scrollPane1.setBounds(400, 40, 540, 300);
        scrollPane1.setPreferredSize(new Dimension(1150, 400)); 
        scrollPane1.setBackground(Color.WHITE);
        
        scrollPane2.setBounds(750, 150, 2500, 2500);
        scrollPane2.setPreferredSize(new Dimension(2500, 2500));  
        scrollPane2.setBackground(Color.WHITE);
        
        scrollPane3.setBounds(400, 370, 540, 300);
        scrollPane3.setPreferredSize(new Dimension(1150, 400)); 
        scrollPane3.setBackground(Color.WHITE);        
        
        scrollPane4.setBounds(25, 150, 500, 4000);
        scrollPane4.setPreferredSize(new Dimension(500, 4000));  
        scrollPane4.setBackground(Color.WHITE);
        
        scrollPane5.setBounds(40, 180, 310, 490);
        scrollPane5.setPreferredSize(new Dimension(1150, 400)); 
        scrollPane5.setBackground(Color.WHITE);
        
        scrollPane1.addMouseListener(new MouseAdapter() {
            
            @Override 
            public void mousePressed(MouseEvent e) {
                
                if(cantidadNodos<9){
                
                    scrollPane1.removeAll();

                    cantidadNodos++;

                    JLabel tempLabel = new JLabel(Integer.toString(cantidadNodos));

                    ImageIcon imgIcon = new ImageIcon(getClass().getResource(Integer.toString(cantidadNodos)+".png"));

                    Image imgEscalada = imgIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
                    Icon iconoEscalado = new ImageIcon(imgEscalada);

                    tempLabel.setIcon(iconoEscalado);            
                    tempLabel.setBounds(e.getX(), e.getY(), 15, 15);
                    tempLabel.setForeground(Color.blue);

                    numeros.add(tempLabel);

                    for(int i = 0; i < numeros.size(); i++){

                        scrollPane1.add(numeros.get((numeros.size() - i) - 1));

                    }

                    scrollPane1.repaint();

                    dibujarTabla();

                }
                
            }
            
        });
        
    }
    
    private void dibujarTabla(){
        
        scrollPane2.removeAll();
        
        tabla = new JTextField[cantidadNodos][cantidadNodos];
        
        for(int i = 0; i < cantidadNodos; i++){
            
            for(int j = 0; j < cantidadNodos; j++){
                
                tabla[i][j] = new JTextField();
                tabla[i][j].setBounds(50 + (i*60), 25 + (j*30), 50, 20);
                scrollPane2.add(tabla[i][j]);
                
            }
            
        }
        
        for(int k = 0; k < cantidadNodos; k++){
                    
            JLabel tempLabel = new JLabel(Integer.toString(k+1) + " ===>");
            tempLabel.setBounds( 5, 25 + (k*30), 50, 20);
            scrollPane2.add(tempLabel);
            
        }
        
        scrollPane2.repaint();
        scrollPane3.setViewportView(scrollPane2);
        
    }
    
    public void dibujarListas(){
    
        JLabel texto = new JLabel("Grafo original:");
        texto.setBounds(20, 20, 500, 20);
        texto.setForeground(Color.BLUE);
        scrollPane4.add(texto);
        
        int coorY = 0;
        
        for(int i = 0 ; i < cantidadNodos; i++){
            
            String cadena = "";
            
            for(int j = 0; j < listaGrafo[i].hijos.size(); j++){
                
                cadena = cadena + listaGrafo[i].hijos.get(j).getLlave() + ", " + listaGrafo[i].hijos.get(j).getActividad() + ", " + listaGrafo[i].hijos.get(j).getCoste() + " ===> " ;
                
            }
            
            coorY =  40 + (i*30);
            JLabel tempLabel = new JLabel(listaGrafo[i].getLlave() + ":   " + cadena + "*");
            tempLabel.setBounds(20, coorY, 1500, 20);

            scrollPane4.add(tempLabel);
            
        }

        coorY = 50 + coorY;
        
        JLabel texto2 = new JLabel("Grafo Invertido:");
        texto2.setBounds(20, coorY, 500, 20);
        texto2.setForeground(Color.ORANGE);
        scrollPane4.add(texto2);
        
        for(int i = 0 ; i < cantidadNodos; i++){
            
            String cadena = "";
            
            for(int j = 0; j < listaInvertida[i].hijos.size(); j++){
                
                cadena = cadena + listaInvertida[i].hijos.get(j).getLlave() + ", " + listaInvertida[i].hijos.get(j).getActividad() + ", " + listaInvertida[i].hijos.get(j).getCoste() + " ===> " ;
                
            }
            
            JLabel tempLabel = new JLabel(listaInvertida[i].getLlave() + ":   " + cadena + "*");
            
            coorY = coorY + 30;
            
            tempLabel.setBounds(20, coorY, 1500, 20);

            scrollPane4.add(tempLabel);
            
        }
        
        coorY = 50 + coorY;
        
        JLabel texto3 = new JLabel("Ruta critica:");
        texto3.setBounds(20, coorY, 1000, 20);
        texto3.setForeground(Color.RED);
        scrollPane4.add(texto3);
        
        String ruta = "", actividades = "";
        
        for(int i = 0; i < rutaCritica.size(); i++){
            
            ruta = ruta + rutaCritica.get(i).getLlave() + "==>";

            for(int j = 0 ; j < rutaCritica.get(i).getHijos().size(); j++){
                
                if(rutaNumeros.contains(rutaCritica.get(i).getHijos().get(j).getLlave())){
                    
                    actividades = actividades + "A" + rutaCritica.get(i).getHijos().get(j).getActividad() + ", ";
                
                }
    
            }
            
        }
        
        coorY = 30 + coorY;
        
        JLabel texto4 = new JLabel("Eventos (nodos): " + ruta + " *");
        texto4.setBounds(20, coorY, 1000, 20);
        scrollPane4.add(texto4);
        
        coorY = 30 + coorY;
        
        JLabel texto5 = new JLabel("Actividades: " + actividades);
        texto5.setBounds(20, coorY, 1000, 20);
        scrollPane4.add(texto5);
        
    }
    
    public void dibujarGrafo(){
        
        JLabel padre = new JLabel(), hijo = new JLabel(), predecesor = new JLabel();
        String cadena; 
        
        for(int i = 0 ; i < numeros.size(); i++){
            
            Nodo tempPadre = listaGrafo[i];
            
            for(int j = 0; j< tempPadre.getHijos().size(); j++){
            
                NodoHijo tempHijo = tempPadre.getHijos().get(j); 
                
                for(int k = 0 ; k < numeros.size(); k++){
                   
                    if(numeros.get(k).getText().equals(Integer.toString(tempHijo.getLlave()))){
                   
                        hijo = numeros.get(k);
                       
                    }
                   
                    if(numeros.get(k).getText().equals(Integer.toString(tempPadre.getLlave()))){
                   
                        padre = numeros.get(k);
                       
                    }
                
                }
                
                int coorY = 0;
                
                int aciertos = 0;
                
                for(int k = 0; k < rutaCritica.size(); k++){
                    
                    if( (rutaCritica.get(k).getLlave() == Integer.parseInt(padre.getText())) || (rutaCritica.get(k).getLlave() == Integer.parseInt(hijo.getText())) ){
                    
                        aciertos++;
                        
                    }
                       
                }
                
                if(aciertos == 2){ 
                    
                    cadena = "r";
                    
                } else {
                
                    cadena = "v";
                    
                }
                
                if(padre.getY() >= hijo.getY()){

                    cadena = cadena + "Arriba";
                    coorY = padre.getY() - Math.abs(padre.getY() - hijo.getY());
                    
                } else {

                    cadena = cadena + "Abajo";
                    coorY = padre.getY();
                    
                }

                if(padre.getX() <= hijo.getX()){

                    cadena = cadena + "Adelante.png";
                    predecesor = padre;

                } else {

                    cadena = cadena + "Atras.png";
                    predecesor = hijo;

                }

                JLabel img = new JLabel();

                ImageIcon imgIcon = new ImageIcon(getClass().getResource(cadena));

                Image imgEscalada = imgIcon.getImage().getScaledInstance(Math.abs(padre.getX() - hijo.getX()), Math.abs(padre.getY() - hijo.getY()), Image.SCALE_SMOOTH);
                Icon iconoEscalado = new ImageIcon(imgEscalada);

                img.setIcon(iconoEscalado);
                img.setBounds(predecesor.getX(), coorY, Math.abs(padre.getX() - hijo.getX()), Math.abs(padre.getY() - hijo.getY()));

                scrollPane1.add(img);

            }
            
        }
        
        for (int i = 0 ; i < cantidadNodos; i++){
            
            JLabel tempTemprano = new JLabel(Integer.toString(listaGrafo[i].getTiempoTemprano()));          
            tempTemprano.setForeground(Color.red);
            tempTemprano.setBounds(numeros.get(i).getX(), numeros.get(i).getY() - 25, 20, 20);
            
            JLabel tempTardio = new JLabel(Integer.toString(listaGrafo[i].getTiempoTardio()));
            tempTardio.setForeground(Color.blue);
            tempTardio.setBounds(numeros.get(i).getX(), numeros.get(i).getY() + 25, 20, 20);
            
            scrollPane1.add(tempTemprano);
            scrollPane1.add(tempTardio);
            
        }
        
        scrollPane1.repaint();
                
    }
    
    public void calcularTempranos(Nodo nodo){
        
        for(int i = 0; i < nodo.getHijos().size(); i++){
            
            if(listaGrafo[nodo.getHijos().get(i).getLlave()-1].getTiempoTemprano() <= (nodo.getTiempoTemprano() + nodo.getHijos().get(i).getCoste())){
            
                listaGrafo[nodo.getHijos().get(i).getLlave()-1].setTiempoTemprano((nodo.getTiempoTemprano() + nodo.getHijos().get(i).getCoste()));
             
                calcularTempranos(listaGrafo[nodo.getHijos().get(i).getLlave()-1]);
                
            }
            
        }
        
    }
    
    public void completarInversa(){
        
        for(int i = 0; i < cantidadNodos; i++){
            
            listaInvertida[i].setTiempoTemprano(listaGrafo[listaInvertida[i].getLlave()-1].getTiempoTemprano());
            
        }
        
    }
    
    public void calcularTardios(Nodo nodo){
        
        for(int i = 0; i < nodo.getHijos().size(); i++){
    
            if((nodo.getTiempoTardio() - nodo.getHijos().get(i).getCoste()) <= listaInvertida[nodo.getHijos().get(i).getLlave()-1].getTiempoTardio()){
            
                listaInvertida[nodo.getHijos().get(i).getLlave()-1].setTiempoTardio( (nodo.getTiempoTardio() - nodo.getHijos().get(i).getCoste()) );
             
                calcularTardios(listaInvertida[nodo.getHijos().get(i).getLlave()-1]);
                
            }
            
        }
        
    }
    
    public void completarOriginal(){
        
        for(int i = 0; i < cantidadNodos; i++){
            
            listaGrafo[i].setTiempoTardio(listaInvertida[listaGrafo[i].getLlave()-1].getTiempoTardio());
            
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource() == botonIngresar && cantidadNodos != 0){
        
            listaGrafo = new Nodo [cantidadNodos];
            listaInvertida = new Nodo [cantidadNodos];
            
            for(int i = 0; i < cantidadNodos; i++){
                
                listaGrafo[i] = new Nodo(i+1, new ArrayList()); 
                listaInvertida[i] = new Nodo(i+1, new ArrayList()); 
                
            }
            
            for(int i = 0; i < cantidadNodos; i++){
                
                for(int j = 0; j < cantidadNodos; j++){
                    
                    if(!tabla[j][i].getText().equals("")){
                    
                        String [] tempCadena = tabla[j][i].getText().split(",");
                        listaGrafo[i].getHijos().add(new NodoHijo(Integer.parseInt(tempCadena[0]), Integer.parseInt(tempCadena[1]), Integer.parseInt(tempCadena[2])));
                        listaInvertida[Integer.parseInt(tempCadena[0]) - 1].getHijos().add(new NodoHijo((i+1), Integer.parseInt(tempCadena[1]), Integer.parseInt(tempCadena[2])));
                        
                    } else {
                        
                        break;
                        
                    }

                }
                                
            }
            
            scrollPane4.removeAll();
            
            calcularTempranos(listaGrafo[0]);
            completarInversa();
            
            listaInvertida[cantidadNodos-1].setTiempoTardio(listaGrafo[cantidadNodos-1].getTiempoTemprano());
            
            calcularTardios(listaInvertida[cantidadNodos-1]);
            completarOriginal();
            
            for(int i = 0; i < cantidadNodos; i++){
                
                if(listaGrafo[i].getTiempoTardio() == listaGrafo[i].getTiempoTemprano()){
                
                    rutaCritica.add(listaGrafo[i]);
                    rutaNumeros.add(listaGrafo[i].getLlave());
                }
                
            }
            
            dibujarListas();
            dibujarGrafo();
            scrollPane4.repaint();
            scrollPane5.setViewportView(scrollPane4);
            
            scrollPane.removeAll();
 
        }
    
    }
    
}
