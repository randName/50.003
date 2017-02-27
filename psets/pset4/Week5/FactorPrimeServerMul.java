package Week5;
import java.util.ArrayList;
import java.math.BigInteger;
import java.net.*;
import java.io.*;
 
public class FactorPrimeServerMul {
    private static BigInteger input, a, b;

    public static void main(String[] args) throws Exception {
        try {
             input = new BigInteger(args[0]);
        } catch (ArrayIndexOutOfBoundsException|NumberFormatException e) {
            System.out.println("Please input a semi-prime");
            return;
        }
        System.out.println("Input size: "+input.bitLength());

        ServerSocket serverSocket = new ServerSocket(4321);
        serverSocket.setSoTimeout(5000);
        System.out.println("(... expecting connection ...)");
        ArrayList<Socket> cSockets = new ArrayList<Socket>();
        ArrayList<PrintWriter> outs = new ArrayList<PrintWriter>();
        Socket cSocket;
        PrintWriter out;

        while (true) {
            try {
                cSocket = serverSocket.accept();
            } catch (InterruptedIOException e) {
                System.out.println("(... timeouted ...)");
                break;
            }
            System.out.println("(... connection established ...)");
            cSockets.add(cSocket);
            outs.add(new PrintWriter(cSocket.getOutputStream(), true));
        }

        int total = outs.size();
        System.out.println("(... sending instructions ...)");

        for ( int i = 0; i < total; i++ ) {
            out = outs.get(i);
            out.println(input);
            out.println(i);
            out.println(total);
            out.flush();
            out.close();
            cSockets.get(i).close();
        }
        serverSocket.close();
    }
}
