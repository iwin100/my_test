import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1937
public class Main {
	static int N;
	static int maxmax;
	static int input[][];
	static int table[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input2"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br =  new BufferedReader(isr);
		StringTokenizer token;
		
		N = Integer.parseInt(br.readLine());
		input = new int[N][N];
		table = new int[N][N];
		
		for (int i=0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				input[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		maxmax = 1;
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (table[y][x] == 0) {
					table[y][x] = build(y,x);
				}
			}
		}
		System.out.println(maxmax);
		
	}

	int depth;
	private static int build(int y, int x) {
		if (table[y][x] > 0) {
			return table[y][x];
		}
		
		int max = 1;
		
		//up
		if (y-1 >= 0) {
			if (input[y-1][x] > input[y][x]) {
				max = Math.max(build(y-1,x) + 1, max);
			}
		}
		//down
		if (y+1 < N) {
			if (input[y+1][x] > input[y][x]) {
				max = Math.max(build(y+1,x) + 1, max);
			}
		}
		
		//left
		if (x-1 >= 0) {
			if (input[y][x-1] > input[y][x]) {
				max = Math.max(build(y,x-1) + 1, max);
			}			
		}
		
		//right
		if (x+1 < N) {
			if (input[y][x+1] > input[y][x]) {
				max = Math.max(build(y,x+1) + 1, max);
			}
		}
		
		table[y][x] = max;
		maxmax = Math.max(max, maxmax);
		return max;
	}	
}
