// Node แบบ Doubly Linked List (Ch.4): previous, data, next
// Queue และ Stack ใช้ Node ตัวเดียวกัน แต่ไม่ใช้ previous
public class Node {
   public Node previous;
   public Object data;
   public Node next;

   public Node(Object data) {
      this.previous = null;
      this.data = data;
      this.next = null;
   }
}
