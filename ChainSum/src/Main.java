import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1912
public class Main {
	static int input[];
	static int bigbig;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr =  new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine());
		input = new int[N];
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		for (int i = 0; i< N; i++) {
			input[i] = Integer.parseInt(token.nextToken());
		}
		bigbig = input[0];
		
		build(1, N, input[0]);
		
		System.out.println(bigbig);
	}

	private static void build(int idx, int end, int sum) {
		if (idx >= end) {
			return;
		}
		
		if (sum + input[idx] > input[idx]) {
			sum += input[idx];
		} else {
			sum = input[idx];
		}
		bigbig = Math.max(sum, bigbig);
		build(idx+1, end, sum);
	}
}