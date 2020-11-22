package leetcode;

/**
 * @author manmohan
 * This leet code trapping rain water problem
 * https://leetcode.com/problems/trapping-rain-water/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
 * In this case, 6 units of rain water (blue section) are being trapped.
 */

public class TrappingRainWater {
	
	public static int trapWater(int[] height) {
		int area = 0;
		
		if(height.length == 0)
			return area;
		
		int leftMax = 0;
		int[] rightMax = new int[height.length];
		int[] water = new int[height.length];
		
		rightMax[height.length-1] = 0;
				
		for(int i=height.length-2; i>=0; i--) {
			rightMax[i] = Math.max(rightMax[i+1], height[i+1]);
		}
		
		for(int i=0; i<height.length; i++) {
			if(Math.min(leftMax, rightMax[i]) > height[i]) {
				water[i] = Math.min(leftMax, rightMax[i]) - height[i];
			}
			
			if(leftMax < height[i])
				leftMax = height[i];
		}
		
		for(int i=0; i<water.length; i++) {
			area += water[i];
		}
		
		return area;
	}
	
	public static void main(String[] args) {
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		
		System.out.println("Total water area = "+trapWater(height));
	}

}
