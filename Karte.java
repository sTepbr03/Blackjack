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
        return switch (wert) {
            case "Ass" -> 11;
            case "König", "Dame", "Bube" -> 10;
            default -> Integer.parseInt(wert);
        }; // Ass kann 1 oder 11 sein, hier zunächst 11
    }

    @Override
    public String toString() {
        return wert + " von " + farbe;
    }

    public void setWert(String wert) {
        this.wert = wert;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }
}