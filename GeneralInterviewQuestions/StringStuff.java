import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.Map;

/**
 * Created by robert on 11/29/15.
 */
public class StringStuff {

    public StringStuff() {

    }

    /**
     * Reverses string iteratively.
     * @param str
     * @return Reversed string.
     */
    public String reverseString(String str) {
        String newString = "";
        for (int i = str.length() - 1; i > -1; i--) {
            newString += str.charAt(i);
        }
        return newString;
    }

    /**
     * Reverses a string recursively.
     * @param str
     * @return Reversed string.
     */
    public String reverseRecurse(String str) {
        if (str.length() <= 1) {
            return str;
        }
        return reverseRecurse(str.substring(1)) + str.charAt(0);
    }

    /**
     * Determines if a string is a palindrome or not.
     * @param str
     * @return true if the String is a palindrome. False if it is not.
     */
    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }

        int i = 0;
        int j = str.length() - 1;

        while (i <= j && i < str.length() && j > -1) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    /**
     * Finds respective occurrences for each char in a string in O(n) time.
     * This was a question from CareerBuilder.
     * @param str String you want to have characters counted for.
     * @return Map with integer occurrences of each character.
     */
    public Map<Character, Integer> countChars(String str) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (map.get(current) != null) {
                map.put(current, map.get(current) + 1);
            } else {
                map.put(current, 1);
            }
        }
        return map;
    }

    /**
     * Changes 0s to 1s and 1s to 0s.
     * @param bitString
     * @return
     */
    public String flipBits(String bitString) {
        if (bitString == null || bitString.length() == 0) {
            return bitString;
        }

        int first = Integer.parseInt(String.valueOf(bitString.charAt(0)));
        if (first != 0 && first != 1) {
            throw new IllegalArgumentException("Only 0s and 1s are allowed in bitstrings, fam");
        }

        int swap = (first == 1) ? 0 : 1;
        return swap + flipBits(bitString.substring(1));
    }

    public boolean hasDuplicates(String str) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (map.get(current) != null) {
                return true;
            }
            map.put(current, 1);
        }

        return false;
    }

    public static void main(String[] args) {
        StringStuff strings = new StringStuff();
        System.out.println(strings.hasDuplicates("12344"));
    }
}
