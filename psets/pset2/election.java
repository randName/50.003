class election {

    private static int[] tally = new int[2];
    private static int electorate = 0;

    public static void main(String[] args) {
        System.out.println("Vote Caster");
        while ( electorate < 5 ) {
            updateResult(readInput());
        }
        System.out.println("Voting Over");
        showResult();
    }

    private static char readInput() {
        char n = ' ';
        do {
            System.out.print("Please enter your vote: ");
            String input = System.console().readLine();
            n = input.charAt(0);
            if ( n != 'A' && n != 'B' ) {
                System.out.println("Sorry, that wasn't a valid vote");
            }
        } while ( n != 'A' && n != 'B' );
        return n;
    }

    private static void showResult() {
        if ( tally[0] > tally[1] ) {
            System.out.println("A Won");
        } else {
            System.out.println("B Won");
        }
    }

    private static void updateResult(char n) {
        tally[n == 'A'?0:1]++;
        electorate++;
    }
}
