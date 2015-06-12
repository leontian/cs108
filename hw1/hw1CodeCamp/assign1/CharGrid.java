// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

package assign1;

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
	    int minx = this.grid.length;
	    int miny;
	    if(minx > 0) {
	        miny = grid[0].length;
	    }
	    else {
	        miny = 0;
	    }
 
	    int maxx = 0;
	    int maxy = 0;
	    
	    for(int i = 0; i < this.grid.length; i++) {
	        if(this.grid.length == 0) {
	            return 0;
	        }
	        for(int j = 0; j < this.grid[0].length; j++) {
	            if(grid[i][j] == ch) {
	                if(i < minx) {
	                    minx = i;
	                }
	                if(i > maxx) {
	                    maxx = i;
	                }
	                if(j < miny) {
	                    miny = j;
	                }
	                if (j > maxy) {
	                    maxy = j;
	                }
	            }
	        }	        
	    }
	    if(maxx >= minx && maxy >= miny) {
	        return (maxx - minx + 1) * (maxy - miny + 1);
	    }
	    else {
	        return 0;
	    }
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
	    if(this.grid.length < 3 || this.grid[0].length < 3) {
	        return 0;
	    }
	    int count = 0;
	    int row = this.grid.length;
	    int col = this.grid[0].length;
	    for(int i = 1; i < row; i++) {
	        for(int j = 0; j < col; j++) {
	            int k = armLength(i, j, -1, 0);
	            if(k > 1 & k == armLength(i, j, 0, 1) 
	                    && k == armLength(i, j, 1, 0) 
	                    && k == armLength(i, j, 0, -1)) {
	                count++;
	            }
	        }
	    }
	    return count;
	}

	private int armLength(int i, int j, int x, int y) {
	    int k = 1;
	    char ch = this.grid[i][j];
	    while(i+k*x >= 0 && i+k*x <= this.grid.length-1
	            && j+k*y >= 0 && j+k*y <= this.grid[0].length-1) {
	        if(this.grid[i+k*x][j+k*y] == ch) {
	            k++;
	        }
	        else {
	            break;
	        }
	    }
	    return k;
	}
	
}
