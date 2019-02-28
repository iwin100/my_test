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
	static boolean table[];
	static boolean Match[][];
	
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
		
		table = new boolean[total + 1];
		Match = new boolean[501][total + 1];
		Arrays.fill(table, false);
		
		table[Weight[0]] = true;
		int end = Weight[0];
		
		for (int idx = 1; idx<N; idx++) {
			build(Weight[idx], 0, (end += Weight[idx]) - Weight[idx]);
			
//			for (int j = 0; j<total+1; j++) {
//				System.out.print(table[j] + " ");
//			}
//			System.out.println();
		}
		
		int CASE = Integer.parseInt(br.readLine());
		token = new StringTokenizer(br.readLine());
		StringBuilder output = new StringBuilder();
		while (CASE-- > 0) {
			int idx  = Integer.parseInt(token.nextToken());
			
			if (idx > total) {
				output.append("N");
			} else if (table[idx] == true) {
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
			if (table[i] == true) {
				if (Match[value][i] != true) {
					data.add(i);
				}
			}
		}
		
		table[value] = true;
		for (int i=0; i<data.size(); i++) {
			int value2 = data.get(i);
			table[value + value2] = true;
			if (value > value2) {
				table[value - value2] = true;		
			} else {
				table[value2 - value] = true;
			}
			
			Match[value][value2] = true;
		}
	}
}