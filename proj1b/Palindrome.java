public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> q = new LinkedListDeque<>();
        int num = word.length();
        for(int i = 0; i < num; i++){
            char charAti = word.charAt(i);
            q.addLast(charAti);
        }//end of for loop
        return q;
    }//end of  wordToDeque

    public boolean isPalindrome(String word){
        Deque d = wordToDeque(word);
        return isPalindromehelper(d);
    }//end of isPalindrome
    private boolean isPalindromehelper(Deque d){
        if (d.size() == 0 || d.size() == 1){
            return true;
        }else{
            Object first = d.removeFirst();
            Object last = d.removeLast();
            if (first == last){
                return isPalindromehelper(d);
            }
            return false;
        }
    }//end of isPalindromehelper
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque d = wordToDeque(word);
        return isPalindromehelper(d,cc);
    }
    private boolean isPalindromehelper(Deque d, CharacterComparator cc){
        if (d.size() == 0 || d.size() == 1){
            return true;
        }else{
            Object first = d.removeFirst();
            Object last = d.removeLast();
            char f = (Character) first;
            char l = (Character) last;
            if (cc.equalChars(f,l)){
                return isPalindromehelper(d,cc);
            }
            return false;
        }
    }
}//end of class
