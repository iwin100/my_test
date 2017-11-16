import java.awt.image.BufferedImage;
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
		for (int i = 0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(token.nextToken());
			int t = Integer.parseInt(token.nextToken());
			input[s].connect.add(input[t]);
			input[t].connect.add(input[s]);
		}
		
	}
}

class Node {
	ArrayList<Node> connect = new ArrayList<Node>();
}