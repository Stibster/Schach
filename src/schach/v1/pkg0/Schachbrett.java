package schach.v1.pkg0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JComponent;
/**
 *
 * @author Martin Borsdorf, Steve Vogel
 */
public class Schachbrett extends JComponent implements MouseListener {

    String datei = "brett_test";
    int i, j;
    int anzahlfelder = 64;
    SchachbrettFeld[] felder;
    String[][] struktur;
    Image bild;
    int h = 80;//größe des einzelnen feldes...hier gesetzt da globale Änderung möglich
    int z;
    SchachbrettFeld istFeld, zwFeld;
    boolean farbe;

    /**
     * Der Konstruktor erstellt am Anfang das Schachbrett mit allen Schachbrettfeldern und Figuren
     * 
     */
    public Schachbrett() {
        addMouseListener(this);
        
        felder = new SchachbrettFeld[anzahlfelder];
      

        try{
            this.struktur = this.readFeldStruktur(); // Struktur auslesen
        }
        catch (IOException ex)
        {
            System.out.println("Fehler beim Laden der Struktur Datei");
            System.exit(-1);
        }
        
        for (int i = 0; i < 8; i++) {//für die "Anzahl der" Spalten
            for (int j = 0; j < 8; j++) {
                boolean feldFarbe = (i + j) % 2 == 0;//mit Modulo wird hier die Farbe für das jeweilige Feld bestimmt

                SchachbrettFeld feld = new SchachbrettFeld(feldFarbe, j, i);
                
                switch(this.struktur[i][j])
                {
                    case "TS":
                        feld.setFigur(new Turm(true));
                        break;
                    case "TW":
                        feld.setFigur(new Turm(false));
                        break;
                    case "LS":
                        feld.setFigur(new Laeufer(true));
                        break;
                    case "LW":
                        feld.setFigur(new Laeufer(false));
                        break;
                    case "PS":
                        feld.setFigur(new Springer(true));
                        break;
                    case "PW":
                        feld.setFigur(new Springer(false));
                        break;
                    case "KS":
                        feld.setFigur(new Koenig(true));
                        break;
                    case "KW": 
                        feld.setFigur(new Koenig(false)); 
                        break;
                    case "DS":
                        feld.setFigur(new Dame(true));
                        break;
                    case "DW":
                        feld.setFigur(new Dame(false));
                        break;
                    case "BS":
                        feld.setFigur(new Bauer(true));
                        break;
                    case "BW":
                        feld.setFigur(new Bauer(false));
                        break;
                    default:
                        break;
                }
                felder[z] = feld;//zählt mit
                z++;
            }
        }
    }
    
    private String[][] readFeldStruktur() throws FileNotFoundException, IOException {
        String[][] struktur = new String[8][8];

        BufferedReader b;
        b = new BufferedReader(new FileReader(this.datei));//die Strukturdatei
        for (int i = 0; i < 8; i++) {
            String line = b.readLine(); //Beispiel: "BS BS BS BS BS BS BS BS"
            struktur[i] = line.split(" ");//splitet die Zeile auf...in Elemente
        }
        return struktur;
    }
    
    @Override
    public void paintComponent(Graphics stift) {
        
        super.paintComponent(stift);
        for(SchachbrettFeld feld : felder)
        {
            int b = h;
            int x = (feld.spalte)*b;
            int y = (feld.reihe)*h;
            
            final Color white = new Color(239, 228, 176);
            final Color black = new Color(128, 64, 64);
            final Color lavender = new Color(139, 131, 134);
            final Color blau = new Color(73, 87, 237);
            //System.out.println("Zeichne Feld beginnend bei: " + x + "," + y);
            
            if(feld.isWhite())
            {
                stift.setColor(white);
            }
            else
            {
                stift.setColor(black);
            }
            
            if (feld == this.istFeld)
            {
                //System.out.println("ausgewähltes Feld gefunden");
                stift.setColor(lavender);
            }
                    
            stift.fillRect(x, y, b, h);
            
            if(feld.figur != null)
            {
                //schauen was es fuer eine Figur ist und dann entsprechende Figur zeichnen
                //System.out.println(feld.figur.bild + " gefunden auf Feld " + feld.spalte + " " + feld.reihe);
                //zeichne(feld.figur.bild);
                
                stift.drawImage(feld.figur.bild, ((b-64)/2)+x,((h-64)/2)+y, this);
                //stift.drawImage(feld.figur.bild + feld.reihe + feld.spalte);
                
            }         
        }
    }
    
    private void bewegeFigur(SchachbrettFeld istFeld, SchachbrettFeld sollFeld){
        
        sollFeld.figur = istFeld.figur;
        istFeld.figur = null;
        

    }
    
    private boolean istErsterKlick()
    {
        return this.istFeld == null;
    }
  
    
    @Override
    public void mouseClicked(MouseEvent e) {
                
        int s = e.getX()/h;//hier wird der Wert um 1 erhöht da das Feld von 0 bis 7 gezählt wird
        int r = e.getY()/h;
        int k = r * 8 + s;  //K ist Index des geklickten Feldes
        
        SchachbrettFeld geklicktesFeld = felder[k];
        
        if (geklicktesFeld.figur == null && this.istErsterKlick())//wenn in ein leeres Feld geklickt wurde
        {
            //System.out.println("Bitte klicke auf eine Figur!");
            return; // brauchen nix machen, Ende von Methode
        } 
        //System.out.println("Da steht eine Figur!!!: " + felder[k].figur.getClass());
        if(this.istErsterKlick())
        {
            istFeld = geklicktesFeld;
            System.out.println("Startfeld: " + k);
        }
        else if(geklicktesFeld == istFeld){
                
            istFeld = null;
        }

        else
        {
            
            if(istFeld.figur.zugMoeglich(istFeld, geklicktesFeld, felder))
            {
                System.out.println("Zielfeld: " + k);
                this.bewegeFigur(istFeld, geklicktesFeld);
                this.istFeld = null; 
                //Zielfeld wurde ausgewählt
            }
        }
        
        this.repaint();//ruft die paintComponent wieder auf...
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }   
}