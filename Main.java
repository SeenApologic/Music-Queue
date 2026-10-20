import java.util.Scanner;

public class Main {

   private static final int INVALID = Integer.MIN_VALUE;

   private static String readLine(Scanner sc, String message) {
      System.out.print(message);
      if (!sc.hasNextLine()) {
         return null;
      }
      return sc.nextLine();
   }

   private static int readInt(Scanner sc, String message) {
      String line = readLine(sc, message);
      if (line == null) {
         return INVALID;
      }
      try {
         return Integer.parseInt(line.trim());
      } catch (NumberFormatException e) {
         return INVALID;
      }
   }

   private static void showMenu() {
      System.out.println();
      System.out.println("=== Music Queue (Spotify-style) ===");
      System.out.println("1. Show queue");
      System.out.println("2. Add to queue");
      System.out.println("3. Remove from queue");
      System.out.println("4. Clear queue");
      System.out.println("0. Exit");
   }

   private static void showLibrary(SongLibrary library) {
      System.out.println("---------------- LIBRARY ----------------");
      for (int i = 0; i < library.size(); i++) {
         System.out.println((i + 1) + ". " + library.get(i));
      }
      System.out.println("-----------------------------------------");
   }

   private static Song chooseSong(Scanner sc, SongLibrary library) {
      showLibrary(library);
      int number = readInt(sc, "Select song number (1-" + library.size() + ") : ");
      Song song = null;
      if (number != INVALID) {
         song = library.get(number - 1);
      }
      if (song == null) {
         System.out.println("Invalid song number.");
         return null;
      }
      return song;
   }

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      SongLibrary library = new SongLibrary();
      MusicPlayer player = new MusicPlayer();
      player.loadPlaylist(library);

      boolean running = true;
      while (running) {
         System.out.println();
         if (player.getNowPlaying() == null) {
            System.out.println("Now playing => (nothing)");
         } else {
            System.out.println("Now playing => " + player.getNowPlaying());
         }
         showMenu();
         String line = readLine(sc, "Choose menu : ");
         if (line == null) { // input closed
            break;
         }
         String choice = line.trim();

         if (choice.equals("1")) {
            player.showQueue();

         } else if (choice.equals("2")) { // [Feature 1] Add to Queue
            Song song = chooseSong(sc, library);
            if (song != null) {
               player.addToQueue(song);
               System.out.println("Added to queue => " + song);
            }

         } else if (choice.equals("3")) { // [Feature 2] Remove from Queue
            if (player.getUserQueueSize() == 0) {
               System.out.println("Next in Queue is empty, nothing to remove.");
            } else {
               player.showQueue();
               int position = readInt(sc, "Remove position in Next in Queue (1-" + player.getUserQueueSize() + ") : ");
               Song removed = null;
               if (position != INVALID) {
                  removed = player.removeFromQueue(position - 1);
               }
               if (removed == null) {
                  System.out.println("Invalid position.");
               } else {
                  System.out.println("Removed => " + removed);
               }
            }

         } else if (choice.equals("4")) { // [Feature 3] Clear Queue 
            if (player.getUserQueueSize() == 0) {
               System.out.println("Next in Queue is empty, nothing to clear.");
            } else {
               int removed = player.clearQueue();
               System.out.println("Cleared " + removed + " song(s) from Next in Queue.");
            }

         } else if (choice.equals("0")) {
            System.out.println("Bye!");
            running = false;

         } else {
            System.out.println("Invalid menu, please try again.");
         }
      }
      sc.close();
   }
}
