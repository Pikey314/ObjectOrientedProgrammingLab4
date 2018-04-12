import prData.*;

public class DataTest {

    public static void main (String[] args){

        if (args.length < 3){
            System.out.println("Not enough arguments - minimum 3 (where last 2 arguments are min and max value)");
            System.out.println("Example of arguments:   12.0 13.0 17.0 5.0 9.0 10.0 20.0 25 Pepe María Paco Ana Juan Lola 10.0 20.0");
            System.exit(-1);
        }

        try {

            double min = Double.parseDouble(args[args.length-2]);
            double max = Double.parseDouble(args[args.length-1]);
            String [] values  = new String[args.length-2];
            for (int i =0; i<values.length; i++)
                values[i] = args[i];
            Data d = new Data(values,min,max);
            System.out.println();
            System.out.println();
            System.out.println(d.toString());
        System.out.println();
        System.out.println();
        System.out.println("Changing minmax by function setRange(1;Jose)");
        System.out.println();
        System.out.println();

        d.setRange("1;Jose");
            System.out.println(d.toString());

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }
}
