package schach.v1.pkg0;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Martin Borsdorf, Steve Vogel
 */
public class Laeufer extends Figur{
    
    public Laeufer(boolean f)
    {
        this.farbe = f;
  
        try{
            if(this.farbe)
            {
                bild = ImageIO.read(new File("laeuferS.png"));
            }
            else
            {
                bild = ImageIO.read(new File("laeuferW.png"));
            }
        }
        catch (IOException e) {
            System.out.println("Error!");
        }
    }

    

    @Override
    public boolean zugMoeglich(SchachbrettFeld istFeld, SchachbrettFeld sollFeld, SchachbrettFeld[] felder) {
        
        int dReihe = Math.abs(sollFeld.reihe-istFeld.reihe);
        int dSpalte = Math.abs(sollFeld.spalte-istFeld.spalte);
        
        if(!(dReihe == dSpalte && dSpalte != 0))
        {
            return false;
   
        }
     return setzenMoeglich(istFeld,sollFeld, felder); 
          
//        return gibt mir dies gleich zurück, Math für den Betrag da hier das Vorzeichen nicht von bedeutung ist  
//        reihe ist x Achse(Werte) -hier Vergleich-        spalte ist Y Achse(Werte)
//        das würde auch mit if gehen, wäre aber länger
//       
    
    }
   
    
}
