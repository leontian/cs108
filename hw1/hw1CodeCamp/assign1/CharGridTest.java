// Test cases for CharGrid -- a few basic tests are provided.
package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

public class CharGridTest {
	
	@Test
	public void testCharArea1() {
		char[][] grid = new char[][] {
				{'a', 'y', ' '},
				{'x', 'a', 'z'},
			};
		
		
		CharGrid cg = new CharGrid(grid);
				
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
	}
	
	
	@Test
	public void testCharArea2() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'}
			};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
		assertEquals(0, cg.charArea('d'));
	}
	
    @Test
    //edge, zero
    public void testCountPlus1() {
        char[][] grid = new char[][] {
                {'c', 'a', ' '},
                {'b', ' ', 'b'},
                {' ', ' ', 'a'}
            };
        char[][] grid2 = new char[][] {
                {'c', 'b', ' '},
                {'b', 'b', 'b'},
                {' ', 'b', 'a'},
                {' ', 'b', 'a'}
            };
        
        CharGrid cg = new CharGrid(grid);
        CharGrid cg2 = new CharGrid(grid2);
        
        assertEquals(0, cg.countPlus());
        assertEquals(0, cg2.countPlus());

    }
    @Test
    //single plus
    public void testCountPlus2() {
        char[][] grid = new char[][] {
                {'c', 'b', ' '},
                {'b', 'b', 'b'},
                {' ', 'b', 'a'}
            };
        char[][] grid2 = new char[][] {
                {'c', 'b', ' '},
                {'b', 'b', 'b'},
                {' ', 'b', 'a'},
                {' ', ' ', 'a'}
            };
        
        CharGrid cg = new CharGrid(grid);
        CharGrid cg2 = new CharGrid(grid2);
        
        assertEquals(1, cg.countPlus());
        assertEquals(1, cg2.countPlus());

    }
    @Test
    //large grid
    public void testCountPlus3() {
        char[][] grid = new char[][] {
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', 'x', ' '},
                {'p', 'p', 'p', 'p', 'p', ' ', 'x', 'x', 'x'},
                {' ', ' ', 'p', ' ', ' ', 'y', ' ', 'x', ' '},
                {' ', ' ', 'p', ' ', 'y', 'y', 'y', ' ', ' '},
                {'z', 'z', 'z', 'z', 'z', 'y', 'z', 'z', 'z'},
                {' ', ' ', 'x', 'x', ' ', 'y', ' ', ' ', ' '},
            };

        
        CharGrid cg = new CharGrid(grid);
        
        assertEquals(2, cg.countPlus());

    }
}
