class Solution {
    public double angleClock(int hour, int minutes) {
        // Calculate degrees per minute and hour
        double minuteAngle = minutes * 6.0; // 360 degrees / 60 minutes
        // Hour hand moves 30 degrees per hour, plus 0.5 degrees per minute
        double hourAngle = (hour % 12) * 30.0 + minutes * 0.5; 
        
        double diff = Math.abs(minuteAngle - hourAngle);
        
        // Return the smaller angle
        return Math.min(diff, 360.0 - diff);
    }
}