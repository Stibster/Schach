package schach.v1.pkg0;


/**
 *
 * @author Martin Borsdorf, Steve Vogel
 */

public class SchachbrettFeld
{
    private final boolean farbe;
    Figur figur;
    int spalte;
    int reihe;
    
    public SchachbrettFeld(boolean p, int i, int j){
        farbe = p;
        spalte = i;
        reihe = j;
    }
    public boolean isWhite()
    {
        return farbe;
    }
    public void setFigur(Figur f)
    {
        this.figur = f;
    }

    public boolean frei()
    {
        return this.figur == null;
    }
}
