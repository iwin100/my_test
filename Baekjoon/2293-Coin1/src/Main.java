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
		
		int N, K;
		N = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());
		
		int[] input = new int[N];
		int[] memo = new int[K+1];
		
		for (int i = 0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			input[i] = Integer.parseInt(token.nextToken());
		}
		
		memo[0] = 1;
		for (int i = input[0]; i <= K; i++) {
			if (i % input[0] == 0) {
				memo[i] = 1;
			}
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = input[i]; j <= K; j++) {
				memo[j] += memo[j - input[i]];
			}
		}
		System.out.println( memo[K] );
	}

}
