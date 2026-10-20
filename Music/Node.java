
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
