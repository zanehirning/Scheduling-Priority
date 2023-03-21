public class TestQueue {
    public static void main(String[] args) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.enqueue(27);
        q.enqueue(20);
        q.enqueue(25);
        q.enqueue(26);
        //System.out.println(q.dequeue());
        //System.out.println(q.dequeue());
        q.enqueue(10);
        q.enqueue(18);
        q.enqueue(13);
        q.enqueue(17);
        q.enqueue(16);
        q.printTree("label");

        q.dequeue();

        q.dequeue();

        System.out.println("-------------------------------------");

        q.printTree("after delete");
        //System.out.println(q.dequeue());
    }
}
