class Solution {
    public boolean canAliceWin(int n) {
        int remove = 10;
        boolean aliceTurn = true;

        while (n >= remove && remove > 0) {
            n -= remove;
            remove--;
            aliceTurn = !aliceTurn;
        }

        // The loop stops when the current player cannot make a move,
        // meaning the previous player (the opposite of aliceTurn) won.
        return !aliceTurn;
    }
}