package pl.jancichy;

import java.util.ArrayList;

/**
 * Klasa dziedziczaca po klasie Pola do odkrywania grup pol
 */
public class Puste extends Pola{
    /**
     * Konstruktor Pustych
     * @param listaBomb lista bomb na planszy
     * @param x wspolrzedna osi X
     * @param y wspolrzedna osi Y
     */
    public Puste(ArrayList<Bomba> listaBomb, int x, int y) {
        super(listaBomb, x, y);
    }
    //metoda do odkrywania pól

    /**
     * Metoda sluzaca do odkrywania pol wokol odnalezionego pustego pola.
     * Otrzymuje ona Arraylisty zawierajace wspolrzedne pustych pol i zawierajacych cyfry, oraz tablice dwuwymiarowa,
     * ktora sluzy do wyswietlania odkrytych pol planszy
     * Metoda sprawdza, czy wokol odslonietego pola znajduje sie cyfra, a nastepnie czy znajduje sie inne puste pole
     * Jezeli sie znajduje to odkrywa te pola, a ponadto jezeli natknie sie na kolejne puste pole to czynnosc zostanie powtorzona dla tego pustego pola
     *
     * @param puste lista pustych pol na planszy
     * @param pola lista pol z cyframi na planszy
     * @param tab tablica do wyswietlania odkrytych i zakrytych pol planszy
     */
    public void odkrywanie(ArrayList<Puste> puste, ArrayList<Pola> pola, int[][] tab){
        for(int i=0; i<pola.size(); i++){
            if (wspX+1< tab.length){
                if (pola.get(i).wspX == wspX + 1 && pola.get(i).wspY == wspY) {
                    tab[wspX + 1][wspY] = pola.get(i).wartosc;
                }
            }
            if (wspX+1< tab.length && wspY+1< tab.length) {
                if (pola.get(i).wspX == wspX + 1 && pola.get(i).wspY == wspY + 1) {
                    tab[wspX + 1][wspY + 1] = pola.get(i).wartosc;
                }
            }
            if (wspY+1< tab.length) {
                if (pola.get(i).wspX == wspX && pola.get(i).wspY == wspY + 1) {
                    tab[wspX][wspY + 1] = pola.get(i).wartosc;
                }
            }
            if (wspX-1>=0 && wspY+1< tab.length) {
                if (pola.get(i).wspX == wspX - 1 && pola.get(i).wspY == wspY + 1) {
                    tab[wspX - 1][wspY + 1] = pola.get(i).wartosc;
                }
            }
            if (wspX-1>=0) {
                if (pola.get(i).wspX == wspX - 1 && pola.get(i).wspY == wspY) {
                    tab[wspX - 1][wspY] = pola.get(i).wartosc;
                }
            }
            if (wspX-1>=0 && wspY-1>=0) {
                if (pola.get(i).wspX == wspX - 1 && pola.get(i).wspY == wspY - 1) {
                    tab[wspX - 1][wspY - 1] = pola.get(i).wartosc;
                }
            }
            if (wspY-1>=0) {
                if (pola.get(i).wspX == wspX && pola.get(i).wspY == wspY - 1) {
                    tab[wspX][wspY - 1] = pola.get(i).wartosc;
                }
            }
            if (wspX+1< tab.length && wspY-1>=0) {
                if (pola.get(i).wspX == wspX + 1 && pola.get(i).wspY == wspY - 1) {
                    tab[wspX + 1][wspY - 1] = pola.get(i).wartosc;
                }
            }
        }
        
        for(int i=0; i<puste.size(); i++){
            if (wspX+1< tab.length) {
                if (puste.get(i).wspX == wspX + 1 && puste.get(i).wspY == wspY) {
                    if(tab[puste.get(i).wspX][puste.get(i).wspY]!=0){
                        tab[wspX + 1][wspY] = puste.get(i).wartosc;
                        puste.get(i).odkrywanie(puste, pola, tab);
                    }
                }
            }
            if (wspX+1< tab.length && wspY+1< tab.length) {
                if (puste.get(i).wspX == wspX + 1 && puste.get(i).wspY == wspY + 1) {
                    if(tab[puste.get(i).wspX][puste.get(i).wspY]!=0){
                        tab[wspX + 1][wspY + 1] = puste.get(i).wartosc;
                        puste.get(i).odkrywanie(puste, pola, tab);
                    }
                }
            }
            if (wspY+1< tab.length) {
                if (puste.get(i).wspX == wspX && puste.get(i).wspY == wspY + 1) {
                    if(tab[puste.get(i).wspX][puste.get(i).wspY]!=0){
                        tab[wspX][wspY + 1] = puste.get(i).wartosc;
                        puste.get(i).odkrywanie(puste, pola, tab);
                    }
                }
            }
            if (wspX-1>=0 && wspY+1< tab.length) {
                if (puste.get(i).wspX == wspX - 1 && puste.get(i).wspY == wspY + 1) {
                    if(tab[puste.get(i).wspX][puste.get(i).wspY]!=0){
                        tab[wspX - 1][wspY + 1] = puste.get(i).wartosc;
                        puste.get(i).odkrywanie(puste, pola, tab);
                    }
                }
            }
            if (wspX-1>=0) {
                if (puste.get(i).wspX == wspX - 1 && puste.get(i).wspY == wspY) {
                    if(tab[puste.get(i).wspX][puste.get(i).wspY]!=0){
                        tab[wspX - 1][wspY] = puste.get(i).wartosc;
                        puste.get(i).odkrywanie(puste, pola, tab);
                    }
                }
            }
            if (wspX-1>=0 && wspY-1>=0) {
                if (puste.get(i).wspX == wspX - 1 && puste.get(i).wspY == wspY - 1) {
                    if(tab[puste.get(i).wspX][puste.get(i).wspY]!=0){
                        tab[wspX - 1][wspY - 1] = puste.get(i).wartosc;
                        puste.get(i).odkrywanie(puste, pola, tab);
                    }
                }
            }
            if (wspY-1>=0) {
                if (puste.get(i).wspX == wspX && puste.get(i).wspY == wspY - 1) {
                    if(tab[puste.get(i).wspX][puste.get(i).wspY]!=0){
                        tab[wspX][wspY - 1] = puste.get(i).wartosc;
                        puste.get(i).odkrywanie(puste, pola, tab);
                    }
                }
            }
            if (wspX+1< tab.length && wspY-1>=0) {
                if (puste.get(i).wspX == wspX + 1 && puste.get(i).wspY == wspY - 1) {
                    if(tab[puste.get(i).wspX][puste.get(i).wspY]!=0){
                        tab[wspX + 1][wspY - 1] = puste.get(i).wartosc;
                        puste.get(i).odkrywanie(puste, pola, tab);
                    }
                }
            }
        }
    }
}
