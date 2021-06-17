package pl.jancichy;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Glowna klasa projektu, w ktorej zgromadzone sa funkcjonalnosci gry
 */
public class Plansza {
    //Arraylisty do przechowywania cyfr, pustych pól, bomb i znaczników
    /**
     * Arraylista do przechowywania informacji o wspolrzednych pol
     */
    public ArrayList<Pola> polaArrayList = new ArrayList<>();
    /**
     * Arraylista do przechowywania informacji o wspolrzednych pustych pol
     */
    public ArrayList<Puste> pusteArrayList = new ArrayList<>();
    /**
     * Arraylista do przechowywania informacji o wspolrzednych pol z bombami
     */
    public ArrayList<Bomba> bombaArrayList = new ArrayList<>();
    /**
     * Arraylista do przechowywania informacji o wspolrzednych znacznikow
     */
    public ArrayList<Znacznik> znacznikArrayList = new ArrayList<>();
    /**
     * wielkosc planszy
     */
    public int wielkosc;

    /**
     * Metoda do losowania miejsc w ktorych znajdowac sie beda bomby
     * losuje dwie wartasci (wspolrzedne) na ktorych znajdowac sie bedzie bomba i dodaje ja do arraylisty
     * jezeli bomba wylosuje sie w tym samym miejscu co przedtem to ma ja usunac z listy
     */
    private void tworzenieBomb(){
        Random random = new Random();           //losowanie ilości bomb w poniższym zakresie
        int iloscBomb = random.nextInt((wielkosc*wielkosc)/4)+(wielkosc);
        //po określeniu ilości bomb na planszy losujemy miejsca, w których się znajdują
        for (int i=0; i<iloscBomb; i++){
            //losujemy współrzędne bomb, a następnie dodajemy je do listy z bombami
            Bomba bomba = new Bomba(random.nextInt(wielkosc),random.nextInt(wielkosc));
            bombaArrayList.add(bomba);
            /*pętla sprawdzająca, czy dodana bomba już wcześniej nie została dodana
              na tą samą współrzędną, jeśli tak to ją usuwa*/
            for (int j=0; j<bombaArrayList.size()-1; j++){
                //współrzędna x i y dodawanej bomby musi się równać współżędnej x i y dowolnej bomby na liście
                if(bomba.wspX==bombaArrayList.get(j).wspX && bomba.wspY==bombaArrayList.get(j).wspY){
                    bombaArrayList.remove(bomba);
                }
            }
        }
    }

    /**
     * Metoda do tworzenia pol z cyframi na planszy
     * Jezeli wartosc pola jest rowna 0 to dodajemy to miejsce do listy pol
     * jezeli inaczej to do pol z cyframi
     * gdy na tych samych wspolrzednych jest bomba i pole z cyfra lub puste to usuwamy z listy pol lub pustych
     */
    private void tworzeniePol(){
        //dodawanie do list
        for (int i=0; i<wielkosc; i++){
            for (int j=0; j<wielkosc; j++){
                Pola pola = new Pola(bombaArrayList, j, i);
                /*kiedy wartosc pola jest równa 0, to jest puste*/
                if(pola.wartosc==0){
                    Puste puste = new Puste(bombaArrayList, j, i);
                    pusteArrayList.add(puste);
                }
                //jeżeli jest wartość to dodajemy do listy pól
                else polaArrayList.add(pola);
            }
        }
                /*jeżeli współrzędne pola lub pustego wypadają w tym samym miejscu co bomby to usuwa
                  współrzędne z tych list*/
        for (int i=0; i<bombaArrayList.size(); i++){
            for (int j=0; j<polaArrayList.size(); j++){
                if(polaArrayList.get(j).wspX==bombaArrayList.get(i).wspX && polaArrayList.get(j).wspY==bombaArrayList.get(i).wspY){
                    polaArrayList.remove(j);
                }
            }
            for (int j=0; j<pusteArrayList.size(); j++){
                if(pusteArrayList.get(j).wspX==bombaArrayList.get(i).wspX && pusteArrayList.get(j).wspY==bombaArrayList.get(i).wspY){
                    pusteArrayList.remove(j);
                }
            }
        }
    }

