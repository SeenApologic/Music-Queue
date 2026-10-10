public class DoublyLinkedList {
   private Node head;
   private Node tail;
   private int size; // number of nodes in list

   // create an empty list
   public DoublyLinkedList() {
      this.head = null;
      this.tail = null;
      this.size = 0;
   }

   // Time Complexity : O(1)
   public boolean isEmpty() {
      return size == 0;
   }

   // Time Complexity : O(1)
   public int length() {
      return size;
   }

   // Insert at the end (Add to Queue)
   // Time Complexity : O(1) เพราะมี tail ไม่ต้อง traverse
   public void insert(Object value) {
      Node newNode = new Node(value);
      if (head == null) {
         head = newNode;
         tail = newNode;
      } else {
         tail.next = newNode;
         newNode.previous = tail;
         tail = newNode;
      }
      size++;
   }

   // Delete node at the position, return its data (null if position is invalid)
   // Time Complexity : best O(1) (head/tail), worst O(n) (middle)
   public Object delete(int position) {
      if (position < 0 || position >= size) {
         return null;
      }
      Node current;
      if (position == 0) {
         current = head;
      } else if (position == size - 1) {
         current = tail;
      } else {
         current = head;
         for (int i = 0; i < position; i++) {
            current = current.next;
         }
      }
      // point previous.next (or head) to the next node
      if (current.previous != null) {
         current.previous.next = current.next;
      } else {
         head = current.next;
      }
      // point next.previous (or tail) to the previous node
      if (current.next != null) {
         current.next.previous = current.previous;
      } else {
         tail = current.previous;
      }
      Object data = current.data;
      current.previous = null;
      current.next = null;
      size--;
      return data;
   }

   // Traversal from head to tail, copy all data into an array
   // Time Complexity : O(n)
   public Object[] toArray() {
      Object[] result = new Object[size];
      Node current = head;
      int index = 0;
      while (current != null) {
         result[index] = current.data;
         index++;
         current = current.next;
      }
      return result;
   }

   // Remove every node
   // Time Complexity : O(1)
   public void clear() {
      head = null;
      tail = null;
      size = 0;
   }
}
