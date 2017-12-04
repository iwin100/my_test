import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2336
public class Main {
	static int[] tree1;
	static int[] tree2;
	static int[][] input;
	static int output = 1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token0;
		StringTokenizer token1;
		StringTokenizer token2;
		
		int N = Integer.parseInt(br.readLine());
		tree1 = new int[N*4];
		tree2 = new int[N*4];
		input = new int[2][N];
		int ex[] = new int[N];
		
		token0 = new StringTokenizer(br.readLine());
		token1 = new StringTokenizer(br.readLine());
		token2 = new StringTokenizer(br.readLine());
		
		for (int i = 0; i<N; i++) {
			int student0 = Integer.parseInt(token0.nextToken());
			ex[i] = student0;
			
			int student1 = Integer.parseInt(token1.nextToken());
			input[0][student1-1] = i+1;
			
			int student2 = Integer.parseInt(token2.nextToken());
			input[1][student2-1] = i+1;
		}
		
		int start = 0;
		int end = N-1;
		
		update1(1, input[0][ex[0]-1]-1, start, end);
		update2(1, input[1][ex[0]-1]-1, start, end);
		
		for (int i = 1; i<N; i++) {
			int target = ex[i];
			int a = query1(1, 0, input[0][target-1]-2, start, end);
			if (a==0) {
				output++;
			} else {
				int b = query2(1, 0, input[1][target-1]-2, start, end);
				if (b>=1) {
				} else {
					output++;
				}
			}
			
			update1(1, input[0][target-1]-1, start, end);
			update2(1, input[1][target-1]-1, start, end);
		}
		System.out.println(output);
	}



	private static int query1(int idx, int left, int right, int start, int end) {
		if (left > end || right < start) {
			return 0;
		}
		
		if (start == end) {
			return tree1[idx];
		}
		
		return query1(idx*2, left, right, start, (start+end)/2) + query1(idx*2+1, left, right, (start+end)/2+1, end);
	}

	private static int query2(int idx, int left, int right, int start, int end) {
		if (left > end || right < start) {
			return 0;
		}
		
		if (start == end) {
			return tree2[idx];
		}
		
		return query2(idx*2, left, right, start, (start+end)/2) + query2(idx*2+1, left, right, (start+end)/2+1, end);
	}

	private static void update1(int idx, int target, int start, int end) {
		if (target < start || target > end) {
			return;
		}
		
		if (start == end) {
			tree1[idx] = 1;
			return;
		}
		
		update1(idx*2, target, start, (start+end)/2);
		update1(idx*2+1, target, (start+end)/2+1, end);
		tree1[idx] = tree1[idx*2] + tree1[idx*2+1]; 
	}
	
	private static void update2(int idx, int target, int start, int end) {
		if (target < start || target > end) {
			return;
		}
		
		if (start == end) {
			 tree2[idx] = 1;
			 return;
		}
		
		 update2(idx*2, target, start, (start+end)/2);
		 update2(idx*2+1, target, (start+end)/2+1, end);
		 tree2[idx] = tree2[idx*2] + tree2[idx*2+1];
	}
}
