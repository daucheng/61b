import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome(){
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("ABCA"));
        assertFalse(palindrome.isPalindrome("Palindrome"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(" "));
        assertTrue(palindrome.isPalindrome("MadamimadaM"));
        assertTrue(palindrome.isPalindrome("racecar"));
    }
}//end of class
