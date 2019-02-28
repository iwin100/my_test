import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2336
public class Main {
	static int[] tree;
	static int[][] input;
	static int output = 1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine());
		input = new int[3][N];

		tree = new int[N*4];
		Arrays.fill(tree, Integer.MAX_VALUE);
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		for (int i = 0; i<N; i++) {
			input[2][i] = Integer.parseInt(token.nextToken());
		}
		
		int student;
		token = new StringTokenizer(br.readLine());
		for (int i = 0; i<N; i++) {
			student = Integer.parseInt(token.nextToken());
			input[0][student-1] = i;
		}
		
		token = new StringTokenizer(br.readLine());
		for (int i = 0; i<N; i++) {
			student = Integer.parseInt(token.nextToken());
			input[1][student-1] = i;
		}
		
		int start = 0;
		int end = N-1;
		update(1, input[0][input[2][0]-1]-1, input[1][input[2][0]-1]-1, start, end);
		
		for (int i = 1; i<N; i++) {
			int target = input[2][i]-1;
			update(1, input[0][target]-1, input[1][target]-1, start, end);
			
			long candidate = query(1, 0, input[0][target]-1, start, end);
			if (candidate > input[1][target]) {
				output++;
			}
		}
		System.out.println(output);
	}

	static long query(int idx, int left, int right, int start, int end) {
		if (left > end || right < start) {
			return Integer.MAX_VALUE;
		}
		
		if (start == end) {
			return tree[idx];
		}
		
		return Math.min(query(idx*2, left, right, start, (start+end)/2), query(idx*2+1, left, right, (start+end)/2+1, end));
	}
	
	static int update(int idx, int targetIDX, int value, int start, int end) {
		if (targetIDX < start || targetIDX > end) {
			return Integer.MAX_VALUE;
		}
		
		if (start == end) {
			return tree[idx] = value;
		}
		
		return tree[idx] = Math.min(update(idx*2, targetIDX, value, start, (start+end)/2), update(idx*2+1, targetIDX, value, (start+end)/2+1, end)); 
	}
}