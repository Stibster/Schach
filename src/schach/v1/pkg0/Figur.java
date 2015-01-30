package schach.v1.pkg0;

import java.awt.Image;

/**
 *
 * @author Martin Borsdorf, Steve Vogel
 */
public class Figur {
    
    //SchachbrettFeld aktuellesfeld;  
    boolean farbe; //true=Schwarz 
    Image bild;
    

    /**
     * Überprüft für die jeweilige Figur ob der Zug vom istFeld zum sollFeld möglich ist.
     *
     * @param istFeld Das Feld auf dem die Figur startet.
     * @param sollFeld Das Feld   //int z = 0;auf das die Figur gesetzt werden soll.
     * @param felder
     * @return ist der Zug möglich oder nicht.
     */
    public boolean zugMoeglich(SchachbrettFeld istFeld, SchachbrettFeld sollFeld, SchachbrettFeld[] felder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean istSchwarz()
    {
        return farbe;
    }
    
    
    
    boolean wegFrei(SchachbrettFeld istFeld, SchachbrettFeld sollFeld, SchachbrettFeld[] felder){

        int reihendiff = (sollFeld.reihe - istFeld.reihe);
        int spaltendiff = (sollFeld.spalte - istFeld.spalte);
  
        
        if(reihendiff == 0)
        {
            int richtung = spaltendiff/Math.abs(spaltendiff);// hier wird die Richtung bestimmt (Positiv oder Negativ)
                        
            for(int i = 0; Math.abs(i) < Math.abs(spaltendiff)-1; i = i + richtung) // -1 damit das Zielfeld nicht geprüft wird
            {
                int k = (8*istFeld.reihe + i + richtung + istFeld.spalte);
                
                if(!felder[k].frei())
                {
                    System.out.println("Feld: " + k + " ist belegt!");
                    return false;
                }
                //System.out.println("auf Feld: " + (8*istFeld.reihe+sollFeld.spalte));
            } 
           
            return true;
        }
        else if (spaltendiff == 0)
        {
            int richtung = reihendiff/Math.abs(reihendiff);//1 oder -1
           
            for(int i = 0; Math.abs(i) < Math.abs(reihendiff)-1; i = i + richtung)
            {
                int k = (8*(istFeld.reihe + i + richtung) + istFeld.spalte);
                
                if(!felder[k].frei())
                {
                    System.out.println("Feld: " + k + " ist belegt!");
                    return false;
                }
            }
            
            return true;
        }
        else
        {
            int richtung1 = reihendiff/Math.abs(reihendiff);
            int richtung2 = spaltendiff/Math.abs(spaltendiff);
            
            for (int i = 0, j = 0; Math.abs(i)<Math.abs(reihendiff)-1; i = i + richtung1, j = j + richtung2)
            {
                int k = 8*(istFeld.reihe + i + richtung1) + (istFeld.spalte + j + richtung2);
                
                if(!felder[k].frei())
                {
                    return false;
                }
            }
            return true;
        }
    }
    
    
//TODO Methode für Schach setzen

    public boolean setzenMoeglich(SchachbrettFeld istFeld, SchachbrettFeld sollFeld, SchachbrettFeld[] felder){
        
        if(sollFeld.figur == null)
        {
            if(wegFrei(istFeld, sollFeld, felder))
            {
                return true;
            }
            else
            {
                System.out.println(this.getClass().getSimpleName() + " darf nicht Springen!");
                return false;
            }
        }
        else
        {
            if(wegFrei(istFeld, sollFeld, felder) && istFeld.figur.farbe != sollFeld.figur.farbe)
            {
                
                return true;
            }
            else
            {
                System.out.println("gleiche Farbe");
                return false;
            } 
        }
    }
}
