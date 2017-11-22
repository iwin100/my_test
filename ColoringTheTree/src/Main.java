import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1693
public class Main {
	static ArrayList<Node> input = new ArrayList<>();
	static boolean childColor[] = new boolean[100001];
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
		System.out.println(input.get(0).sum);
	}
	
	private static void dfs(int idx) {
		input.get(idx).visit = true;
		for (int i = 0; i< input.get(idx).connect.size(); i++) {
			if (input.get(idx).connect.get(i).visit == true) {
				continue;
			}
			dfs(input.get(idx).connect.get(i).idx);
		}
		
		// get child color
		Arrays.fill(childColor, false);
		for (int i = 0; i< input.get(idx).connect.size(); i++) {
			if (input.get(idx).connect.get(i).color == -1) {
				continue;
			}
			int color = input.get(idx).connect.get(i).color;
			childColor[color] = true;
			input.get(idx).sum += input.get(idx).connect.get(i).sum;
		}
		
		for (int i = 1; i<=100000; i++) {
			if (childColor[i] != true) {
				input.get(idx).color = i;
				input.get(idx).sum += i;
				break;
			}
		}
	}
}

class Node {
	ArrayList<Node> connect = new ArrayList<>();
	int idx;
	int color = -1;
	long sum = 0;
	boolean visit = false;
	
	Node(int idx){
		this.idx = idx;
	}
}