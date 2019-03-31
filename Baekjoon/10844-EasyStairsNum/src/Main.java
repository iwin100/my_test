import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(token.nextToken());
		
		long[][] memo = new long[N+2][10];
		
		// √ ±‚»≠
		for (int i = 1; i<=9; i++) {
			memo[1][i] = 1;
		}
		for (int i = 1; i<9; i++) {
			memo[2][i] = 2;
		}
		memo[2][9] = 1;
		
		for (int i = 3; i<=N; i++) {
			memo[i][1] = (memo[i-2][1] + memo[i-1][2]) % 1000000000;
			memo[i][9] = (memo[i-1][8]) % 1000000000;
			
			for (int j = 2; j<9; j++) {
				memo[i][j] = (memo[i-1][j-1] + memo[i-1][j+1]) % 1000000000;	
			}
		}
		
		long output = 0;
		for (int i=1; i<10; i++) {
			output = (output + memo[N][i]) % 1000000000;
		}
		System.out.println(output);
	}

}
