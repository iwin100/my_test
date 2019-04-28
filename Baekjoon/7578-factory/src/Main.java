import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int[] A, B, tree;
	static Map<Integer, Integer> map = new HashMap<>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(token.nextToken());
		
		A = new int[N];
		tree = new int[N*4];
		
		token = new StringTokenizer(br.readLine());
		for (int i = 0; i<N; i++) {
			A[i] = Integer.parseInt(token.nextToken());
		}

		token = new StringTokenizer(br.readLine());
		for (int i = 0; i<N; i++) {
			int machine = Integer.parseInt(token.nextToken());
			int index = i;
			map.put(machine, index);
		}
		
		long output = 0;
		for (int i = 0; i<N; i++) {
			int index = map.get(A[i]);
			output += getSum(1, index, N-1, 0, N-1);
			update(1, index, 0, N-1);
		}
		
		System.out.println(output);
	}
	
	private static void update(int node, int idx, int min, int max) {
		if ( idx > max || idx < min) {
			return;
		}

		tree[node]++;
		
		if (min != max) {
			update(node*2, idx, min, (min+max)/2);
			update(node*2+1, idx, (min+max)/2+1, max);
		}
		return;
	}
	
	private static long getSum(int node, int left, int right, int min, int max) {
		if ( left > max || right < min) {
			return 0;
		}
		
		if (left <= min && right >= max) {
			return tree[node];
		}
		
		long leftSum = getSum(node*2, left, right, min, (min+max)/2);
		long rightSum = getSum(node*2+1, left, right, (min+max)/2+1, max);
		return leftSum + rightSum;
	}
}
