import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
		Arrays.fill(table, 'x');
		
		table[Weight[0]] = 'o';
		int start = Weight[0];
		int end = Weight[0];
		
		for (int idx = 1; idx<N; idx++) {
			build(Weight[idx], start, (end += Weight[idx]) - Weight[idx]);
			
//			for (int j = 0; j<total+1; j++) {
//				System.out.print(table[j] + " ");
//			}
//			System.out.println();
		}
		
		int CASE = Integer.parseInt(br.readLine());
		token = new StringTokenizer(br.readLine());
		StringBuilder output = new StringBuilder();
		while (CASE-- >0) {
			int CASE00 = Integer.parseInt(token.nextToken());
			if (table[CASE00] == 'o') {
				output.append("Y");
			} else {
				output.append("N");
			}
			if (CASE - 1 != -1) {
                output.append(" ");
            }
		}
		System.out.println(output);
	}

	private static void build(int value, int start, int end) {
		ArrayList<Integer> data = new ArrayList<Integer>();
		for (int i=start; i<=end; i++) {
			if (table[i] == 'o') {
				data.add(i);
			}
		}
		
		table[value] = 'o';
		for (int i=0; i<data.size(); i++) {
			int value2 = data.get(i);
			table[value + value2] = 'o';
			if (value > value2) {
				table[value - value2] = 'o';		
			} else {
				table[value2 - value] = 'o';
			}
		
		}
	}
}