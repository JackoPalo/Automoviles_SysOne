import java.util.Scanner;


public class Main  {

    public static void main(String[] args) {



            boolean ciclo =true;
            Scanner sc = new Scanner(System.in);
            String opcion;
            while (ciclo){

                System.out.println("Quiere ordenar un Auto?");
                System.out.println("Presione S(si) o N(no) para salir del programa");
                opcion = sc.nextLine();
                if(opcion.toLowerCase().equals("s")){
                    Registrable.agregarAuto();
                }else if(opcion.toLowerCase().equals("n")){
                    ciclo=false;
                    System.out.println("Adios!");
                }


            }

    }


}
