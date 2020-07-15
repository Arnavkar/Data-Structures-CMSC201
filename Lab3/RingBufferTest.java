
public class RingBufferTest {

    static Ringbuffer b = new Ringbuffer(3);

    public static void main(String[]args) {
        b.enqueue(1);
        b.enqueue(2);
        System.out.println(b.dequeue());
        b.enqueue(3);
        b.enqueue(4);
        b.enqueue(5);
        b.enqueue(6);
        System.out.println(b.dequeue());
        System.out.println(b.dequeue());
        b.enqueue(7);
        b.enqueue(2);
        b.enqueue(12);
        b.enqueue(1);
        System.out.println(b.dequeue());
        System.out.println(b.dequeue());
        System.out.println(b.dequeue());


    }

}
