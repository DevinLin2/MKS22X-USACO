import java.util.*;
import java.io.*;

public class USACO {

  public static void main(String[] args) {
    try {
      bronze("C:\\Users\\dlin0\\MKS22X-USACO\\testCases\\makelake.1.in");
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
    //int[][] lake; initiate this after finding out row and col

    File text = new File(filename);
    Scanner inf = new Scanner(text);
    String line = "";
    while (inf.hasNext()) {
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
      line += segment;
    }
    int[][] lake = new int[row][col];
    return -1; // so it compiles
  }

  public static int silver(String filename) {
    return -1; // so it compiles
  }
}
