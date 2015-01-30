package schach.v1.pkg0;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Martin Borsdorf, Steve Vogel
 */
public class Turm extends Figur{
    
   
   
   public Turm(boolean f)
   {
       this.farbe = f;
       
        try{
            if(this.farbe)
            {
                bild = ImageIO.read(new File("turmS.png"));
            }
            else
            {
                bild = ImageIO.read(new File("turmW.png"));
            }
        }
        catch (IOException e) {
            System.out.println("Error!");
        }
    }

    
   @Override
    public boolean zugMoeglich(SchachbrettFeld istFeld, SchachbrettFeld sollFeld, SchachbrettFeld[] felder) {
       
        
        if (!(sollFeld.spalte == istFeld.spalte ^ sollFeld.reihe == istFeld.reihe))//Regeln für Figur 
        {
            return false;
        }
        
        return setzenMoeglich(istFeld,sollFeld, felder);

        // herausfinden welche Reihe, Spalte (A1 -> D1 [00 -> 30])     
        // Felder zwischen Start und Ziel nach Status fragen (ODER verknüpft) (10 und 20 abfragen)
        // status zurückgeben
        
        //felder[8].figur == null && felder[16].figur == null >> true     
    }  
}

