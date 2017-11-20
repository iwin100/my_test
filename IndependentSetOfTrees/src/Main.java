import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2213
public class Main {
	static Node input[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int N = Integer.parseInt(br.readLine());
		input = new Node[N];
		
		token = new StringTokenizer(br.readLine());
		for (int i = 0; i<N; i++) {
			int w = Integer.parseInt(token.nextToken());
			input[i] = new Node(i, w);
		}
		
		for (int i = 1; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(token.nextToken()) -1;
			int t = Integer.parseInt(token.nextToken()) -1;

			input[s].connect.add(input[t]);
			input[t].connect.add(input[s]);
		}
		
		dfs(input[0]);
		if (input[0].caseT > input[0].caseF) {
			Collections.sort(input[0].caseTList, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					if (o1 > o2) {
						return 1;
					} else {
						return -1;
					}
				}
			});
			System.out.println(input[0].caseT);
			for (int j = 0; j<input[0].caseTList.size(); j++) {
				System.out.print(input[0].caseTList.get(j));
				if (j+1 <input[0].caseTList.size()) {
					System.out.print(" ");
				}
			}
		} else {
			Collections.sort(input[0].caseFList, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					if (o1 > o2) {
						return 1;
					} else {
						return -1;
					}
				}
			});
			System.out.println(input[0].caseF);
			for (int j = 0; j<input[0].caseFList.size(); j++) {
				System.out.print(input[0].caseFList.get(j));
				if (j+1 <input[0].caseFList.size()) {
					System.out.print(" ");
				}
			}
		}
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
				node.caseT += child.caseF;
				node.caseTList.addAll(child.caseFList);
				if (child.caseT > child.caseF) {
					node.caseF += child.caseT;
					node.caseFList.addAll(child.caseTList);
				} else {
					node.caseF += child.caseF;
					node.caseFList.addAll(child.caseFList);
				}
			}
		}
		node.caseCheck = true;
	}
}

class Node {
	ArrayList<Node> connect;
	ArrayList<Integer> caseTList;
	ArrayList<Integer> caseFList;
	int idx;
	int weight;
	boolean check = false;
	boolean caseCheck = false;
	int caseT = 1;
	int caseF = 0;
	
	Node(int idx, int weight){
		connect = new ArrayList<Node>();
		caseTList = new ArrayList<Integer>();
		caseFList = new ArrayList<Integer>();
		this.idx = idx;
		this.weight = weight;
		this.caseT = weight;
		caseTList.add(idx+1);
	}
}