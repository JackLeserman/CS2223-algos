package jleserman.hw2;

public class testSpecialQueue {
    public static void main(String[] args) {
        SpecialQueue sq = new SpecialQueue();
        sq.enqueue(1);
        sq.enqueue(2);
        sq.enqueue(3);
        sq.printQueue();
        sq.dequeue();
        sq.printQueue();
        sq.enqueue(999);
    }
}
