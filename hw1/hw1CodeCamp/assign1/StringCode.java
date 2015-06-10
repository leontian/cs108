package assign1;

import java.util.HashSet;
import java.util.Set;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
	    int max = 0;
	    int temp = 1;
	    for(int i = 0; i < str.length(); i++) {
	        if((i+1) != str.length() && str.charAt(i) == str.charAt(i+1)) {
	            temp++;
	        }
	        else {
	            if(temp > max) {
	                max = temp;
	            }
                temp = 1;
	        }
	    }
		return max; 
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
	    int i = 0;
	    StringBuilder bstr = new StringBuilder();
	    for(i = 0; i < str.length(); i++) {
	        char ch = str.charAt(i);
	        if(Character.isDigit(ch)) {
	            int rep = Character.getNumericValue(ch);
	            for(int j = 0; j < rep && (i+1) != str.length(); j++) {
	                bstr.append(str.charAt(i+1));
	            }
	        }
	        else {
	            bstr.append(ch);
	        }
	    }
//	    if(str.length() > 1) {
//	        if(!Character.isDigit(str.charAt(str.length()-1))){
//	            bstr.append(str.charAt(str.length()-1));
//	        }
//	    }
	    //System.out.println(bstr.toString());
		return bstr.toString(); 
	}
	
	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */
	public static boolean stringIntersect(String a, String b, int len) {
	    HashSet<String> bhash = new HashSet<String>();
	    for(int i = 0; i < b.length() - len + 1; i++) {
	        bhash.add(b.substring(i, i+len));
	    }
	    for(int i = 0; i < a.length() - len + 1; i++) {
	        if(bhash.contains(a.substring(i, i+len))) {
	            //System.out.println(a.substring(i, i+len));
	            return true;
	        }
	    }
		return false; // TO DO ADD YOUR CODE HERE
	}
}
