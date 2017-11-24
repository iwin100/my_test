import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1572
public class Main {
	static int tree[];
	static int input[];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(token.nextToken());
		int K = Integer.parseInt(token.nextToken());
		tree = new int[65536*4];
		input = new int[N];
		
		for (int i = 0; i< N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		int start = 0;
		int end = 65536;
		
		int j;
		for (j = 0; j<K; j++) {
			insert(1, start, end, input[j]);
		}
		long output =0;
		output += query(1, start, end, (K+1)/2);
		
		for (int i = 0; i < N-K; i++) {
			insert(1, start, end, input[j]);
			delete(1, start, end, input[j-K]);
			output += query(1, start, end, (K+1)/2);
			j++;
		}
		System.out.println(output);
	}


	private static long query(int idx, int start, int end, int key) {
		if (tree[idx] == 0) return 0;
		
		if (key <= 0) return 0;
		
		if (key > tree[idx]) return 0;
		
		if (start == end) {
			return end;
		}
		
		int leftVal = tree[idx*2];
		long left = query(idx*2, start, (start+end)/2, key);
		long right = query(idx*2+1, (start+end)/2+1, end, key - leftVal);
		
		return Math.max(left,  right);
	}


	private static void delete(int idx, int start, int end, int value) {
		if (value > end || value < start) return;
		
		tree[idx]--;
		if (end == start) return;
		
		delete(idx*2, start, (start+end)/2, value);
		delete(idx*2+1, (start+end)/2+1, end, value);
	}

	private static void insert(int idx, int start, int end, int value) {
		if (value > end || value < start) return;
		
		tree[idx]++;
		if (end == start) return;
		
		insert(idx*2, start, (start+end)/2, value);
		insert(idx*2+1, (start+end)/2+1, end, value);
	}
}