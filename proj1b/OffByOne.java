public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y){
        int d = x - y;
        if (Math.abs(d) == 1){
            return true;
        }
        return false;
    }//end of equalChars
}//end of class
