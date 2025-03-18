package vinnsla;

/**
 * Floskur klasinn sér um að geyma og setja upp allsskonar breytur**/
public class Floskur {
    private int fjoldiDosir;
    private int fjoldiFloskur;

    private final int virdiDosir = 20;
    private final int virdiFloskur = 25;

    /** Setur flöskur í vinnsluna og uppfærir virðið og samtals **/
    public void setFjoldiFloskur(int floskur) {
        if (floskur >= 0) {
            this.fjoldiFloskur = floskur;
        }
    }

    /** Skilar virði flaska **/
    public int getISKFloskur() {
        return fjoldiFloskur * virdiFloskur;
    }

    /** Setur fjölda dósa og uppfærir virði dósa **/
    public void setFjoldiDosir(int dosir) {
        if (dosir >= 0) {
            this.fjoldiDosir = dosir;
        }
    }

    /** Skilar virði dósa **/
    public int getISKDosir() {
        return fjoldiDosir * virdiDosir;
    }

    /** Núllar fjölda og virði dósa og flaska **/
    public void hreinsa() {
        fjoldiDosir = 0;
        fjoldiFloskur = 0;
    }

    /** Fá samtals ISK eða virðið **/
    public int getSamtalsISK() {
        return getISKDosir() + getISKFloskur();
    }

    /** Fá samtals magnið **/
    public int getSamtalsMagn() {
        return fjoldiDosir + fjoldiFloskur;
    }

    /** Fá fjölda flaska **/
    public int getFjoldiFloskur() {
        return fjoldiFloskur;
    }

    /** Fá fjölda dósa **/
    public int getFjoldiDosir() {
        return fjoldiDosir;
    }
}


