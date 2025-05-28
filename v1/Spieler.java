package v1;
import java.util.ArrayList;
import java.util.Random;

class Spieler {
    private String name;
    private int geld;
    private ArrayList<Karte> hand;
    private Random random;

    public Spieler(String name, int startGeld) {
        this.name = name;
        this.geld = startGeld;
        this.hand = new ArrayList<>();
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public int getGeld() {
        return geld;
    }

    public void setGeld(int geld) {
        this.geld = geld;
    }

    public ArrayList<Karte> getHand() {
        return hand;
    }

    // Fügt eine Karte zur Hand hinzu
    public void addKarte(Karte karte) {
        hand.add(karte);
    }

    // Berechnet den Gesamtwert der Hand (berücksichtigt Ass-Regel)
    public int getHandWert() {
        int wert = 0;
        int asse = 0;

        for (Karte karte : hand) {
            if (karte.getWert().equals("Ass")) {
                asse++;
            }
            wert += karte.getNumerischerWert();
        }

        // Passe Ass-Werte an, wenn der Gesamtwert > 21 ist
        while (wert > 21 && asse > 0) {
            wert -= 10; // Ass von 11 auf 1 reduzieren
            asse--;
        }

        return wert;
    }

    // Setzt die Hand zurück
    public void clearHand() {
        hand.clear();
    }

    // Wenn Geld == 0, zufällige Menge zwischen 100 und 10.000 erhalten
    public void checkAndRefillGeld() {
        if (geld <= 0) {
            int neuesGeld = 100 + random.nextInt(9901); // 100 bis 10.000
            geld = neuesGeld;
            System.out.println(name + " hat kein Geld mehr und erhält " + neuesGeld + "!");
        }
    }
}