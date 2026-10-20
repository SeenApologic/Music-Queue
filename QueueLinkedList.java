public class QueueLinkedList {
   // pointer of the front and rear node
   private Node front;
   private Node rear;
   private int size;

   // create an empty queue
   public QueueLinkedList() {
      front = null;
      rear = null;
      size = 0;
   }

   public boolean isEmpty() {
      return size == 0;
   }

   public int size() {
      return size;
   }

   // Add an element at the rear, there is no fixed capacity
   public void enqueue(Object value) {
      Node newNode = new Node(value);
      if (isEmpty()) {
         front = newNode;
         rear = newNode;
      } else {
         rear.next = newNode;
         rear = newNode;
      }
      size++;
   }

   // Retrieve the front element without removing, null if queue is empty
   public Object peek() {
      if (!isEmpty()) {
         return front.data;
      }
      return null;
   }

   // Remove the front element, null if queue is empty (underflow)
   public Object dequeue() {
      if (isEmpty()) {
         return null;
      }
      Node temp = front;
      Object tempData = temp.data;
      front = front.next;
      if (front == null) { // queue became empty, rear must be reset too
         rear = null;
      }
      temp.next = null;
      size--;
      return tempData;
   }

   // Traversal from front to rear, copy all data into an array
   public Object[] toArray() {
      Object[] result = new Object[size];
      Node current = front;
      int index = 0;
      while (current != null) {
         result[index] = current.data;
         index++;
         current = current.next;
      }
      return result;
   }

   // Remove all elements
   public void popAll() {
      front = null;
      rear = null;
      size = 0;
   }
}
