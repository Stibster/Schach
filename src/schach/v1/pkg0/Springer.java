package schach.v1.pkg0;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Martin Borsdorf, Steve Vogel
 */
public class Springer extends Figur{

    public Springer(boolean f)
    {
        this.farbe = f;
        
        try{
            if(this.farbe)
            {
                bild = ImageIO.read(new File("springerS.png"));
            }
            else
            {
                bild = ImageIO.read(new File("springerW.png"));
            }
        }
        catch (IOException e) {
            System.out.println("Error!");
        }
    }
    
    
    public boolean zugMoeglich(SchachbrettFeld istFeld, SchachbrettFeld sollFeld, SchachbrettFeld[] felder){
     
        int dReihe = Math.abs(sollFeld.reihe-istFeld.reihe);
        int dSpalte = Math.abs(sollFeld.spalte-istFeld.spalte);
        
        
        if(!(dReihe == 2 && dSpalte == 1 || dReihe == 1 && dSpalte == 2))
        {
            return false;
        }
        
        if(!sollFeld.frei() && sollFeld.figur.farbe == this.farbe)
        {
            return false;
        }
        return true;    
    }
}
