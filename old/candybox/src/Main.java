import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] TREE = new int[4000000];
	static int candy;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int N = Integer.parseInt(br.readLine());
				
		for (int i = 0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			int A,B,C;
			A = Integer.parseInt(token.nextToken());
			B = Integer.parseInt(token.nextToken());
									
			if (A == 1) {
				candy = -1;
				pop(1, B, 0, 1000000-1);
				System.out.println(candy);
			} else {
				C = Integer.parseInt(token.nextToken());
				modify(1, B, C, 0, 1000000-1);
			}
		}
	}

	private static void pop(int node, int num, int start, int end) {
		if (start == end) {
			candy = start;
			TREE[node]--;
			return;
		}
		
		int leftVal = TREE[node*2];
		TREE[node]--;
		if (num <= leftVal) {
			// 왼쪽으로 보내
			pop(node*2, num, start, (start + end)/2);
		} else {
			// 오른쪽으로 보내
			// num 값 조정해서 보내
			pop(node*2 + 1, num - leftVal, (start + end)/2 + 1, end);			
		}
	}

	private static void modify(int node, int idx, int value, int start, int end) {
		if (idx < start || idx > end) {
			return;
		}
		
		TREE[node] += value;
		
		if (start == end) {
			return;
		}
		
		modify(node*2, idx, value, start, (start + end)/2);
		modify(node*2 + 1, idx, value, (start + end)/2 + 1, end);
	}
}