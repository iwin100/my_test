import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1395
public class Main {
	static int tree[];
	static int lazy[];
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
		lazy = new int[N*4];
		
		while (M-- > 0) {
			token = new StringTokenizer(br.readLine());
			int O = Integer.parseInt(token.nextToken());
			int S = Integer.parseInt(token.nextToken());
			int T = Integer.parseInt(token.nextToken());
			
			if (O == 0) {
				// 스위치 반전
				range_update(1, 0, N-1, S-1, T-1);
			} else {
				// 켜진거 카운트
				int output = get(1, 0, N-1, S-1, T-1);
				System.out.println(output);
			}
		}
	}
	
	private static void range_update(int idx, int start, int end, int left, int right) {
		update_lazy(idx, start, end);
		
		if (right < start || left > end) {
			return;
		}
		
		// 리프 노드
		if (start == end) {
			if (tree[idx] == 0) {
				tree[idx] = 1;
			} else {
				tree[idx] = 0;
			} 
			return;
		}
		
		if (start >= left && end <= right) {
			//lazy
			tree[idx] = (end-start+1) - tree[idx];
			if (lazy[idx*2] == 0) {
				lazy[idx*2] = 1;
			} else {
				lazy[idx*2] = 0;
			}
			if (lazy[idx*2+1] == 0) {
				lazy[idx*2+1] = 1;
			} else {
				lazy[idx*2+1] = 0;
			}
		} else {
			range_update(idx*2, start, (start+end)/2, left, right);
			range_update(idx*2+1, (start+end)/2+1, end, left, right);
			tree[idx] = tree[idx*2] + tree[idx*2+1];
		}
	}
	
	private static void update_lazy(int idx, int start, int end) {
		if (lazy[idx] == 1) {
			tree[idx] = (end-start+1) - tree[idx];
			
			if (start != end) {
				if (lazy[idx*2] == 0) {
					lazy[idx*2] = 1;
				} else {
					lazy[idx*2] = 0;
				}
				if (lazy[idx*2+1] == 0) {
					lazy[idx*2+1] = 1;
				} else {
					lazy[idx*2+1] = 0;
				}
			}
			lazy[idx] = 0;
		}
	}

	private static int get(int idx, int start, int end, int left, int right) {
		update_lazy(idx, start, end);
		if (right < start || left > end) {
			return 0;
		}
		
		if (right >= end && left <= start) {
			return tree[idx];
		}
		
		return get(idx*2, start, (start+end)/2, left, right) + get(idx*2+1, (start+end)/2+1, end, left, right);
	}
}