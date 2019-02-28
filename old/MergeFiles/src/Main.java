import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11066
public class Main {
	static int table[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-->0) {
			int K = Integer.parseInt(br.readLine());
			table = new int[K+1][K+1];
			token = new StringTokenizer(br.readLine());
			for (int i = 1; i<=K; i++) {
				table[0][i] = Integer.parseInt(token.nextToken());
				table[i][0] = table[0][i];
			}
			
			for (int y = 1; y < K; y++) {
				gogo(0,y+1, y,0);
			}
		}
	}
	
	
	private static void gogo(int x1, int x2, int y1, int y2) {
		int min = 0;
		if (table[x2-1][y1] == 0 && table[x2][y1+1] == 0) {
			min = 0;
		} else {
			min = 0;
			for (int i=y1+1; i<=x2; i++ ) {
				
			}
		}
		table[x2][y1] = min + sumsum(y1, x2);
	}
	private static int sumsum(int y1, int x2) {
		int output = 0;
		for (int i = y1; i<=x2; i++) {
			output += table[0][i];
		}
		return output;
	}
}
