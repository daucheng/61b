import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testequalChars(){
       assertTrue(offByOne.equalChars('a','a'));
       assertTrue(offByOne.equalChars('1','1'));
       assertFalse(offByOne.equalChars('a','A'));
       assertFalse(offByOne.equalChars('a','1'));
    }//end of testequalChars
}// end of class
