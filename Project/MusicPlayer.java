public class MusicPlayer {
   private Song nowPlaying;                // Now Playing
   private DoublyLinkedList userQueue;     // Next in Queue (songs added by the user)
   private QueueLinkedList playlistQueue;  // Next from Playlist (FIFO)

   // create an empty player
   public MusicPlayer() {
      nowPlaying = null;
      userQueue = new DoublyLinkedList();
      playlistQueue = new QueueLinkedList();
   }

   public Song getNowPlaying() {
      return nowPlaying;
   }

   public int getUserQueueSize() {
      return userQueue.length();
   }

   public int getPlaylistSize() {
      return playlistQueue.size();
   }

   // true if nothing is playing and both queues are empty
   // Time Complexity : O(1)
   public boolean isEmpty() {
      return nowPlaying == null && userQueue.isEmpty() && playlistQueue.isEmpty();
   }

   // Load every song in the library into the playlist, then start the first song
   // Time Complexity : O(n) เพราะ enqueue n ครั้ง ครั้งละ O(1)
   public void loadPlaylist(SongLibrary library) {
      nowPlaying = null;
      userQueue.clear();
      playlistQueue.popAll();
      for (int i = 0; i < library.size(); i++) {
         playlistQueue.enqueue(library.get(i));
      }
      skipNext();
   }

   // [Feature 1] Add to Queue : put the song at the end of "Next in Queue"
   // FUNCTION addToQueue(song):
   //    IF song IS NOT NULL THEN INSERT song AT END OF userQueue
   // END FUNCTION
   // Time Complexity : O(1)
   public void addToQueue(Song song) {
      if (song == null) {
         return;
      }
      userQueue.insert(song);
   }

   // [Feature 2] Skip Next : songs added by the user are played before the playlist
   // FUNCTION skipNext():
   //    IF userQueue IS NOT EMPTY THEN
   //       SET nowPlaying AS DATA REMOVED FROM HEAD OF userQueue
   //    ELSE IF playlistQueue IS NOT EMPTY THEN
   //       SET nowPlaying AS dequeue OF playlistQueue
   //    ELSE THEN
   //       SET nowPlaying AS NULL
   //    END IF
   // END FUNCTION
   // Time Complexity : O(1)
   public void skipNext() {
      if (!userQueue.isEmpty()) {
         nowPlaying = (Song) userQueue.delete(0);
      } else if (!playlistQueue.isEmpty()) {
         nowPlaying = (Song) playlistQueue.dequeue();
      } else {
         nowPlaying = null;
      }
   }

   // [Feature 3] Remove from Queue : remove a song from "Next in Queue" by position (0-based)
   // Return the removed song, null if position is invalid
   // Time Complexity : best O(1) (head/tail), worst O(n) (middle)
   public Song removeFromQueue(int position) {
      return (Song) userQueue.delete(position);
   }

   // Show Now Playing, Next in Queue, Next from Playlist
   // Time Complexity : O(n)
   public void showQueue() {
      System.out.println("---------------- QUEUE ----------------");
      if (nowPlaying == null) {
         System.out.println("Now playing : (nothing)");
      } else {
         System.out.println("Now playing : " + nowPlaying);
      }

      System.out.println("Next in Queue (" + userQueue.length() + ")");
      Object[] userSongs = userQueue.toArray();
      if (userSongs.length == 0) {
         System.out.println("   (empty)");
      }
      for (int i = 0; i < userSongs.length; i++) {
         System.out.println("   " + (i + 1) + ". " + userSongs[i]);
      }

      System.out.println("Next from Playlist (" + playlistQueue.size() + ")");
      Object[] playlistSongs = playlistQueue.toArray();
      if (playlistSongs.length == 0) {
         System.out.println("   (empty)");
      }
      for (int i = 0; i < playlistSongs.length; i++) {
         System.out.println("   " + (i + 1) + ". " + playlistSongs[i]);
      }
      System.out.println("---------------------------------------");
   }
}
