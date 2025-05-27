class Karte {
    private String farbe; // z.B. Herz, Karo
    private String wert;  // z.B. Ass, König, 2

    public Karte(String farbe, String wert) {
        this.farbe = farbe;
        this.wert = wert;
    }

    public String getFarbe() {
        return farbe;
    }

    public String getWert() {
        return wert;
    }

    // Gibt den numerischen Wert der Karte zurück
    public int getNumerischerWert() {
        switch (wert) {
            case "Ass":
                return 11; // Ass kann 1 oder 11 sein, hier zunächst 11
            case "König":
            case "Dame":
            case "Bube":
                return 10;
            default:
                return Integer.parseInt(wert);
        }
    }

    @Override
    public String toString() {
        return wert + " von " + farbe;
    }
}