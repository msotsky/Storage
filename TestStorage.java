import java.util.*;
import java.io.*;
/**
 * Test class for storage facility.
 * @author Maxime Sotsky 3637964
 */
public class TestStorage {

    /**
     * Main method.
     * @param args - input file representing a storage facility (test case)
     */
    public static void main(String[] args){
        try{
            File file = new File(args[0]);
            Storage storage = new Storage(file);
            int area = storage.findArea();
            storage.show();
            System.out.println("The area of the cavern is: " + area);
        }catch(FileNotFoundException e){
            System.out.println("Invalid file.");
        }
    }
}
