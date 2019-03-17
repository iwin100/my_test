import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] tree = new int [1000000 * 4];
	private static int output;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(token.nextToken());
		for (int i = 0; i<N; i++) {
			int A,B,C;
			token = new StringTokenizer(br.readLine());
			A = Integer.parseInt(token.nextToken());
			B = Integer.parseInt(token.nextToken());
			
			if (A == 1) {
				// ��� -�ϳ� ����.
				output = -1;
				pop(1, B, 0, 1000000-1);
				System.out.println(output);
			} else {
				// Ʈ�� ���� ����
				C = Integer.parseInt(token.nextToken());
				update(1, B, C, 0, 1000000-1);
			}
		}
	}
	
	private static void pop(int node, int num, int start, int end) {
		tree[node]--;
		
		if (start == end) {
			output = start;
			return;
		} else {
			if (tree[node*2] >= num) {
				//���� �ڽ� �߿� ã�� �༮�� �ִ�
				pop(node*2, num, start, (end+start)/2);
			} else {
				//������ �ڽ� �߿� ã�� �༮�� �ִ�
				//���� �ڽ� ���� ����.
				pop(node*2+1, num - tree[node*2], (start+end)/2+1, end);
			}
		}
	}

	private static void update(int node, int idx, int mount, int start, int end) {
		if (idx < start || idx > end) {
			return;
		}
		
		tree[node] += mount;
		
		if (start == end) {
			return;
		} else {
			update(node*2, idx, mount, start, (end+start)/2);
			update(node*2+1, idx, mount, (start+end)/2+1, end);
		}
	}

}
