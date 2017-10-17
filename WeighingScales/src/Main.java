import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2629
public class Main {
	
	static int Weight[];
	static char table[];
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine());
		Weight = new int[N];
		
		int total = 0;		
		StringTokenizer token = new StringTokenizer(br.readLine());
		for (int i = 0; i< N; i++) {
			Weight[i] = Integer.parseInt(token.nextToken());
			total += Weight[i];
		}
		
		table = new char[total + 1];
		
		total = 0;
		table[Weight[0]] = 'o';
		
		for (int idx = 1; idx<N; idx++) {
			build(idx);
		}
	}

	private static void build(int idx) {
		int cur = Weight[idx];
		table[cur] = 'o';
		
		
	}
}