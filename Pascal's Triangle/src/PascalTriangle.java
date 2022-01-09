import java.util.Arrays;

public class PascalTriangle {
	
	public static int[][] pascalTriangle(int n) {
		int[][] pascal = new int[n + 1][];
		pascal[0] = new int[] {1};
		for(int i = 1; i < n + 1; i++) {
			pascal[i] = new int[pascal[i - 1].length + 1];
			for(int j = 0; j < pascal[i].length; j++) {
				if(j != 0 && j != pascal[i].length - 1) {
					pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
				} else {
					pascal[i][j] = 1;
				}
			}
		}
		return pascal;
	}
	
	public static void main(String[] args) {
		int[][] triangle = pascalTriangle(8);
		for(int i = 0; i < triangle.length; i++) {
			System.out.println(Arrays.toString(triangle[i]));
		}
	}
}