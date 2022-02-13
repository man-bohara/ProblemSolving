import java.util.*;

class MinWaysToTraverseGraph {

	// O(n * m) time | O(n * m) space
  public int numberOfWaysToTraverseGraph(int width, int height) {
    // Write your code here.
		
		int[][] ways = new int[height][width];
		
		for(int r=0; r<height; r++) {
			ways[r][0] = 1;
		}
		
		for(int c=0; c<width; c++) {
			ways[0][c] = 1;
		}
		
		for(int r=1; r<height; r++) {
			for(int c=1; c<width; c++) {
				ways[r][c] = ways[r-1][c] + ways[r][c-1];
			}
		}
		
    return ways[height-1][width-1];
  }
}
