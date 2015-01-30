package schach.v1.pkg0;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Martin Borsdorf, Steve Vogel
 */
public class Dame extends Figur{
    
    public Dame(boolean f)
    {
        this.farbe = f;

        try{
            if(this.farbe)
            {
                bild = ImageIO.read(new File("dameS.png"));
            }
            else
            {
                bild = ImageIO.read(new File("dameW.png"));
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
        
        if(!(sollFeld.reihe == istFeld.reihe || sollFeld.spalte == istFeld.spalte) && !(dReihe == dSpalte && dSpalte != 0))
        {
            return false;
        }
        
        return setzenMoeglich(istFeld,sollFeld, felder);
    }
    
}
