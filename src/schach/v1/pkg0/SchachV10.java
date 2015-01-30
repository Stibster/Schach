package schach.v1.pkg0;

/**
 *
 * @author Martin Borsdorf, Steve Vogel
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SchachV10 extends javax.swing.JFrame
{  
    public static void main(String[] args) {
        
        Schachbrett sb = new Schachbrett();
        
        final JFrame fenster = new JFrame("Schach");            //Fenster anlegen 
        fenster.setSize(700, 700);                              //Fenstergröße setzen
        //fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /**
         * Das Fenster fragt nach, falls es geschlossen wird
         */
        	fenster.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                fenster.addWindowListener(new WindowAdapter() {
                @Override
                    public void windowClosing(WindowEvent evt)
                    {
                    int auswahl = JOptionPane.showConfirmDialog(fenster, "Wirklich beenden?", "Frage",JOptionPane.YES_NO_OPTION);
                    if(auswahl == JOptionPane.YES_OPTION)
                        {
                        fenster.dispose();
                        }
                    }
                });	
        
        fenster.getContentPane().add(sb); 
        //Container contentPane = fenster.getContentPane();
        //contentPane.add(myCanvas);
        fenster.setVisible(true);
        fenster.setResizable(false); 
        
        
    }
        
} 

