class Solution {
    public int minEatingSpeed(int[] arr, int k) {
         int n = arr.length;
        int low = 1;
        int high = 0;
        int Hrs = k;
        for(int elem : arr){
            high = Math.max(high,elem);
        }
        while(low<high){
            int mid = low + (high - low)/2;
            int totalHrs= 0;
            for(int elem : arr){
                totalHrs+=(elem + mid -1)/mid;
            }
            if(totalHrs>k){
                low = mid+1;
            }else{
                high = mid;
            }
        }
        return low;
        
    }
}
