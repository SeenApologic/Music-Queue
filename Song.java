public class Song {
   private int id;
   private String title;
   private String artist;
   private int duration; // seconds

   public Song(int id, String title, String artist, int duration) {
      this.id = id;
      this.title = title;
      this.artist = artist;
      this.duration = duration;
   }

   public int getId() {
      return id;
   }

   public String getTitle() {
      return title;
   }

   public String getArtist() {
      return artist;
   }

   public int getDuration() {
      return duration;
   }

   public String toString() {
      int min = duration / 60;
      int sec = duration % 60;
      return title + " - " + artist + " (" + min + ":" + (sec < 10 ? "0" : "") + sec + ")";
   }
}
