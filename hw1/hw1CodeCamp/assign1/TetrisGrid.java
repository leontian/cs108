//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.
package assign1;

public class TetrisGrid {
	
    private boolean[][] grid;
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
	    this.grid = grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
	    int x = this.grid.length;
	    int y = this.grid[0].length;
	    boolean clear;
	    for(int j = y-1; j >= 0; j--) {
	        clear = true;
	        for(int i = 0; i < x; i++) {
	            if(this.grid[i][j] == false) {
	                clear = false;
	                break;
	            }
	        }
	        if(clear) {
	            for(int k = j; k < y-1; k++) {
	                for(int i = 0; i < x; i++) {
	                    grid[i][k] = grid[i][k+1];
	                }
	            }
	            for(int i = 0; i < x; i++)
	            {
	                grid[i][y-1] = false;
	            }
	        }
	    }
	}
	
	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return this.grid; // TODO YOUR CODE HERE
	}
}
