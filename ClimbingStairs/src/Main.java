import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2579
public class Main {
	static int input[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine());
		input = new int[N];
		for (int i = N-1; i>=0; i--) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		int result = Math.max(goNext1(0, N, 1), goNext2(0, N));
		System.out.println(result + input[0]);
	}
	
	private static int goNext2(int idx, int end) {
		idx += 2;
		if (idx >= end) {
			return 0;
		}
		int rst = input[idx];
		rst += Math.max(goNext1(idx, end, 1), goNext2(idx, end));
		return rst;
	}
	
	private static int goNext1(int idx, int end, int cnt) {
		idx += 1;
		cnt ++;
		if (idx >= end) {
			return 0;
		}
		if (cnt == 2 ) {
			int rst = input[idx];
			rst += goNext2(idx, end);
			return rst;
		}
		
		int rst = input[idx];
		rst += Math.max(goNext1(idx, end, cnt), goNext2(idx, end));
		return rst;
	}
}
