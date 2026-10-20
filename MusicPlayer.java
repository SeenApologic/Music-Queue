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

   public void loadPlaylist(SongLibrary library) {
      nowPlaying = null;
      userQueue.clear();
      playlistQueue.popAll();
      for (int i = 0; i < library.size(); i++) {
         playlistQueue.enqueue(library.get(i));
      }
      skipNext();
   }

   // [Feature 1] Add to Queue
   public void addToQueue(Song song) {
      if (song == null) {
         return;
      }
      userQueue.insert(song);
   }

   public void skipNext() {
      if (!userQueue.isEmpty()) {
         nowPlaying = (Song) userQueue.delete(0);
      } else if (!playlistQueue.isEmpty()) {
         nowPlaying = (Song) playlistQueue.dequeue();
      } else {
         nowPlaying = null;
      }
   }

   // [Feature 2] Remove from Queue 
   public Song removeFromQueue(int position) {
      return (Song) userQueue.delete(position);
   }

   // Feature 3 Clear Queue 
   public int clearQueue() {
      int removed = userQueue.length();
      userQueue.clear();
      return removed;
   }

   // Show Now Playing, Next in Queue, Next from Playlist
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
