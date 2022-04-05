package jleserman.hw2;

public class testSpecialQueue {
    public static void main(String[] args) {
        SpecialQueue sq = new SpecialQueue();
        sq.enqueue(1);
        sq.enqueue(2);
        sq.enqueue(3);
        sq.enqueue(4);
        sq.printQueue();
        sq.swapEndPoints();
        sq.printQueue();
        sq.swapEndPoints();
        sq.printQueue();
        /*
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
         */
    }
}
