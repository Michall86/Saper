package pl.jancichy;

import java.util.ArrayList;

/**
 * Klasa pola sluzaca do dodawania wartosci do pol jezeli wokol danych wspolzednych znajduje sie bomba
 */
public class Pola {
    /**
     * wspolrzedna osi X
     */
    public int wspX;
    /**
     * wspolrzedna osi Y
     */
    public int wspY;
    /**
     * wartosc poczatkowa pola przed dodaniem wartosci do pola
     */
    public int wartosc=0;

    /**
     * Konsturktor Pola, w ktorym dodawane sa wartosci pol do planszy w zaleznosci od tego czy wokol wskazanych
     * wspolrzednych znajduje sie bomba
     *
     * @param listaBomb arraylista zawierajace wylosowane bomby
     * @param x wspolrzedna osi X
     * @param y wspolrzedna osi Y
     */
    public Pola(ArrayList<Bomba> listaBomb, int x, int y){
        wspX=x;
        wspY=y;
        //dodawanie wartości do pól względem współrzędnych bomb z listy
        for(int i=0; i<listaBomb.size(); i++){
            //dodajemy do pola wartosc jeden
            if(listaBomb.get(i).wspX==wspX+1 && listaBomb.get(i).wspY==wspY){
                wartosc++;
            }
            if(listaBomb.get(i).wspX==wspX+1 && listaBomb.get(i).wspY==wspY+1){
                wartosc++;
            }
            if(listaBomb.get(i).wspX==wspX && listaBomb.get(i).wspY==wspY+1){
                wartosc++;
            }
            if(listaBomb.get(i).wspX==wspX-1 && listaBomb.get(i).wspY==wspY+1){
                wartosc++;
            }
            if(listaBomb.get(i).wspX==wspX-1 && listaBomb.get(i).wspY==wspY){
                wartosc++;
            }
            if(listaBomb.get(i).wspX==wspX-1 && listaBomb.get(i).wspY==wspY-1){
                wartosc++;
            }
            if(listaBomb.get(i).wspX==wspX && listaBomb.get(i).wspY==wspY-1){
                wartosc++;
            }
            if(listaBomb.get(i).wspX==wspX+1 && listaBomb.get(i).wspY==wspY-1){
                wartosc++;
            }
        }
    }
}