    /*warunki do wyświetlania która w zależności od wartości w tablicy wyświetla nam planszę na ekranie
      wartości po odsłonięciu to wartości 0-9, -1 to symbol zaznaczenia, a -2 to zasłonięte pole*/

    /**
     * Metoda do wyswietlania planszy
     * Jezeli wartosc pola jest rowna -2 to wyswietla zasloniete pola jako #
     * Jezeli wartosc pola jest rowna -1 to wyswietla znacznik pola jako @
     * Jezeli wartosc pola jest wieksza od -1 to wyswietla pole ktore jest odsonite
     * Jezeli jestesmy na danych wspolrzednych planszy to wokol wartosci znajduje sie klamra kwadratowa
     *
     * @param tab tablica do przekazywania miejsca, na ktorym znajdujemy sie na planszy
     * @param tablica tablica z wyswietlanymi wartosciami
     */

    public void wyswietlanie(int[] tab,int[][] tablica){
        for (int i=0; i< tablica.length; i++) {
            System.out.println();
            for (int j = 0; j < tablica.length; j++) {
                if (tablica[i][j] > -1) {
                    if (i == tab[1] && j == tab[0]) {               //jeżeili ten warunek jest spełniny to na tym polu "znajdujemy się"
                        System.out.print("[" + tablica[i][j] + "]");
                    } else System.out.print(" " + tablica[i][j] + " ");
                } else if (tablica[i][j]==-2) {
                    if (i == tab[1] && j == tab[0]){
                        System.out.print("[#]");
                    }else System.out.print(" # ");
                } else if(tablica[i][j]==-1){
                    if (i == tab[1] && j == tab[0]) {
                        System.out.print("[@]");
                    }else System.out.print(" @ ");
                }
            }
        }
        //część deweloperska kodu do sprawdzania odsłoniętych pól
        /*
        System.out.println();
        System.out.println();
        System.out.println();
        for (int i=0; i<wielkosc; i++){
            for (int j=0; j<wielkosc; j++){
                for (int k=0; k<polaArrayList.size(); k++){
                    if(polaArrayList.get(k).wspX==i && polaArrayList.get(k).wspY==j){
                        System.out.print(polaArrayList.get(k).wartosc + " ");
                        break;
                    }
                }
                for (int k=0; k<pusteArrayList.size(); k++){
                    if(pusteArrayList.get(k).wspX==i && pusteArrayList.get(k).wspY==j){
                        System.out.print(pusteArrayList.get(k).wartosc + " ");
                        break;
                    }
                }
                for (int k=0; k<bombaArrayList.size(); k++){
                    if(bombaArrayList.get(k).wspX==i && bombaArrayList.get(k).wspY==j){
                        System.out.print("* ");
                        break;
                    }
                }
            }
            System.out.println();


        }*/
    }
    //metoda do wyswietlania całej mapy po przegranej

    /**
     * Metoda wyswietlajaca wszystkie odkryte pola planszy
     * Jezeli jest cyfra to ja wyswietla, natomiast gdy na wspolrzednych jest bomba to wyswietla '*'
     */
    private void widokKoncowy(){
        System.out.println();
        System.out.println();
        System.out.println();
        //Wyswietla określoną arraylistę dla danego miejsca na planszy
        for (int i=0; i<wielkosc; i++){
            for (int j=0; j<wielkosc; j++){
                for (int k=0; k<polaArrayList.size(); k++){
                    if(polaArrayList.get(k).wspX==i && polaArrayList.get(k).wspY==j){
                        System.out.print(" "+polaArrayList.get(k).wartosc + " ");
                        break;
                    }
                }
                for (int k=0; k<pusteArrayList.size(); k++){
                    if(pusteArrayList.get(k).wspX==i && pusteArrayList.get(k).wspY==j){
                        System.out.print(" "+pusteArrayList.get(k).wartosc + " ");
                        break;
                    }
                }
                for (int k=0; k<bombaArrayList.size(); k++){
                    if(bombaArrayList.get(k).wspX==i && bombaArrayList.get(k).wspY==j){
                        System.out.print(" * ");
                        break;
                    }
                }
            }
            System.out.println();
        }
    }

