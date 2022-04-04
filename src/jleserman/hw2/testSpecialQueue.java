package jleserman.hw2;

public class testSpecialQueue {
    public static void main(String[] args) {
        SpecialQueue sq = new SpecialQueue();
        System.out.println(sq.isEmpty()); //TODO
        sq.enqueue(1);
        sq.enqueue(2);
        sq.enqueue(3);
        sq.printQueue();
        sq.dequeue();
        sq.printQueue();
        sq.enqueue(999);
        sq.enqueue(4);
        sq.enqueue(5);
        sq.printQueue();
        sq.dequeueLargest();
        sq.printQueue();
        System.out.println("SWAP");
        sq.swapEndPoints(); //TODO
        sq.printQueue();
        System.out.println(sq.isEmpty());
    }
}
