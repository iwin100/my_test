import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2837
public class Main {
	static long output = 0;
	static ArrayList<Node> input = new ArrayList<>();
	static int MAX;
	static int FIN;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(token.nextToken());
		int E = Integer.parseInt(token.nextToken());
		MAX = Integer.parseInt(token.nextToken());
		FIN = N-1;
		
		for (int i = 0; i<N; i++) {
			input.add(new Node(i));
		}
		
		for (int i = 0; i<E; i++) {
			token = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(token.nextToken())-1;
			int dst = Integer.parseInt(token.nextToken())-1;
			char mark = '0';
			if (token.hasMoreTokens()) {
				mark = token.nextToken().toCharArray()[0];
			}
			Road temp = new Road(dst, mark);
			input.get(src).connect.add(temp);
		}
		
		Stack<Character> container = new Stack<>();
		dfs(0, 0, container, '0');
		System.out.println(output);
	}

	private static void dfs(int idx, int cnt, Stack<Character> container, char mark1) {
		if (cnt > MAX) {
			return;
		}
		
		if (idx == FIN) {
			output++;
			return;
		}
		
		if (input.get(idx).parent) {
			return;
		}
		
		if (mark1 == '0') {
		} else if (Character.isUpperCase(mark1)) {
			container.push(mark1);
		} else {
			container.pop();
		}
		
		
		
		input.get(idx).parent = true;
		
		for (int i = 0; i < input.get(idx).connect.size(); i++) {
			Road temp = input.get(idx).connect.get(i);
			if (container.isEmpty()) {
				if (temp.mark == '0') {
					dfs(temp.dst, cnt+1, (Stack<Character>) container.clone(), '0');
				} else if (Character.isUpperCase(temp.mark)) {
					dfs(temp.dst, cnt+1, (Stack<Character>) container.clone(), temp.mark);
				}
			} else {
				if (temp.mark == '0') {
					dfs(temp.dst, cnt+1, (Stack<Character>) container.clone(), '0');
				} else if (Character.isUpperCase(temp.mark)) {
					dfs(temp.dst, cnt+1, (Stack<Character>) container.clone(), temp.mark);
				} else {
					char tempChar = container.pop();
					if (temp.mark == Character.toLowerCase(tempChar)) {
						container.push(tempChar);
						dfs(temp.dst, cnt+1, (Stack<Character>) container.clone(), temp.mark);	
					} else {
						container.push(tempChar);
					}
				}
			}
		}
		
		input.get(idx).parent = false;
	}
}

class Road {
	int dst;
	char mark;
	Road(int dst, char mark) {
		this.dst = dst;
		this.mark = mark;
	}
}

class Node {
	int idx;
	boolean parent = false;
	ArrayList<Road> connect = new ArrayList<>();
	
	Node(int idx){
		this.idx = idx;
	}
}