package vinnsla;

/**
 * Floskur klasinn sér um að geyma og setja upp allsskonar breytur**/
public class Floskur {
    private int fjoldiDosir;
    private int fjoldiPlast;
    private int fjoldiGler;

    private final int virdiDosir = 20;
    private final int virdiFloskur = 25;
    private final int virdiGler = 35;

    /** Setur flöskur í vinnsluna og uppfærir virðið og samtals **/
    public void setFjoldiFloskur(int floskur) {
        if (floskur >= 0) {
            this.fjoldiPlast = floskur;
        }
    }

    /** Skilar virði plast flaska **/
    public int getISKFloskur() {
        return fjoldiPlast * virdiFloskur;
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

    /** Setur fjölda glers og uppfærir virði glers **/
    public void setFjoldiGler(int gler) {
        if (gler >= 0) {
            this.fjoldiGler = gler;
        }
    }

    /** Skilar virði glers **/
    public int getISKGler() {
        return fjoldiGler * virdiGler;
    }

    /** Núllar fjölda og virði dósa og plast/glers flaska **/
    public void hreinsa() {
        fjoldiDosir = 0;
        fjoldiPlast = 0;
        fjoldiGler = 0;
    }

    /** Fá samtals ISK eða virðið **/
    public int getSamtalsISK() {
        return getISKDosir() + getISKFloskur() + getISKGler();
    }

    /** Fá samtals magnið **/
    public int getSamtalsMagn() {
        return fjoldiDosir + fjoldiPlast + fjoldiGler;
    }

    /** Fá fjölda plast flaska **/
    public int getFjoldiPlast() {
        return fjoldiPlast;
    }

    /** Fá fjölda dósa **/
    public int getFjoldiDosir() {
        return fjoldiDosir;
    }

    /** Fá fjölda glers **/
    public int getFjoldiGler() { return fjoldiGler; }
}



