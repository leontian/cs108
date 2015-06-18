// TabooTest.java
// Taboo class tests -- nothing provided.
package assign1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class TabooTest {
    // utility -- converts a string to a list with one
    // elem for each char.
    private List<String> stringToList(String s) {
        List<String> list = new ArrayList<String>();
        for (int i=0; i<s.length(); i++) {
            list.add(String.valueOf(s.charAt(i)));
            // note: String.valueOf() converts lots of things to string form
        }
        return list;
    }
    //basic
    @Test
    public void noFollowTest1() {
        Set<Integer> orig = new HashSet<Integer>();
        orig.add(2);
        List<Integer> a = Arrays.asList(1, 2, 3, 4);
        Taboo<Integer> tbo = new Taboo<Integer>(a);
        assertEquals(orig, tbo.noFollow(1));
    }
    
    @Test
    public void noFollowTest2() {
        Set<Integer> orig = new HashSet<Integer>();
        List<Integer> a = Arrays.asList(1, 2, 3, 4);
        Taboo<Integer> tbo = new Taboo<Integer>(a);
        assertEquals(orig, tbo.noFollow(4));
    }
    
    @Test
    public void noFollowTest3() {
        Set<Integer> orig = new HashSet<Integer>();
        orig.add(2);
        orig.add(4);
        List<Integer> a = Arrays.asList(1, 2, 1, 4);
        Taboo<Integer> tbo = new Taboo<Integer>(a);
        assertEquals(orig, tbo.noFollow(1));
    }
    
    @Test
    public void noFollowTest4() {
        Set<Integer> orig = new HashSet<Integer>();
        List<Integer> a = Arrays.asList(1, 2, null, 1, 4);
        Taboo<Integer> tbo = new Taboo<Integer>(a);
        assertEquals(orig, tbo.noFollow(2));
    }
    
    @Test
    public void reduceTest1() {
        List<Integer> orig = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        List<Integer> after = Arrays.asList(1, 3);       
        List<Integer> rules = Arrays.asList(1, 2, 3, 4);
        Taboo<Integer> tbo = new Taboo<Integer>(rules);
        tbo.reduce(orig);
        assertTrue(Arrays.deepEquals(after.toArray(), orig.toArray()));
    }
    
    @Test
    public void reduceTest2() {
        List<String> orig = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
        List<String> after = Arrays.asList("a");       
        List<String> rules = Arrays.asList("a", "b", "a", "c", "a", "d");
        Taboo<String> tbo = new Taboo<String>(rules);
        tbo.reduce(orig);
        assertTrue(Arrays.deepEquals(after.toArray(), orig.toArray()));
    }
}
