package Week5;
import java.math.BigInteger;
import java.net.*;
import java.io.*;
 
public class FactorPrimeClientMul {

    public static void main(String[] args) throws Exception {
        String hostName = "localhost";
        // String hostIP = "10.11.3.28";
        // String hostName = "fe80::7517:c1af:b2bb:da73%4";
        int portNumber = 4321;
         
        //Socket echoSocket = new Socket(hostName, portNumber);
        Socket cSocket = new Socket();
        SocketAddress sockaddr = new InetSocketAddress(hostName, portNumber);
        cSocket.connect(sockaddr, 100);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(cSocket.getInputStream()));

        BigInteger input = new BigInteger(in.readLine());
        int init = new Integer(in.readLine());
        int step = new Integer(in.readLine());

        BigInteger a = factor(input, init, step);
        BigInteger b = input.divide(a);
        System.out.println("Factors: "+a+", "+b);

        cSocket.close();
        in.close();
    }

    private static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger y, div2 = div;
        while (true) {
            y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2)) return y;
            div2 = div; div = y;
        }
    }

    private static BigInteger factor(BigInteger x, int init, int step) {
        BigInteger i, inc = BigInteger.valueOf(step*2), until = sqrt(x);
        int curlen, lastlen = 0;
        for ( i = BigInteger.valueOf(3+init*2); i.compareTo(until) <= 0; i = i.add(inc) ) {
            if ( x.remainder(i) == BigInteger.ZERO ) return i;
            curlen = i.bitLength();
            if ( curlen > lastlen ) {
                lastlen = curlen; 
                System.out.print(""+curlen+"\r");
            }
        }
        return x;
    }
}
