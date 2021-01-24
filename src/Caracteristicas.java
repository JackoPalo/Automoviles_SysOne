public class Caracteristicas {

    private int AA=20000;
    private int TC=12000;
    private int ABS=14000;
    private int AB=7000;
    private int LL=12000;
    private double costoCaracteristica = 0;

    public int getAA() {
        return AA;
    }

    public int getTC() {
        return TC;
    }

    public int getABS() {
        return ABS;
    }

    public int getAB() {
        return AB;
    }

    public int getLL() {
        return LL;
    }

    public Caracteristicas(boolean AA,boolean TC,boolean ABS, boolean AB, boolean LL){
      if (AA){
          this.costoCaracteristica +=this.AA;};
      if (TC){
          this.costoCaracteristica +=this.TC;};
      if (ABS){
          this.costoCaracteristica +=this.ABS;};
      if (AB){
          this.costoCaracteristica +=this.AB;};
      if (LL){
          this.costoCaracteristica +=this.LL;};
    }

    public double getCostoCaracteristica( ){
        return costoCaracteristica;
    }

}
