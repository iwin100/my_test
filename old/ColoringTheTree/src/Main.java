import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1693
public class Main {
	static ArrayList<Node> input = new ArrayList<>();
	static int MAX_SIZE = 15;
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
		int min = c_table[0];
		for (int i = 1; i<MAX_SIZE; i++) {
			min = Math.min(min, c_table[i]);
		}
		System.out.println(min);
	}
	
	private static void dfs(int idx) {
		input.get(idx).visit = true;
		input.get(idx).parent = true;
		for (int i = 1; i<= MAX_SIZE; i++) {
			input.get(idx).table[i-1] = i;
		}
		
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
			for (int j = 0; j<MAX_SIZE; j++) {
				input.get(idx).table[j] += getMin(j, c_table);
			}
		}
	}

	private static int getMin(int exclude, int[] c_table) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i<MAX_SIZE; i++) {
			if (i == exclude) {
				continue;
			}
			min = Math.min(min, c_table[i]);
		}
		return min;
	}
}

class Node {
	ArrayList<Node> connect = new ArrayList<>();
	int table[] = new int[20];
	int idx;
	boolean visit = false;
	boolean parent = false;
	
	Node(int idx){
		this.idx = idx;
	}
}