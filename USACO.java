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
    String m = "";
    File text = new File(filename);
    Scanner inf = new Scanner(text);
    while (inf.hasNext() && index < 4) { // sets the row, col, and elevation
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
    while (inf.hasNextLine() && index < row+5) { // makes the 2-d array of the lake's heights
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
    while (inf.hasNextLine()) { // makes a string of stomping instructions
      m += inf.nextLine();
      m += " ";
    }
    String[] instructions = m.split(" ");
    return bronzeH(lake, instructions);
  }

  private static int bronzeH(int[][] lake, String[] instructions) {
    // loop through lake and find the 9 squares associated with the coords given in instructions
    // add them to an array and sort the numbers
    // take the largest of that array and subtract the dig height from instructions from it
    // then anything in the 9 squares in the range of largest to largest-height is reduced to largest-height
    int[] depths = new int[9];
    int index = 0;
    for (int i = 0; i < instructions.length; i += 3) {
      int row = Integer.parseInt(instructions[i]) - 1;
      int col = Integer.parseInt(instructions[i+1]) - 1;
      int height = Integer.parseInt(instructions[i+2]);
      for (int r = row; r < row + 3; r++) {
        for (int c = col; c < col + 3; c++) {
          depths[index] = lake[r][c];
          index++;
        }
      }
      Arrays.sort(depths);
      depths = new int[9];
      index = 0;
    }
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
