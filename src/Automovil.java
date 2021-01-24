import java.sql.*;

public class Automovil {



    enum modelo {
        COUPE, FAMILIAR,SEDAN;

        public static String armarString(Automovil.modelo h){
            String salida ="";
            switch( h){
                case COUPE:
                    salida = "COUPE";
                    break;
                case FAMILIAR:
                    salida ="FAMILIAR";
                    break;
                case SEDAN:
                    salida = "SEDAN";
                    break;
            }
            return salida;
        }
    }

    private Caracteristicas carac ;
    private double costo;
    private modelo model;

    public Automovil(modelo n, Caracteristicas C){
        switch (n){
            case COUPE:
                this.costo = 270000;

            case SEDAN:
                this.costo = 230000;

            case FAMILIAR:
                this.costo = 245000;
        }
        this.model = n;
        this.carac =C;
        this.costo+= C.getCostoCaracteristica();

    }


    public double getCosto() {
        return this.costo;
    }

}
