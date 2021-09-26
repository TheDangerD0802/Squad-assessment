package files;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            String line =  reader.readLine();
            while (true) {
                testcase.division(line);
                line = reader.readLine();
                if(line == null)
                	break;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}