/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

//  public void enqueue(String item)
//  {
//  Node oldlast = last;
//  last = new Node();
//  last.item = item;
//  last.next = null;
//  if (isEmpty()) first = last;
//  else oldlast.next = last;
//  }
//
//  public String dequeue()
//  {
//  String item = first.item;
//  first = first.next;
//  if (isEmpty()) last = null;
//  return item;
//  }
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first = null;
    //   private Node last = first;
    private int N = 0;


    private class Node {
        Item item;
        Node next;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        //  if (isEmpty()) first = last;
        //  else oldlast.next = last;
        // System.out.println("enqueue fitem: " + first.item);
        N = N + 1;
    }

    // remove and return a random item
    public Item dequeue() {
        // System.out.println("deaue N: " + N);
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        // System.out.println("deaue N: " + N);
        if (N == 1) {
            N--;
            Item copy = first.item;
            first = null;
            // System.out.println("remove number: " + copy);
            return copy;
        }
        if (N == 0) throw new java.util.NoSuchElementException();
        Iterator it = iterator();
        Node currNode = null;
        Node prevNode = null;
        Item degueItem = null;
        int randomInt = StdRandom.uniform(1, size() + 1);
        //  System.out.println("deaue randomInt: " + randomInt);
        for (int i = 0; i < randomInt; i++) {

            prevNode = currNode;
            if (currNode == null) currNode = first;
            else {
                currNode = currNode.next;
            }
        }
        if (prevNode != null) {
            prevNode.next = currNode.next;
            degueItem = currNode.item;
            currNode = null;
            N--;
            //  System.out.println("remove number: " + degueItem);
            return degueItem;
        }
        degueItem = currNode.item;
        first = currNode.next;
        currNode = null;
        N--;
        //  System.out.println("remove number: " + degueItem);
        return degueItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int randomInt = StdRandom.uniform(1, size() + 1);
        Iterator it = iterator();
        Node currNode = null;
        for (int i = 0; i < randomInt; i++) {
            if (currNode == null) currNode = first;
            else {
                currNode = currNode.next;
            }
        }
        return currNode.item;
        // throw new java.util.NoSuchElementException();
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new listIterator();
    }

    private class listIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rDeck = new RandomizedQueue<Integer>();
        rDeck.enqueue(1);
        rDeck.enqueue(2);
        rDeck.enqueue(3);
        rDeck.enqueue(4);
        rDeck.enqueue(5);
        rDeck.dequeue();
        rDeck.dequeue();
        rDeck.dequeue();
        rDeck.dequeue();
        // System.out.println(rDeck.sample());
        // System.out.println(rDeck.sample());
        // System.out.println(rDeck.sample());

        // System.out.println("finish: " + rDeck.N);
        // Iterator it = deck.iterator();
        // System.out.println(it.next());
        // System.out.println(it.next());
        // System.out.println(it.next());
        // System.out.println(it.next());
        // System.out.println(it.next());
        // System.out.println(it.next());
        // System.out.println(deck.removeFirst());
        // System.out.println(deck.removeLast());
        // System.out.println(deck.removeFirst());
        // System.out.println(deck.removeLast());
        // System.out.println(deck.removeFirst());
        // System.out.println(deck.removeLast());
    }

}