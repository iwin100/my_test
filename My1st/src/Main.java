import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 세그먼트 트리 1
// https://www.acmicpc.net/problem/2042
public class Main {
	static long input[];
	static long tree[];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		// input
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int N, M, K;
		N = Integer.valueOf(tokenizer.nextToken());
		M = Integer.valueOf(tokenizer.nextToken());
		K = Integer.valueOf(tokenizer.nextToken());
		
		input = new long[N];
		tree = new long[N*4];
		
		for (int i = 0; i < N; i++) {
			input[i] = Long.valueOf(br.readLine());
		}

		// build
		build(1, 0, N-1);
		
		for (int i = M + K; i > 0; i--) {
			tokenizer = new StringTokenizer(br.readLine());
			int type = Integer.valueOf(tokenizer.nextToken());
			int idx, l, r;
			long diff;
			
			if (type == 1) {
				idx = Integer.valueOf(tokenizer.nextToken()) - 1;
				diff = Long.valueOf(tokenizer.nextToken()) - input[idx];
				input[idx] += diff;
				// update
				update(1, idx, diff, 0, N-1);
			} else if (type == 2) {
				l = Integer.valueOf(tokenizer.nextToken()) - 1;
				r = Integer.valueOf(tokenizer.nextToken()) -1 ;
				// sum
				System.out.println( sum(1, l, r, 0, N-1) );
			}
		}
	}
	
	public static long build(int node, int start, int end) {
		if (start == end) {
			tree[node] = input[start];
			return tree[node];
		} else {
			tree[node] = build(node*2, start, (start+end)/2) + build(node*2+1, (start+end)/2+1, end);
			return tree[node];
		}
	}
	
	public static void update(int node, int idx, long diff, int start, int end) {
		if (idx < start || idx > end) {
			return;
		}
		tree[node] += diff;
		if (start != end) {
			update(node*2, idx, diff, start, (start+end)/2);
			update(node*2+1, idx, diff, (start+end)/2+1, end);
		}
	}
	
	public static long sum(int node, int l, int r, int start, int end) {
		if (l > end || r < start) {
			return 0;
		}
		
		if (l <= start &&  r >= end) {
			return tree[node];
		}
		return sum(node*2, l, r, start, (start+end)/2) + sum(node*2+1, l, r, (start+end)/2+1, end);
	}
}