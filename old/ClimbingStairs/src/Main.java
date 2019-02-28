import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2579
public class Main {
	static int table[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine());
		table = new int[3][N+2];
		for (int i = N-1; i>=0; i--) {
			table[0][i] = Integer.parseInt(br.readLine());
		}
		
		table[1][0] = table[0][0];
		int result = 0;
		for (int i = 0; i<N; i++) {
			result = Math.max(Math.max(table[1][i], table[2][i]), result);
			if (table[1][i] != 0) {
				goN1(i);
				goN2(1, i);
			}
			if (table[2][i] != 0) {
				goN2(2, i);
			}
		}
		System.out.println(result);
	}
	
	private static void goN2(int cnt, int idx) {
		table[1][idx+2] = Math.max(table[1][idx+2], table[cnt][idx] + table[0][idx+2]);
	}

	private static void goN1(int idx) {
		table[2][idx+1] = Math.max(table[2][idx+1], table[1][idx] + table[0][idx+1]);
	}
}