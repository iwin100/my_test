import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static Integer[] depth;
	static Integer[] parent;
	static Node[] tree;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		depth = new Integer[N+1];
		parent = new Integer[N+1];
		tree = new Node[N+1];
				
		for (int i = 1; i<=N; i++) {
			tree[i] = new Node(i);
		}
		
		for (int i = 1; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			int c = Integer.parseInt(token.nextToken());
			
			tree[a].conn.add(b);
			tree[a].val.add(c);
			
			tree[b].conn.add(a);
			tree[b].val.add(c);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		depth[1] = 1;
		parent[1] = -1;
		queue.add(1);
		
		// 깊이 측정
		while (!queue.isEmpty()){
			int parentNum = queue.poll();
			Node parentNode = tree[parentNum];
			for (int i = 0; i<parentNode.conn.size(); i++) {
				int child = parentNode.conn.get(i);
				if (child == parent[parentNum]){
					tree[parentNum].value = parentNode.val.get(i);
					continue;
				}
				queue.add(child);
				depth[child] = depth[parentNum] + 1;
				parent[child] = parentNum;
			}
		}
		
		token = new StringTokenizer(br.readLine());
		M = Integer.parseInt(token.nextToken());
		
		for (int i = 0; i<M; i++) {
			token = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			
			int output = 0;
			//같은 depth로 맞추기
			while(depth[a] != depth[b]) {
				if (depth[a] > depth[b]) {
					output += tree[a].value;
					a = parent[a];
				} else {
					output += tree[b].value;
					b = parent[b];					
				}
			}
			
			//공통 조상 찾기
			while(a != b) {
				output += tree[a].value;
				a = parent[a];
				
				output += tree[b].value;
				b = parent[b];
			}
			System.out.println(output);
		}
	}

}

class Node {
	LinkedList<Integer> conn = new LinkedList<>();
	LinkedList<Integer> val = new LinkedList<>();
	int num = -1;
	int value = 0;
	Node(int num) {
		this.num = num;
	}
}