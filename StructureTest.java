public class StructureTest {
   private static int passed = 0;
   private static int failed = 0;

   private static void check(String name, boolean condition) {
      if (condition) {
         passed++;
         System.out.println("[PASS] " + name);
      } else {
         failed++;
         System.out.println("[FAIL] " + name);
      }
   }

   public static void main(String[] args) {
      testQueue();
      testDoublyLinkedList();
      testLibrary();
      testMusicPlayer();
      System.out.println();
      System.out.println("Passed = " + passed + ", Failed = " + failed);
      if (failed > 0) {
         System.exit(1);
      }
   }

   // ---------- QueueLinkedList ----------
   private static void testQueue() {
      QueueLinkedList queue = new QueueLinkedList();
      check("Queue: new queue is empty", queue.isEmpty());
      check("Queue: dequeue on empty returns null", queue.dequeue() == null);
      check("Queue: peek on empty returns null", queue.peek() == null);
      queue.enqueue(10);
      queue.enqueue(11);
      queue.enqueue(12);
      check("Queue: size = 3", queue.size() == 3);
      check("Queue: peek = 10", (Integer) queue.peek() == 10);
      check("Queue: dequeue = 10 (FIFO)", (Integer) queue.dequeue() == 10);
      check("Queue: peek = 11", (Integer) queue.peek() == 11);
      queue.dequeue();
      queue.dequeue();
      check("Queue: empty after dequeue all", queue.isEmpty());
      queue.enqueue(99); // rear must be reset correctly after becoming empty
      check("Queue: enqueue after empty works", (Integer) queue.peek() == 99 && queue.size() == 1);
      queue.popAll();
      check("Queue: popAll clears", queue.isEmpty());
   }

   // ---------- DoublyLinkedList ----------
   private static String listToString(DoublyLinkedList list) {
      Object[] arr = list.toArray();
      String s = "";
      for (int i = 0; i < arr.length; i++) {
         s += arr[i] + (i < arr.length - 1 ? "," : "");
      }
      return s;
   }

   private static void testDoublyLinkedList() {
      DoublyLinkedList list = new DoublyLinkedList();
      check("DLL: new list is empty", list.isEmpty() && list.length() == 0);
      check("DLL: delete on empty returns null", list.delete(0) == null);
      list.insert("A");
      list.insert("B");
      list.insert("C");
      list.insert("D");
      list.insert("E");
      check("DLL: insert at end => A,B,C,D,E", listToString(list).equals("A,B,C,D,E"));
      check("DLL: delete middle returns C", "C".equals(list.delete(2)));
      check("DLL: after delete middle => A,B,D,E", listToString(list).equals("A,B,D,E"));
      check("DLL: delete head returns A", "A".equals(list.delete(0)));
      check("DLL: delete tail returns E", "E".equals(list.delete(list.length() - 1)));
      check("DLL: after delete head/tail => B,D", listToString(list).equals("B,D"));
      list.insert("F"); // tail must be updated correctly
      check("DLL: insert at end after delete tail => B,D,F", listToString(list).equals("B,D,F"));
      check("DLL: delete position = size returns null", list.delete(3) == null);
      check("DLL: delete negative position returns null", list.delete(-1) == null);
      check("DLL: size unchanged after invalid delete", list.length() == 3);
      list.delete(0);
      list.delete(0);
      list.delete(0);
      check("DLL: delete all => empty", list.isEmpty() && list.length() == 0);
      list.insert("N"); // head/tail must be reset after becoming empty
      check("DLL: insert after empty works", listToString(list).equals("N"));
      list.clear();
      check("DLL: clear empties the list", list.isEmpty());
   }

   // ---------- SongLibrary ----------
   private static void testLibrary() {
      SongLibrary library = new SongLibrary();
      check("Library: has 10 songs", library.size() == 10);
      check("Library: get(0) is song id 1", library.get(0).getId() == 1);
      check("Library: get invalid index returns null", library.get(-1) == null && library.get(99) == null);
   }

   // ---------- MusicPlayer ----------
   private static void testMusicPlayer() {
      SongLibrary library = new SongLibrary();
      MusicPlayer player = new MusicPlayer();
      check("Player: nothing playing at start", player.getNowPlaying() == null && player.isEmpty());
      player.skipNext(); // must not crash when everything is empty
      check("Player: skip on empty does not crash", player.getNowPlaying() == null);
      check("Player: remove on empty returns null", player.removeFromQueue(0) == null);

      player.loadPlaylist(library);
      check("Player: starts with song 1", player.getNowPlaying().getId() == 1);
      check("Player: playlist has 9 left", player.getPlaylistSize() == 9);

      // Feature 1 : Add to Queue
      player.addToQueue(null);
      check("Player: add null is ignored", player.getUserQueueSize() == 0);
      player.addToQueue(library.get(4)); // song 5
      player.addToQueue(library.get(6)); // song 7
      check("Player: userQueue size = 2", player.getUserQueueSize() == 2);

      // Feature 2 : Skip Next
      player.skipNext();
      check("Player: skip plays first added song (5)", player.getNowPlaying().getId() == 5);
      player.skipNext();
      check("Player: then second added song (7)", player.getNowPlaying().getId() == 7);
      player.skipNext();
      check("Player: then back to playlist (2)", player.getNowPlaying().getId() == 2);
      check("Player: playlist has 8 left", player.getPlaylistSize() == 8);

      // Feature 3 : Remove from Queue
      check("Player: remove from empty userQueue returns null", player.removeFromQueue(0) == null);
      player.addToQueue(library.get(7)); // song 8
      player.addToQueue(library.get(8)); // song 9
      player.addToQueue(library.get(9)); // song 10
      check("Player: remove negative position returns null", player.removeFromQueue(-1) == null);
      check("Player: remove position = size returns null", player.removeFromQueue(3) == null);
      check("Player: remove middle returns song 9", player.removeFromQueue(1).getId() == 9);
      check("Player: remove head returns song 8", player.removeFromQueue(0).getId() == 8);
      check("Player: remove tail returns song 10", player.removeFromQueue(0).getId() == 10);
      check("Player: userQueue empty after remove", player.getUserQueueSize() == 0);

      // order after removing: song 8 and 10 should be gone
      player.addToQueue(library.get(2)); // song 3
      player.addToQueue(library.get(3)); // song 4
      player.removeFromQueue(0);         // remove song 3
      player.skipNext();
      check("Player: removed song is not played (plays 4)", player.getNowPlaying().getId() == 4);

      // play everything until the end, must stop with null and not crash
      int guard = 0;
      while (player.getNowPlaying() != null && guard < 100) {
         player.skipNext();
         guard++;
      }
      check("Player: reaches end of queue (nowPlaying = null)", player.getNowPlaying() == null);
      check("Player: playlist is empty at the end", player.getPlaylistSize() == 0 && player.isEmpty());
      player.skipNext();
      check("Player: skip after end does not crash", player.getNowPlaying() == null);

      // add a song after the end of the queue, then skip must play it
      player.addToQueue(library.get(0));
      check("Player: not empty after adding at the end", !player.isEmpty());
      player.skipNext();
      check("Player: skip plays song added after end (1)", player.getNowPlaying().getId() == 1);
   }
}
