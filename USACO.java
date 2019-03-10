import java.util.*;
import java.io.*;

public class USACO {

  public static void main(String[] args) {
    try {
      // File text = new File("makelake." + args[0] + ".out");
      // Scanner inf = new Scanner(text);
      // String ans = inf.nextLine();
      // System.out.println(ans);
      // System.out.println(bronze("makelake." + args[0] + ".in"));
      System.out.println(silver("ctravel." + args[0] + ".in"));
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
    return bronzeH(lake, instructions, elevation);
  }

  private static int bronzeH(int[][] lake, String[] instructions, int elevation) {
    // loop through lake and find the 9 squares associated with the coords given in instructions
    // add them to an array and sort the numbers
    // take the largest of that array and subtract the dig height from instructions from it
    // then anything in the 9 squares in the range of largest to largest-height is reduced to largest-height
    int totalDepth = 0;
    for (int i = 0; i < instructions.length; i += 3) {
      int[] depths = new int[9];
      int index = 0;
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
      int largest = depths[8];
      int difference = largest - height;
      for (int r = row; r < row + 3; r++) {
        for (int c = col; c < col + 3; c++) {
          if (lake[r][c] <= largest && lake[r][c] >= difference) {
            lake[r][c] = difference;
          }
        }
      }
    }
    for (int r = 0; r < lake.length; r++) {
      for (int c = 0; c < lake[0].length; c++) {
        if (elevation - lake[r][c] > 0) {
          totalDepth += elevation - lake[r][c];
        }
      }
    }
    return totalDepth * 5184;
  }

  public static int silver(String filename) throws FileNotFoundException {
    int row = 0;
    int col = 0;
    int seconds = 0;

    int index = 0;
    File text = new File(filename);
    Scanner inf = new Scanner(text);
    while (inf.hasNext() && index < 3) { // sets the row, col, and elevation
      String segment = inf.next();
      if (index == 0) {
        row = Integer.parseInt(segment);
      }
      if (index == 1) {
        col = Integer.parseInt(segment);
      }
      if (index == 2) {
        seconds = Integer.parseInt(segment);
      }
      index++;
    }
    String[][] pasture = new String[row][col];
    col = 0;
    int r = 0;
    while (inf.hasNextLine() && index < row + 4) { // makes the 2-d array of the pasture
      String segment = inf.nextLine();
      for (int i = 0; i < segment.length(); i++) {
        pasture[r-1][col] = segment.charAt(i) + "";
        col++;
      }
      col = 0;
      r++;
      index++;
    }
    String[] coords = inf.nextLine().split(" ");
    int r1 = Integer.parseInt(coords[0]);
    int r2 = Integer.parseInt(coords[1]);
    int c1 = Integer.parseInt(coords[2]);
    int c2 = Integer.parseInt(coords[3]);
    System.out.println(r1);
    System.out.println(r2);
    System.out.println(c1);
    System.out.println(c2);
    return -1; // so it compiles
  }

  private static void debug(String[][] array) {
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
