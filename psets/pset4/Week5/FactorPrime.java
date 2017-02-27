package Week5;
import java.math.BigInteger;

public class FactorPrime {
    private static BigInteger input, a, b;

    public static void main(String[] args) {
        try {
             input = new BigInteger(args[0]);
        } catch (ArrayIndexOutOfBoundsException|NumberFormatException e) {
            System.out.println("Please input a semi-prime");
            return;
        }
        System.out.println("Input size: "+input.bitLength());
        a = factor(input);
        b = input.divide(a);
        System.out.println("Factors: " + a + ", " + b);
    }

    private static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger y, div2 = div;
        // Loop until we hit the same value twice in a row, or wind up alternating.
        while (true) {
            y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2)) return y;
            div2 = div; div = y;
        }
    }

    private static BigInteger factor(BigInteger x) {
        BigInteger i, inc = BigInteger.valueOf(2), until = sqrt(x);
        int curlen, lastlen = 0;
        for ( i = BigInteger.valueOf(3); i.compareTo(until) <= 0; i = i.add(inc) ) {
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
