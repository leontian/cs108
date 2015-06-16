package assign1;

import java.util.*;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
        Map<T, Integer> hashA = new HashMap<T, Integer>();
        Map<T, Integer> hashB = new HashMap<T, Integer>();
        for (T item : a) {
            int count = hashA.containsKey(item) ? hashA.get(item) : 0;
            hashA.put(item, count + 1);
        }
        for (T item : b) {
            int count = hashB.containsKey(item) ? hashB.get(item) : 0;
            hashB.put(item, count + 1);
        }
        int r = 0;
        for(T key : hashA.keySet())
            if (hashA.get(key) == hashB.get(key))
                r += 1;
        return r; 
	}
}
