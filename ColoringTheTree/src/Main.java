import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1693
public class Main {
	static ArrayList<Node> input = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i<N; i++) {
			input.add(new Node(i));
		}
		for (int i = 1; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(token.nextToken())-1;
			int dst = Integer.parseInt(token.nextToken())-1;
			input.get(src).connect.add(input.get(dst));
			input.get(dst).connect.add(input.get(src));
		}
		
		dfs(0);
		int c_table[] = input.get(0).table;
		System.out.println(Math.min(Math.min(c_table[0], c_table[1]),
				                    Math.min(c_table[2], c_table[3])
				                   )
				          );
	}
	
	private static void dfs(int idx) {
		input.get(idx).visit = true;
		input.get(idx).parent = true;
		for (int i = 0; i< input.get(idx).connect.size(); i++) {
			if (input.get(idx).connect.get(i).visit == true) {
				continue;
			}
			dfs(input.get(idx).connect.get(i).idx);
		}
		input.get(idx).parent = false;
		
		for (int i = 0; i< input.get(idx).connect.size(); i++) {
			Node child = input.get(idx).connect.get(i);
			if (child.parent == true) {
				continue;
			}
			
			int c_table[] = child.table;
			input.get(idx).table[0] += Math.min(c_table[1], 
					                            Math.min(c_table[2], c_table[3])); 
			input.get(idx).table[1] += Math.min(c_table[0],
					                            Math.min(c_table[2], c_table[3]));
			input.get(idx).table[2] += Math.min(c_table[0],
					                            Math.min(c_table[1], c_table[3]));
			input.get(idx).table[3] += Math.min(c_table[0],
					                            Math.min(c_table[1], c_table[2]));
		}
	}
}

class Node {
	ArrayList<Node> connect = new ArrayList<>();
	int table[] = new int[4];
	int idx;
	boolean visit = false;
	boolean parent = false;
	
	Node(int idx){
		this.idx = idx;
		this.table[0] = 1;
		this.table[1] = 2;
		this.table[2] = 3;
		this.table[3] = 4;
	}
}