import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1149
public class Main {
	static int table[][];
	static int input[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int N = Integer.parseInt(br.readLine());
		table = new int[N][3];
		input = new int[N][3];
		
		for (int i = 0; i<N; i++) {
			token =  new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(token.nextToken());
			input[i][1] = Integer.parseInt(token.nextToken());
			input[i][2] = Integer.parseInt(token.nextToken());
		}
		
		// init
		table[0][0] = input[0][0];
		table[0][1] = input[0][1];
		table[0][2] = input[0][2];
		
		System.out.println(build(N));
	}
	private static int build(int end) {
		int small = 0;
		for (int i = 1; i<end; i++) {
			//R
			table[i][0] = Math.min(table[i-1][1], table[i-1][2]) + input[i][0];
			small = table[i][0];
			//G
			table[i][1] = Math.min(table[i-1][0], table[i-1][2]) + input[i][1];
			small = Math.min(small, table[i][1]);
			//B
			table[i][2] = Math.min(table[i-1][0], table[i-1][1]) + input[i][2];
			small = Math.min(small, table[i][2]);
		}
		return small;
	}
}
