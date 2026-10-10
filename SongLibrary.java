public class SongLibrary {
   private Song[] songs;

   // create a library with sample songs
   public SongLibrary() {
      songs = new Song[] {
         new Song(1, "Blinding Lights", "The Weeknd", 200),
         new Song(2, "Shape of You", "Ed Sheeran", 234),
         new Song(3, "Someone Like You", "Adele", 285),
         new Song(4, "Bad Guy", "Billie Eilish", 194),
         new Song(5, "Levitating", "Dua Lipa", 203),
         new Song(6, "Perfect", "Ed Sheeran", 263),
         new Song(7, "Rolling in the Deep", "Adele", 228),
         new Song(8, "Happier Than Ever", "Billie Eilish", 298),
         new Song(9, "Starboy", "The Weeknd", 230),
         new Song(10, "Don't Start Now", "Dua Lipa", 183)
      };
   }

   // Time Complexity : O(1)
   public int size() {
      return songs.length;
   }

   // Get song by index (0-based), null if index is invalid
   // Time Complexity : O(1)
   public Song get(int index) {
      if (index < 0 || index >= songs.length) {
         return null;
      }
      return songs[index];
   }
}