    //metoda zapełniająca tablicę do wyswietlania #

    /**
     * Metoda zapelniajaca tablice do wyswietlania wartoscia poczatkowa
     *
     * @param tablica tablica z wyswietlanymi wartosciami
     */
    private void planszaPoczatkowa(int[][] tablica){
        for (int i=0; i< tablica.length; i++){
            for (int j=0; j< tablica.length; j++){
                tablica[i][j]=-2;
            }
        }
    }

    //metoda, która sprawdza jakie pole zostało odkryte, używana w case odsłaniania

    /**
     * Metoda do sprawdzenia jakie pole odkrylismy
     * Jezeli odkrylismy bombe zwraca 9
     * Jezeli odkrylismy puste zwraca 0
     * Jezeli odkrylismy pole zwraca wartosc pola
     *
     * @param tab tablica zawierajace wspolzedne wybranego pola
     * @return zwracana wartosc pola
     */
    private int sprawdz(int[] tab){
        for (int i=0; i<bombaArrayList.size(); i++){
            if(bombaArrayList.get(i).wspX==tab[1] && bombaArrayList.get(i).wspY==tab[0]){
                return 9;
            }
        }
        for (int i=0; i<pusteArrayList.size(); i++){
            if(pusteArrayList.get(i).wspX==tab[1] && pusteArrayList.get(i).wspY==tab[0]){
                return 0;
            }
        }
        for (int i=0; i<polaArrayList.size(); i++){
            if(polaArrayList.get(i).wspX==tab[1] && polaArrayList.get(i).wspY==tab[0]){
                return polaArrayList.get(i).wartosc;
            }
        }
        return 0;
    }

    //dodawanie wskaźnika na współrzedne

    /**
     * Metoda tworzaca znacznik na na danej wspolzednej
     *
     * @param tab tablica zawierajace wspolzedne wybranego pola
     */
    private void stworzZnacznik(int[] tab){
        Znacznik znacznik = new Znacznik(tab[1],tab[0]);
        znacznikArrayList.add(znacznik);
    }

    //sprawdza czy znaczniki są w tych samych miejscach co bomby, jeśli tak to wygrałeś

    /**
     * Metoda sprawdzajaca czy wspolzedne znacznikow pokrywaja sie z bombami
     * Jezeli wspolrzedne wszystkich bomb pokrywaja sie ze znacznikami to zwraca true
     * Jezeli nie to false
     *
     * @return zwraca wartosc wygranej
     */
    private boolean czyWygrales(){
        for (int i=0; i< bombaArrayList.size(); i++){
            boolean czy=false;
            for (int j=0; j< znacznikArrayList.size(); j++){
                if (bombaArrayList.get(i).wspX == znacznikArrayList.get(j).wspX && bombaArrayList.get(i).wspY == znacznikArrayList.get(j).wspY) {
                    czy = true;
                    break;
                }
            }
            if (!czy){
                return false;
            }
        }
        return true;
    }

    //metoda do sterowania grą

