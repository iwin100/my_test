import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2533
public class Main {
	static Node input[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int N = Integer.parseInt(br.readLine());
		input = new Node[N];
		for (int i = 1; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(token.nextToken()) -1;
			int t = Integer.parseInt(token.nextToken()) -1;
			if (input[s] == null) {
				input[s] = new Node(s);
			}
			if (input[t] == null) {
				input[t] = new Node(t);
			}
			input[s].connect.add(input[t]);
			input[t].connect.add(input[s]);
		}
		
		dfs(input[0]);
		System.out.println(Math.min(input[0].caseT, input[0].caseF));
	}
	
	private static void dfs(Node node) {
		node.check = true;
		for (int i = 0; i< node.connect.size(); i++) {
			Node child = node.connect.get(i);
			if (child.check == false) {
				dfs(child);
			}
		}
		
		for (int i = 0; i< node.connect.size(); i++) {
			Node child = node.connect.get(i);
			if (child.caseCheck == false) {
				continue;
			} else {
				node.caseT += Math.min(child.caseF, child.caseT);
				node.caseF += child.caseT;
			}
		}
		node.caseCheck = true;
	}
}

class Node {
	ArrayList<Node> connect;
	int idx;
	boolean check = false;
	boolean caseCheck = false;
	int caseT = 1;
	int caseF = 0;
	
	Node(int idx){
		connect = new ArrayList<Node>();
		this.idx = idx;
	}
}