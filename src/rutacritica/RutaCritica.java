
package rutacritica;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
        
        scrollPane.setBounds(25, 150, 2500, 2500);
        scrollPane.setPreferredSize(new Dimension(2500, 2500));  
        scrollPane.setBackground(Color.gray);
        
        scrollPane1.setBounds(400, 40, 540, 300);
        scrollPane1.setPreferredSize(new Dimension(1150, 400)); 
        scrollPane1.setBackground(Color.gray);
        
        scrollPane2.setBounds(750, 150, 2500, 2500);
        scrollPane2.setPreferredSize(new Dimension(2500, 2500));  
        scrollPane2.setBackground(Color.gray);
        
        scrollPane3.setBounds(400, 370, 540, 300);
        scrollPane3.setPreferredSize(new Dimension(1150, 400)); 
        scrollPane3.setBackground(Color.gray);        
        
        scrollPane4.setBounds(25, 150, 2500, 2500);
        scrollPane4.setPreferredSize(new Dimension(2500, 2500));  
        scrollPane4.setBackground(Color.gray);
        
        scrollPane5.setBounds(40, 180, 310, 490);
        scrollPane5.setPreferredSize(new Dimension(1150, 400)); 
        scrollPane5.setBackground(Color.gray);
        
        scrollPane1.addMouseListener(new MouseAdapter() {
            
            @Override 
            public void mousePressed(MouseEvent e) {
                
                scrollPane1.removeAll();
                
                cantidadNodos++;
                
                JLabel tempLabel = new JLabel(Integer.toString(cantidadNodos));
                tempLabel.setBounds(e.getX(), e.getY(), 20, 20);
                
                numeros.add(tempLabel);
                
                for(int i = 0; i < numeros.size(); i++){
                    
                    scrollPane1.add(numeros.get((numeros.size() - i) - 1));
                    
                }
                
                scrollPane1.repaint();

                dibujarTabla();
                
//                System.out.println(e.getX() + "," + e.getY());


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
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
    
    }
    
}
