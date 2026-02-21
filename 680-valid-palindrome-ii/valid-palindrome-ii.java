class Solution {
    public boolean validPalindrome(String s) {
        int i = 0 , j = s.length() - 1 ;

        // find a point where the char at i-th and j-th are different
        while( i <= j ){
            if( s.charAt(i) == s.charAt(j) ){
                i++ ; j-- ;
            }
            else{
                // If they are unequal
                // case1 : delete ith char and check [i+1,j] -> is it palindrome
                // case2 : delete jth char and check [i,j-1] -> is it palindrome\

                if( fun( s , i+1 , j ) || fun( s , i , j-1 ) )
                    return true ;
                return false ;


                
            }
        }
        return true;
    }
    public boolean fun( String s , int start , int end ){
        int i = start , j = end ;
        while( i < j ){
            if( s.charAt(i) == s.charAt(j) ){
                i++ ; j-- ;
            }
            else
                return false ;
        }
        return true ;
    }
}