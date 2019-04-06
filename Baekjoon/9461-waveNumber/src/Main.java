import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] memo = new long[100];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(token.nextToken());
		
		memo[0] = 1;
		memo[1] = 1;
		memo[2] = 1;
		memo[3] = 2;
		memo[4] = 2;
		
		for (int i = 5; i<100; i++) {
			memo[i] = memo[i-1] + memo[i-5];
		}
		
		for (int i = 0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(token.nextToken());
			System.out.println(memo[num-1]);
		}
	}
}
