package pl.jancichy;

/**
 * Klasa dziedziczy wspolzedne do klas Bomba i Znacznik
 */
public class Specjalne {
    /**
     * wspolrzedna osi X
     */
    int wspX;
    /**
     * wspolrzedna osi Y
     */
    int wspY;

    /**
     * Konstruktor specjalnych
     *
     * @param x wspolrzedna osi X
     * @param y wspolrzedna osi Y
     */
    public Specjalne(int x, int y){
        wspX=x;
        wspY=y;
    }
}
