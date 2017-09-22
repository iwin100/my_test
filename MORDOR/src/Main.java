import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int input[];
	static Info Tree[];
	static Info output;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		StringTokenizer token;
		int C = Integer.parseInt(br.readLine());

		while (C-- > 0) {
			int N, Q;
			token = new StringTokenizer(br.readLine());
			N = Integer.parseInt(token.nextToken());
			Q = Integer.parseInt(token.nextToken());
			
			input = new int[N];
			Tree = new Info[N*4];
			token = new StringTokenizer(br.readLine());
			for (int i = 0; i< N; i++) {
				input[i] = Integer.parseInt(token.nextToken());
			}
			
			// build tree
			build(1, 0, N-1);
			StringBuilder sb = new StringBuilder();
			
			while (Q-- > 0) {
				token = new StringTokenizer(br.readLine());
				int l,r;
				l = Integer.parseInt(token.nextToken());
				r = Integer.parseInt(token.nextToken());
				
				// get data
				output = new Info(-1, 20000);
				getOutput(1, l, r, 0, N-1);
				
				sb.append((output.b - output.s) + "\n");
			}
			System.out.print(sb.toString());
		}
	}

	private static void getOutput(int node, int l, int r, int start, int end) {
		if (r < start || l > end) {
			return;
		}
		
		if (l <= start && r >= end) {
			if (Tree[node].b > output.b) {
				output.b = Tree[node].b;
			}
			if (Tree[node].s < output.s) {
				output.s = Tree[node].s;
			}
		} else {
			getOutput(node*2, l, r, start, (start+end)/2);
			getOutput(node*2+1, l, r, (start+end)/2+1, end);
		}
	}

	private static Info build(int node, int start, int end) {
		if (start == end) {
			return Tree[node] = new Info(input[start], input[start]);
		}
		
		Info left = build(node*2, start, (start+end)/2);
		Info right = build(node*2+1, (start+end)/2+1, end);
		
		int b, s;
		if (left.b > right.b) {
			b = left.b;
		} else {
			b = right.b;
		}
		
		if (left.s < right.s) {
			s = left.s;
		} else {
			s = right.s;
		}
		return Tree[node] = new Info(b, s);
	}
}

class Info {
	int b, s;
	Info(int b, int s) {
		this.b = b;
		this.s = s;
	}
}