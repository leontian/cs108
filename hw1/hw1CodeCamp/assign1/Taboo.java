/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/
package assign1;

import java.util.*;

public class Taboo<T> {
    
    public List<T> rules;
	
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
	    this.rules = rules;
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
	    Set<T> r = Collections.emptySet();
	    boolean next = false;
	    for(T i:this.rules) {
	        if(next == true && i!= null) {
	            r.add(i);
	        }
	        if(i == elem) {
	            next = true;
	        }
	    }
	    return r;
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
	    Set<T> noF = Collections.emptySet();
	    int idx = 0;
	    for(T l:list) {
	        if(noF.contains(l)) {
	            list.remove(idx);
	        }
	        else {
	            noF = noFollow(l);
	            idx++;
	        }
	    }
	}
}
