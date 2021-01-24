import java.sql.*;
import java.util.Scanner;

public interface Registrable {

    static void agregarAuto() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=JackoNoPalo1412" );
            Statement st = connection.createStatement();
            //st.setQueryTimeout(60);--------------------------------------------------------------------------------------------
            String opcion;
            Scanner sc = new Scanner(System.in);
            System.out.println("Seleccione su modelo:" +
                "1: SEDAN" +
                "2: FAMILIAR" +
                "3: COUPE");
            opcion = sc.nextLine();
            Automovil.modelo h = Automovil.modelo.SEDAN;
            switch (opcion) {
            case "1":
                h = Automovil.modelo.SEDAN;
                break;

            case "2":
                h = Automovil.modelo.FAMILIAR;
                break;
            case "3":
                h = Automovil.modelo.COUPE;
                break;
        }
            System.out.println( Automovil.modelo.armarString(h));
            System.out.println("Quieres alguna caracteristica extra?");
            System.out.println("S(si) o N(no)");
            opcion = sc.nextLine();
            Caracteristicas extras = new Caracteristicas(false, false, false, false, false);
            if (opcion.toLowerCase().equals("s")) {
                boolean[] respuestas = {false, false, false, false, false};
                boolean b = true;
                while (b) {
                    System.out.println("Quiere Aire Accondicionado? Presione S o N ");
                    opcion =sc.nextLine();
                    if(opcion.toLowerCase().equals("s")){
                        respuestas[0]=true;
                        b=false;
                    }else if(opcion.toLowerCase().equals("n")){
                        b = false;
                    }else{
                        System.out.println("Error,Ingrese los caracteres correspondientes");
                    }
                }
                b = true;
                while (b) {
                    System.out.println("Quiere Techo Corredizo? Presione S o N ");
                    opcion =sc.nextLine();
                    if(opcion.toLowerCase().equals("s")){
                        respuestas[1]=true;
                        b=false;
                    }else if(opcion.toLowerCase().equals("n")){
                        respuestas[1]=false;
                        b = false;
                    }
                }
                b = true;
                while (b) {
                    System.out.println("Quiere sistemas de frenos ABS? Presione S o N ");
                    opcion =sc.nextLine();
                    if(opcion.toLowerCase().equals("s")){
                        respuestas[2]=true;
                        b=false;
                    }else if(opcion.toLowerCase().equals("n")){
                        respuestas[2]=false;
                        b = false;
                    }
                }
                b = true;
                while (b) {
                    System.out.println("Quiere Airbag? Presione S o N ");
                    opcion =sc.nextLine();
                    if(opcion.toLowerCase().equals("s")){
                        respuestas[3]=true;
                        b=false;
                    }else if(opcion.toLowerCase().equals("n")){
                        respuestas[3]=false;
                        b = false;
                    }
                }
                b = true;
                while (b) {
                    System.out.println("Quiere Llantas de aleacion? Presione S o N ");
                    opcion =sc.nextLine();
                    if(opcion.toLowerCase().equals("s")){
                        respuestas[4]=true;
                        b=false;
                    }else if(opcion.toLowerCase().equals("n")){
                        respuestas[4]=false;
                        b = false;
                    }
                }
                if(!respuestas.equals(false)) {
                    extras = new Caracteristicas(respuestas[0], respuestas[1], respuestas[2], respuestas[3], respuestas[4]);
                }
        }

         st = connection.createStatement();
        Automovil auto = new Automovil(h, extras);
        System.out.println( Automovil.modelo.armarString(h));


            int idAuto = 1;
            st= connection.createStatement();
            st.setQueryTimeout(60);
            ResultSet rs = st.executeQuery("SELECT * FROM sys.automoviles");

            while (rs.next()) { idAuto++; }

        if (extras.equals(new Caracteristicas(false, false, false, false, false))) {

                st.execute("INSERT INTO `sys`.`automoviles` (`idAutomoviles`, `AutomovilesModelo`, `AutomovilesCosto`, `AutomovilesExtras`) VALUES ('"+idAuto+"', '" + Automovil.modelo.armarString(h) + "', '" + auto.getCosto() + "', '0')");
        } else {

                st.execute("INSERT INTO `sys`.`automoviles` (`idAutomoviles`, `AutomovilesModelo`, `AutomovilesCosto`, `AutomovilesExtras`) VALUES ('"+idAuto+"', '" + Automovil.modelo.armarString(h) + "', '"+ auto.getCosto() +"', '1')");
                 agregarCaracteristicas(idAuto,extras);
                //stExtra.execute("INSERT INTO `sys`.`caracteristicasautomoviles` (`idcaracteristicasautomoviles`, `idAutomoviles`, `caracteristicaAA`, `caracteristicaTC`, `caracteristicaAB`, `caracteristicaABS`, `caracteristicaLL`) VALUES ('0', '0', '0', '0', '0', '0', '0');")
        }
            rs = st.executeQuery("SELECT * FROM sys.automoviles");

            for (int i=0;i<idAuto;i++) {rs.next();}

                System.out.println("AutoID: " + rs.getInt("idAutomoviles"));
                System.out.println("modelo: " + rs.getString("AutomovilesModelo"));
                System.out.println("costo: " + rs.getFloat("AutomovilesCosto"));


        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }finally {
            try{
                if ( connection != null){
                    connection.close();
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    static void agregarCaracteristicas(int idAuto, Caracteristicas Carac)
    {
        Connection connection = null;
        //Crep la cpncexion
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=JackoNoPalo1412" );
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sys.caracteristicasautomoviles");
            int idCarac =1;
            while (rs.next()) { idCarac++; }
            st.setQueryTimeout(60);
            st.execute("INSERT INTO `sys`.`caracteristicasautomoviles` (`idcaracteristicasautomoviles`, `idAutomoviles`, `caracteristicaAA`, `caracteristicaTC`, `caracteristicaAB`, `caracteristicaABS`, `caracteristicaLL`) VALUES ('"+idCarac+"', '"+idAuto+"', '"+Carac.getAA()+"', '"+Carac.getTC()+"', '"+Carac.getABS()+"', '"+Carac.getAB()+"', '"+Carac.getLL()+"');");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }finally {
            try{
                if ( connection != null){
                    connection.close();
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
}


