import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static ArrayList<Node> tree = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		token =  new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		Integer[] parent = new Integer[N+1];
		Integer[] depth = new Integer[N+1];
		
		for (int i = 0; i<=N; i++) {
			tree.add(new Node(i));	
		}
		for (int i = 1; i<N; i++) {
			tree.add(new Node(i));
			token =  new StringTokenizer(br.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
					
			tree.get(a).conn.add(b);
			tree.get(b).conn.add(a);
		}
		
		// 깊이 구하기
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		depth[1] = 0;
		parent[1] = 0;
		
		while(!q.isEmpty()) {
			int target = q.poll();
			int parentDepth = depth[target];
			int size = tree.get(target).conn.size();
			for (int i = 0; i< size; i++) {
				int childNum = tree.get(target).conn.get(i);		
				if (childNum != parent[target]) {
					parent[childNum] = target; 
					depth[childNum] = parentDepth + 1;
					q.add(childNum);
				}
			}
		}
		
		token =  new StringTokenizer(br.readLine());
		M = Integer.parseInt(token.nextToken());
		for (int i = 0; i<M; i++) {
			token =  new StringTokenizer(br.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			
			// 깊이 조절
			while(depth[a] != depth[b]){
				if (depth[a] > depth[b]) {
					a = parent[a];
				} else {
					b = parent[b];
				}
			}
			
			// 공통 조상 찾기
			while(a != 0) {
				if (a == b) {
					System.out.println(a);
					break;
				} else {
					a = parent[a];
					b = parent[b];
				}
			}
		}
	}

}

class Node {
	ArrayList<Integer> conn = new ArrayList<>();
	int num;
	Node(int num){
		this.num = num;
	}
}