import java.util.Scanner;

public class BlackjackGame {
    private Deck deck;
    private Spieler spieler;
    private Spieler dealer;
    private Scanner scanner;

    public BlackjackGame() {
        deck = new Deck();
        spieler = new Spieler("Spieler", 1000); // Startgeld 1000
        dealer = new Spieler("Dealer", 0); // Dealer braucht kein Geld
        scanner = new Scanner(System.in);
    }

    public void starteSpiel() {
        System.out.println("Willkommen bei Blackjack!");

        while (true) {
            spieler.checkAndRefillGeld();
            if (spieler.getGeld() <= 0) {
                System.out.println("Fehler: Spieler hat kein Geld mehr!");
                break;
            }

            // Einsatz abfragen
            System.out.println("Dein Geld: " + spieler.getGeld());
            System.out.print("Einsatz eingeben (1-" + spieler.getGeld() + "): ");
            int einsatz = scanner.nextInt();
            if (einsatz <= 0 || einsatz > spieler.getGeld()) {
                System.out.println("Ungültiger Einsatz!");
                continue;
            }

            // Karten austeilen
            spieler.clearHand();
            dealer.clearHand();
            spieler.addKarte(deck.zieheKarte());
            spieler.addKarte(deck.zieheKarte());
            dealer.addKarte(deck.zieheKarte());
            dealer.addKarte(deck.zieheKarte());

            // Spielerzug
            System.out.println("\nDeine Karten: " + spieler.getHand() + " (Wert: " + spieler.getHandWert() + ")");
            System.out.println("Dealer zeigt: " + dealer.getHand().get(0));

            while (spieler.getHandWert() < 21) {
                System.out.print("Möchtest du 'Hit' (h) oder 'Stand' (s)? ");
                String eingabe = scanner.next().toLowerCase();
                if (eingabe.equals("h")) {
                    spieler.addKarte(deck.zieheKarte());
                    System.out.println("Deine Karten: " + spieler.getHand() + " (Wert: " + spieler.getHandWert() + ")");
                    if (spieler.getHandWert() > 21) {
                        System.out.println("Über 21! Du verlierst.");
                        spieler.setGeld(spieler.getGeld() - einsatz);
                        break;
                    }
                } else if (eingabe.equals("s")) {
                    break;
                } else {
                    System.out.println("Ungültige Eingabe! Bitte 'h' oder 's' eingeben.");
                }
            }

            // Dealerzug, wenn Spieler nicht über 21 ist
            if (spieler.getHandWert() <= 21) {
                System.out.println("\nDealer Karten: " + dealer.getHand() + " (Wert: " + dealer.getHandWert() + ")");
                while (dealer.getHandWert() < 17) {
                    dealer.addKarte(deck.zieheKarte());
                    System.out.println("Dealer zieht: " + dealer.getHand().get(dealer.getHand().size() - 1));
                    System.out.println("Dealer Karten: " + dealer.getHand() + " (Wert: " + dealer.getHandWert() + ")");
                }

                // Ergebnis bestimmen
                int spielerWert = spieler.getHandWert();
                int dealerWert = dealer.getHandWert();
                if (dealerWert > 21 || spielerWert > dealerWert) {
                    System.out.println("Du gewinnst!");
                    spieler.setGeld(spieler.getGeld() + einsatz);
                } else if (spielerWert < dealerWert) {
                    System.out.println("Dealer gewinnt!");
                    spieler.setGeld(spieler.getGeld() - einsatz);
                } else {
                    System.out.println("Unentschieden!");
                }
            }

            // Nochmal spielen?
            System.out.print("\nNoch eine Runde spielen? (j/n): ");
            String nochmal = scanner.next().toLowerCase();
            if (!nochmal.equals("j")) {
                break;
            }
        }

        System.out.println("Spiel beendet. Dein Endstand: " + spieler.getGeld());
        scanner.close();
    }

    public static void main(String[] args) {
        BlackjackGame spiel = new BlackjackGame();
        spiel.starteSpiel();
    }
}