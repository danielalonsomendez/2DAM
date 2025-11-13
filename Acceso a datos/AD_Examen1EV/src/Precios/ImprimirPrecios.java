package Precios;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ImprimirPrecios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DataInputStream dis=null;
        try {
            FileInputStream fis = new FileInputStream(new File("Precios.dat"));		  		  		
            dis = new DataInputStream(fis);
            System.out.println("Lista de Precios");
            System.out.println("===========================================");
            while (fis.getChannel().position() < fis.getChannel().size()) {
               System.out.println(dis.readUTF() +" ("+dis.readFloat() +")");
            }
        }
      
        catch( FileNotFoundException fnfe){ 
            System.out.println("No se encuentra el archivo especificado.");
        }
        catch (EOFException eo) {
            if (dis!=null) {
                dis.close();
            }
        }
        catch (IOException ioe){
            System.out.println("IOException: " + ioe.getCause());
        }
    } 
}
