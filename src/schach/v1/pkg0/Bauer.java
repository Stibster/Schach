package schach.v1.pkg0;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Martin Borsdorf, Steve Vogel
 */
public class Bauer extends Figur{

    public Bauer(boolean f)
    {
        this.farbe = f;
    
        try{
            if(this.farbe)
            {
                bild = ImageIO.read(new File("bauerS.png"));
            }
            else
            {
                bild = ImageIO.read(new File("bauerW.png"));
            }
        }
        catch (IOException e) 
        {
            System.out.println("Error!");
        }
    }
    
    @Override
    public boolean zugMoeglich(SchachbrettFeld istFeld, SchachbrettFeld sollFeld, SchachbrettFeld[] felder) {
    
        int dReihe = (sollFeld.reihe - istFeld.reihe);
        int dSpalte = (sollFeld.spalte - istFeld.spalte);
        
        //hier die Anweisung für die Bewegung des Bauern
        if(!(sollFeld.reihe != istFeld.reihe))
        { 
//            if(!(Math.abs(dReihe) == Math.abs(dSpalte)))
//            { 
//                System.out.println("Bauer schlägt diagonal");
//                return false;
//            }
//            if(!(dReihe == 1 && dSpalte != 0))
//            {
//                System.out.println("vorletztes if");
//                return false;
//            }
//            System.out.println("1. if");
            return false;
        }
         
        // diese Anweisung verhindert das der Bauer nicht gerade schlägt
        if(sollFeld.spalte == istFeld.spalte && sollFeld.figur != null)
        {
            System.out.println("So darf ich nicht schlagen!");
            return false;
            //Bauer kann andere Figuren zwar nicht gerade schlagen, aber dafür jede andere Figur, die eine Reihe vor ihm ist
        }
        // diese Anweisung verhindert das der Bauer sich diagonal bewegt (falls das Feld leer ist)
        if(sollFeld.spalte != istFeld.spalte && sollFeld.figur == null)
        {
            System.out.println("So darf ich mich nicht bewegen!");
            return false;
        }

        //hier wird der Spieler abgefragt ob er eine andere Figur für den Bauern haben möchte 
        //falls der Bauer das komplette Spielfeld überquert hat
        if(sollFeld.reihe == 7 || sollFeld.reihe == 0)
        {
            //TODO überprüfen ob die Dame schon draußen ist
            System.out.println("Wollen Sie eine andere Figur?");
            //Wenn ja > welche?
            //TODO Figur Wahl    
        }
        
        //diese Anweisung sorgt dafür das der Bauer nur vorwärts läuft
        if (istFeld.figur.istSchwarz() && dReihe < 0 || !istFeld.figur.istSchwarz() && dReihe > 0)
        {
            System.out.println("LÄUFT nur VORWÄRTS");
            return false;
        }
         //diese Anweisung sorgt dafür das der Bauer am Start 2 Felder gehen darf
        if(Math.abs(dReihe) > 2 || Math.abs(dReihe) > 1 && (istFeld.reihe != 1 && istFeld.reihe != 6))
        {
            System.out.println("Am start 2 Felder");
            return false;
        }
        

         
        return setzenMoeglich(istFeld,sollFeld, felder);
    }  
}
           
    
    
    