    /**
     * Metoda do sterowania cala gra
     * Przy pomocy wyswietlanych klawiszy dokonujemy operacji na planszy
     * Mozemy sie przemieszczac zmieniajac wartosc tablicy jednowymiarowej
     * Mozemy odslaniac pola lub zaznaczac i odznaczac znaczniki
     *
     * @param tablica tablica z wyswietlanymi wartosciami
     */
    private void sterowanie(int[][] tablica){
        Scanner scanner = new Scanner(System.in);
        String wybor;
        //Wybieranie opcji czy przejsc do innego pola, czy zaznaczyc, czy oznaczyc
        boolean dzialnie=true;          //do funkcjonowania pętli
        int[] tabXY = new int[2];       //do przechowywania wartości gdzie jesteśmy 0<-ośX, 1<-ośY
        planszaPoczatkowa(tablica);     //zapełnienie -2
        while (dzialnie) {
            System.out.println("Sterowanie:");
            System.out.println("WASD <- Wybieranie pola");
            System.out.println("x <- zaznaczenie pola");
            System.out.println("z <- zaznaczenie bomby");
            System.out.println("c <- odznaczanie bomby");
            System.out.println();
            wyswietlanie(tabXY, tablica);
            wybor=scanner.nextLine();
            switch (wybor) {
                case "w", "W" -> tabXY[1] -= 1;
                case "s", "S" -> tabXY[1] += 1;
                case "a", "A" -> tabXY[0] -= 1;
                case "d", "D" -> tabXY[0] += 1;
                case "x", "X" -> {
                    //jeżeli trafisz w 9 to przegrywasz, jak 1-8 to wyswietla wartosc, jak 0 to ma przejść do odkrywania pól do okoła
                    if (sprawdz(tabXY) == 9) {
                        System.out.println();
                        System.out.println();
                        System.out.println("Przegrałeś!");
                        widokKoncowy();
                        dzialnie=false;
                    } else if (sprawdz(tabXY) > 0) {
                        tablica[tabXY[1]][tabXY[0]] = sprawdz(tabXY);
                    } else {
                        tablica[tabXY[1]][tabXY[0]] = sprawdz(tabXY);
                        for (int i = 0; i < pusteArrayList.size(); i++) {
                            if (pusteArrayList.get(i).wspX == tabXY[1] && pusteArrayList.get(i).wspY == tabXY[0]) {
                                pusteArrayList.get(i).odkrywanie(pusteArrayList, polaArrayList, tablica);
                            }
                        }
                    }
                }
                case "z", "Z" -> {
                    if(znacznikArrayList.size()<bombaArrayList.size()) {
                        boolean czy=true;
                        for(int i=0;i<znacznikArrayList.size();i++){
                            if (znacznikArrayList.get(i).wspX == tabXY[1] && znacznikArrayList.get(i).wspY == tabXY[0]) {
                                czy = false;
                                break;
                            }
                        }
                        if(czy){
                            stworzZnacznik(tabXY);
                            if (czyWygrales()) {
                                System.out.println("Wygrałeś!");
                                dzialnie = false;
                                break;
                            }
                            tablica[tabXY[1]][tabXY[0]] = -1;
                        }else System.out.println("Tu już jest znacznik!!!");
                    }else System.out.println("Brak znaczków!!!! Usuń znacznik!!!");
                }
                case "c", "C" -> {
                    //zastępujemy znacznik @ na #
                    for(int i=0; i<znacznikArrayList.size(); i++){
                        if(znacznikArrayList.get(i).wspX==tabXY[1] && znacznikArrayList.get(i).wspY==tabXY[0]){
                            znacznikArrayList.remove(i);
                            tablica[tabXY[1]][tabXY[0]] = -2;
                        }
                    }
                }
            }
        }
    }

    /**
     * Metoda glowna w ktorej wynonujemy metody oraz wyznaczamy wielkosc planszy
     */
    public void glowna(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w Saperze!");
        System.out.print("Podaj wielkosc planszy: ");
        wielkosc = scanner.nextInt();
        int[][] tablica = new int[wielkosc][wielkosc];
        tworzenieBomb();
        tworzeniePol();
        sterowanie(tablica);
    }
}
