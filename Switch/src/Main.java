import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1395
public class Main {
	static int tree[];
	static int info[];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		token = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(token.nextToken());
		int M = Integer.parseInt(token.nextToken());
		
		tree = new int[N*4];
		info = new int[N+1];
		
		while (M-- > 0) {
			token = new StringTokenizer(br.readLine());
			int O = Integer.parseInt(token.nextToken());
			int S = Integer.parseInt(token.nextToken());
			int T = Integer.parseInt(token.nextToken());
			
			if (O == 0) {
				for (int target = S; target <= T; target++) {
					// 스위치 반전
					update(1, 0, N, target);
					if (info[target] == 0) {
						info[target] = 1;
					} else {
						info[target] = 0;
					}
				}
			} else {
				// 켜진거 카운트
				int output = get(1, 0, N, S, T);
				System.out.println(output);
			}
		}
	}
	
	private static int get(int idx, int start, int end, int left, int right) {
		if (right < start || left > end) {
			return 0;
		}
		
		if (right >= end && left <= start) {
			return tree[idx];
		}
		
		return get(idx*2, start, (start+end)/2, left, right) + get(idx*2+1, (start+end)/2+1, end, left, right);
	}
	
	private static void update(int idx, int start, int end, int target) {
		if (target < start || target > end) {
			return;
		}
		
		if (info[target] == 0) {
			tree[idx]++;
		} else {
			tree[idx]--;
		}
		
		if (start == end) {
			return;
		}
		
		update(idx*2, start, (start+end)/2, target);
		update(idx*2+1, (start+end)/2+1, end, target);
	}
}