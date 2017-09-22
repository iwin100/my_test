import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//세그먼트 트리 2
//https://www.acmicpc.net/problem/11505

public class Main {
	static long arr[];
	static long tree[];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		int N, M, K;
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());
		
		arr = new long[N];
		tree = new long[N*4];
		
		for (int i = 0; i < N; i ++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		build(1, 0, N-1);
		
		for (int i = M+K; i > 0; i--) {
			token = new StringTokenizer(br.readLine());
			int ii = Integer.parseInt(token.nextToken());
			
			if (ii == 1) {
				//update
				int idx = Integer.parseInt(token.nextToken()) -1;
				long old_val = arr[idx];
				long new_val = Long.parseLong(token.nextToken());				
				arr[idx] = new_val;
				update(1, idx, old_val, new_val, 0, N-1);
			} else if (ii == 2) {
				//mul
				int l = Integer.parseInt(token.nextToken()) -1;
				int r = Integer.parseInt(token.nextToken()) -1;
				System.out.println(mul(1, l, r, 0, N-1) % 1000000007);
			}
		}
	}
	
	private static long mul(int node, int l, int r, int start, int end) {
		if (r < start || l > end) {
			return 1;
		}
		
		if (l <= start && r >= end) {
			return tree[node];
		} else {
			return (mul(node*2, l, r, start, (start+end)/2) * mul(node*2+1, l, r, (start+end)/2+1, end)) % 1000000007;
		}
		
	}

	private static void update(int node, int idx, long old_val, long new_val, int start, int end) {
		if (idx < start || idx > end) {
			return;
		}
		
		if (start == end) {
			tree[node] = new_val;
			return;
		}
		
//		tree[node] = (tree[node]/old_val) * new_val;
//		if (idx >= start && idx <= end) {
			update(node*2, idx, old_val, new_val, start, (start+end)/2);
			update(node*2+1, idx, old_val, new_val, (start+end)/2+1, end);
			tree[node] = (tree[node*2] * tree[node*2+1]) % 1000000007;
			return;
//		}
	}

	static long build(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return tree[node];
		}
		tree[node] = (build(node*2, start, (start+end)/2) * build(node*2+1, (start+end)/2+1, end)) % 1000000007;
		return tree[node];
	}
}
