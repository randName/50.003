import java.io.*;
import java.util.*;

public class countlines {
    public static void main(String args[]) throws IOException {
        ProcessBuilder pb = new ProcessBuilder();
        System.out.print("Please enter your filename: ");
        String input = System.console().readLine();
        pb.command("wc", "-l", input);
        Process p = pb.start();      
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String cout;
        while((cout = br.readLine())!=null) { System.out.println(cout); }
        br.close();
    }
}
