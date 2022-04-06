package jleserman.hw2;

public class testSpecialQueue {
    public static void main(String[] args) {
        SpecialQueue sq1 = new SpecialQueue();
        sq1.enqueue(1);
        sq1.enqueue(2);
        sq1.enqueue(3);
        sq1.enqueue(4);
        sq1.printQueue();
        sq1.swapEndPoints();
        sq1.printQueue();
        sq1.swapEndPoints();
        sq1.printQueue();

        SpecialQueue sq = new SpecialQueue();
        System.out.println(sq.isEmpty()); //TODO
        System.out.println("Size " + sq.size());
        sq.enqueue(1);
        sq.enqueue(4);
        sq.enqueue(3);
        System.out.println("Size " + sq.size());
        sq.printQueue();
        sq.dequeue();
        System.out.println("Size " + sq.size());
        sq.printQueue();
        sq.enqueue(999);
        sq.enqueue(0);
        sq.enqueue(5);
        sq.printQueue();
        sq.dequeueLargest(); //
        sq.printQueue();
        System.out.println("SWAP");
        sq.swapEndPoints();
        sq.printQueue();
        System.out.println(sq.isEmpty());
    }
}
