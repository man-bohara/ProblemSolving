package leetcode;

/**
 * @author manmohan
 * This is leet code Product of Array Except Self problem
 * https://leetcode.com/problems/product-of-array-except-self/
 * 
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * Example:
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 */
public class ProductOfArrayExceptSelf {

	public static void main(String[] args) {
		
		int[] nums = new int[] {1,2,3,4};
		
		int[] output = productExceptSelf(nums);
		
		for(int i: output) {
			System.out.printf("%d ", i);
		}

	}
	
	public static int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0)
            return new int[]{};
        
        int n = nums.length;
        int[] output = new int[n];
        
        int leftRunningProduct = 1;
        
        for(int i=0; i<n; i++) {
            output[i] = leftRunningProduct;
            leftRunningProduct *= nums[i];
        }
        
        int rightRunningProduct = 1;
        
        for(int i=n-1; i>=0; i--) {
            output[i] *= rightRunningProduct;
            rightRunningProduct *= nums[i];
        }
        
        return output;
    }

}
