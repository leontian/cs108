// Board.java
package tetris;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
 */
public class Board	{
    // Some ivars are stubbed out for you:
    private int width;
    private int height;
    private boolean[][] grid;
    private boolean DEBUG = true;
    boolean committed;
    private boolean[][] grid_bak;


    // Here a few trivial methods are provided:

    /**
	 Creates an empty board of the given width and height
	 measured in blocks.
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new boolean[width][height];
        committed = true;
        this.grid_bak = new boolean[width][height];
        // YOUR CODE HERE
    }


    /**
	 Returns the width of the board in blocks.
     */
    public int getWidth() {
        return width;
    }


    /**
	 Returns the height of the board in blocks.
     */
    public int getHeight() {
        return height;
    }


    /**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
     */
    public int getMaxHeight() {	 
        //		for (int i = this.height -1; i >= 0; i--) {
        //		    for (int j = 0; j < this.width; j++) {
        //		        if (this.grid[j][i] = true) {
        //		            return i + 1;
        //		        }
        //		    }
        //		}
        //		return 0;
        //use getColHeight
        int maxHeight = 0;
        for (int i = 0; i < this.width; i++) {
            int temp = this.getColumnHeight(i);
            if (temp > maxHeight) {
                maxHeight = temp;
            }
        }
        return maxHeight;
    }


    /**
	 Checks the board for internal consistency -- used
	 for debugging.
     */
    public void sanityCheck() {
        if (DEBUG) {
            // YOUR CODE HERE
        }
    }

    /**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.

	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
     */
    public int dropHeight(Piece piece, int x) {
        //		for (int i = 0; i < this.height;i++) {
        //		    if (this.place(piece, x, this.getColumnHeight(x) + i - piece.getSkirt()[0]) == Board.PLACE_OK) {
        //		        return this.getColumnHeight(x) + i;// YOUR CODE HERE
        //		    }
        //		} //this could work if we our design were different
        //not efficient though

        int height = 0;
        int[] skirt = piece.getSkirt();
        for (int i = 0; i < skirt.length; i++) {
            int temp = this.getColumnHeight(x+i) -skirt[i];
            if (height < temp) {
                height = temp;
            }
        }
        return height;	
    }


    /**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
     */
    public int getColumnHeight(int x) {
        for (int i = this.height - 1; i >= 0; i--) {
            if (this.getGrid(x, i)) {
                return i + 1;
            }
        }
        return 0;
    }


    /**
	 Returns the number of filled blocks in
	 the given row.
     */
    public int getRowWidth(int y) {
        int rowWidth = 0;
        for (int i = this.width - 1; i >= 0; i--) {
            if (this.getGrid(i, y)) {
                rowWidth ++;
            }
        }
        return rowWidth;
    }


    /**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
     */
    public boolean getGrid(int x, int y) {
        if ( 0 <= x && x < this.width &&
                y >= 0 && y < this.height && this.grid[x][y] == false) {
            return false;
        }
        else {
            return true;
        }
    }


    public static final int PLACE_OK = 0;
    public static final int PLACE_ROW_FILLED = 1;
    public static final int PLACE_OUT_BOUNDS = 2;
    public static final int PLACE_BAD = 3;

    /**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.

	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
     */
    public int place(Piece piece, int x, int y) {
        // flag !committed problem
        if (!committed) throw new RuntimeException("place commit problem");

        int result = PLACE_OK;

        this.committed = false;
        //		for (TPoint t:piece.getBody()) {
        //		    if (this.getGrid(x + t.x, y + t.y)) {
        //		        return PLACE_BAD;
        //		    }
        //		    if (y + t.y >= this.height) {
        //		        return PLACE_OUT_BOUNDS;
        //		    }
        //		    this.grid[x+t.x][y+t.y] = true;
        //		}
        //		for (TPoint t:piece.getBody()) {
        //	        this.grid[x+t.x][y+t.y] = true;
        //		}
        //		return result;
        for (int i = 0; i < grid.length; i++)
        {
            System.arraycopy(grid[i], 0, grid_bak[i], 0, grid[i].length);
        } 
        for (TPoint point : piece.getBody()) {
            int placeToPutAtX = point.x + x;
            int placeToPutAtY = point.y + y;
            if (x < 0 || y < 0 ||placeToPutAtX > width-1 || placeToPutAtY > height-1) {
                result = PLACE_OUT_BOUNDS;
                //undo();
                break;
            } else if (grid[placeToPutAtX][placeToPutAtY]) {
                result = PLACE_BAD;
                //undo();
                break;
            } else {
                grid[placeToPutAtX][placeToPutAtY] = true;
                if (width == getRowWidth(placeToPutAtY))
                    result = PLACE_ROW_FILLED;
            }
        }

        //sanityCheck();
        return result;
    }


    /**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
     */
    public int clearRows() {
        int rowsCleared = 0;
        int x = this.grid.length;
        int y = this.grid[0].length;
        boolean clear;
        for(int j = y-1; j >= 0; j--) {
            //	            for(int i = 0; i < x; i++) {
            //	                if(this.grid[i][j] == false) {
            //	                    clear = false;
            //	                    break;
            //	                }
            //	            }
            if(this.getRowWidth(j) == this.width) {
                for(int k = j; k < y-1; k++) {
                    for(int i = 0; i < x; i++) {
                        grid[i][k] = grid[i][k+1];
                    }
                }
                for(int i = 0; i < x; i++)
                {
                    grid[i][y-1] = false;
                }
                rowsCleared ++;
            }
        }
        // YOUR CODE HERE
        //sanityCheck();
        return rowsCleared;
    }



    /**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
     */
    public void undo() {
        if (!committed) {
            boolean[][] temp = grid;
            grid = grid_bak;
            grid_bak = temp;
            committed = true;
        }
    }


    /**
	 Puts the board in the committed state.
     */
    public void commit() {
        committed = true;
    }



    /*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility) 
     */
    public String toString() {
        StringBuilder buff = new StringBuilder();
        for (int y = height-1; y>=0; y--) {
            buff.append('|');
            for (int x=0; x<width; x++) {
                if (getGrid(x,y)) buff.append('+');
                else buff.append(' ');
            }
            buff.append("|\n");
        }
        for (int x=0; x<width+2; x++) buff.append('-');
        return(buff.toString());
    }
}


