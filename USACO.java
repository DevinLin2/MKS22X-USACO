import java.util.*;
import java.io.*;

public class USACO {

  public static void main(String[] args) {
    try {
      bronze("makelake.1.in");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static int bronze(String filename) throws FileNotFoundException {
    int row = 0;
    int col = 0;
    int elevation = 0;
    int numOfInstructions = 0;
    int index = 0;

    String addToArray = ""; // loop through a segment and add this to array
    File text = new File(filename);
    Scanner inf = new Scanner(text);
    while (inf.hasNext() && index < 4) {
      String segment = inf.next();
      if (index == 0) {
        row = Integer.parseInt(segment);
      }
      if (index == 1) {
        col = Integer.parseInt(segment);
      }
      if (index == 2) {
        elevation = Integer.parseInt(segment);
      }
      if (index == 3) {
        numOfInstructions = Integer.parseInt(segment);
      }
      index++;
    }
    int[][] lake = new int[row][col];
    while(inf.hasNextLine() && index < row+5) {
      String segment = inf.nextLine();
      for (int i = 0; i < segment.length(); i++) {
        if (!(segment.charAt(i) == ' ')) {
          addToArray += segment.charAt(i);
          if (i + 1 == segment.length()) {
            lake[index-5][col] = Integer.parseInt(addToArray);
            col++;
            addToArray = "";
          }
        } else {
          lake[index-5][col] = Integer.parseInt(addToArray);
          col++;
          addToArray = "";
        }
      }
      col = 0;
      addToArray = "";
      index++;
    }
    debug(lake);
    return -1; // so it compiles
  }

  public static int silver(String filename) {
    return -1; // so it compiles
  }

  private static void debug(int[][] array) {
    String ans = "";
    for (int r = 0; r < array.length; r++) {
      for (int c = 0; c < array[0].length; c++) {
        ans += array[r][c] + " ";
      }
      ans += "\n";
    }
    System.out.println(ans);
  }
}
