
package schach.v1.pkg0;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Martin Borsdorf, Steve Vogel
 */
public class Koenig extends Figur{
    
    public Koenig(boolean f)
    {
        this.farbe = f;
        
        try{
            if(this.farbe)
            {
                bild = ImageIO.read(new File("koenigS.png"));
            }
            else
            {
                bild = ImageIO.read(new File("koenigW.png"));
            }
        }     
        catch (IOException e) {
            System.out.println("Error!");
        }
    }
    
    @Override
    public boolean zugMoeglich(SchachbrettFeld istFeld, SchachbrettFeld sollFeld, SchachbrettFeld[] felder){
    
        int dReihe = Math.abs(sollFeld.reihe-istFeld.reihe);
        int dSpalte = Math.abs(sollFeld.spalte-istFeld.spalte);
        
        if(dReihe > 1 || dSpalte > 1)
        {
            return false;
            
        }
        
        if(!(sollFeld.reihe == istFeld.reihe || sollFeld.spalte == istFeld.spalte) && !(dReihe == dSpalte && dSpalte != 0))
        {
            return false;
        }
        
        return setzenMoeglich(istFeld,sollFeld, felder);
    }
    
}
