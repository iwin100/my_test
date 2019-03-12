import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static Node[] tree;
	static Integer[] root = new Integer[10001];
	static Integer[] xMin = new Integer[10001];
	static Integer[] xMax = new Integer[10001];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		token = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(token.nextToken());
		
		tree = new Node[N];
		
		for (int i = 0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			int nodeNum = Integer.parseInt(token.nextToken());
			int left = Integer.parseInt(token.nextToken());
			int right = Integer.parseInt(token.nextToken());
			
			Node temp = new Node(nodeNum, left, right);
			if (left != -1) {
				root[left] = -1;
			}
			if (right != -1) {
				root[right] = -1;
			}
			tree[nodeNum -1] = temp;
		}
		
		// root Ã£±â
		int rootNum = -1;
		for (int i=1; i<=N; i++) {
			if (root[i] == null) {
				rootNum = i;
				break;
			}
		}
		
		
		Stack<Integer> stack = new Stack<>();
		tree[rootNum - 1].y = 1;
		int totalX = 1;
		stack.push(rootNum);
		
		while(!stack.isEmpty()) {
			int parentNum = stack.pop();
			Node temp = tree[parentNum-1];
			int left = temp.left;
			int right = temp.right;
			int x = temp.x;
			int y = temp.y;
			
			if ( (left != -1) && (tree[left-1].x == -1) ) {
				tree[left-1].y = y+1;
				stack.push(parentNum);
				stack.push(left);
				continue;
			} else {
				tree[parentNum-1].x = totalX;
				if (xMin[y] == null) {
					xMin[y] = totalX;
				} else if (totalX < xMin[y]) {
					xMin[y] = totalX;
				}
				
				if (xMax[y] == null) {
					xMax[y] = totalX;
				} else if (totalX > xMax[y]) {
					xMax[y]= totalX;
				}
				totalX++;
			}
			
			if (right != -1) {
				tree[right-1].y = y+1;
				stack.push(right);
			}
		}
		int width = -1;
		int depth = -1;
		for (int i = 1; i <= 10000; i++) {
			if (xMax[i] == null) {
				break;
			}
			int temp_width = xMax[i] - xMin[i] + 1;
			if (temp_width > width) {
				width = temp_width;
				depth = i;
			}
		}
		System.out.println(depth + " " + width);
	}
}

class Node {
	int num, parent, left, right, y, x;
	Node(int num, int left, int right) {
		this.num = num;
		this.left = left;
		this.right = right;
		this.x = -1;
		this.y = -1;
	}
}