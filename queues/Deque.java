/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    final private CustomStack<Item> stack;

    private class CustomStack<Item> {
        CustomStack(int capacity) {
            items = (Item[]) new Object[capacity];
        }

        private void resize(int capacity) {
            Item[] copy = (Item[]) new Object[capacity];
            for (int i = 0; i < N; i++)
                copy[i] = items[i];
            items = copy;
        }

        void push(Item item) {
            if (N == items.length) resize(2 * items.length);
            items[N++] = item;
            // System.out.println("push N = " + N);
            //   System.out.println("items capacity " + items.length);
        }

        Item pop() {
            //   System.out.println("pop N = " + N);
            if (N > 0) {
                Item item = items[--N];
                items[N] = null;
                if (N > 0 && N == items.length / 4) resize(items.length / 2);
                return item;
                // need resize

            }
            throw new java.util.NoSuchElementException();
        }

        private Item[] items;
        private int N = 0;
    }

    final private int startCapacity = 5;


    // construct an empty deque
    public Deque() {
        stack = new CustomStack<Item>(startCapacity);
    }

    // is the deque empty?
    public boolean isEmpty() {
        if (stack.N == 0) return true;
        return false;
    }

    // return the number of items on the deque
    public int size() {
        return stack.N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        int capacity = size();
        CustomStack<Item> reversStack = new CustomStack<Item>(capacity + 1);
        while (!isEmpty()) {
            reversStack.push((Item) stack.pop());
        }
        reversStack.push(item);
        while (reversStack.N > 0) {
            stack.push(reversStack.pop());
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        stack.push(item);
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int capacity = size();
        CustomStack<Item> reversStack = new CustomStack<Item>(capacity + 1);
        while (!isEmpty()) {
            reversStack.push((Item) stack.pop());
        }
        Item first = reversStack.pop();
        while (reversStack.N > 0) {
            stack.push(reversStack.pop());
        }
        return first;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return (Item) stack.pop();

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < stack.N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (hasNext()) return (Item) stack.items[i++];
            throw new java.util.NoSuchElementException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Deque<Integer> deck = new Deque<Integer>();
        // deck.addLast(4);
        // deck.addLast(4);
        // deck.addLast(3);
        // deck.addFirst(16);
        // deck.addFirst(16);
        // deck.addFirst(16);
        // deck.addLast(4);
        // deck.addLast(4);
        // deck.addLast(3);
        // deck.addFirst(16);
        // deck.addFirst(16);
        // deck.addFirst(16);
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