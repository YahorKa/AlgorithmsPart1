/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        // Integer k = 20;
        RandomizedQueue<String> dek = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            dek.enqueue(StdIn.readString());
            if (!StdIn.isEmpty()) {
                if (Character.valueOf(StdIn.readChar()).compareTo(Character.valueOf('\n')) == 0) {
                    break;
                }
            }
        }

        for (int i = 0; i < k; i++) {
            if (dek.isEmpty()) return;
            System.out.println(dek.dequeue());
            // dek.sample();
        }

    }
}