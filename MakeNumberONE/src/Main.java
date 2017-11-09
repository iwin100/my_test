import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1463
public class Main {
	static int table[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int N = Integer.parseInt(br.readLine());
		
		table = new int[N+1];
		for (int i = N; i > 0; i--) {
			di3(i);
			di2(i);
			m1(i);
		}
		System.out.println(table[1]);
	}
	private static void m1(int idx1) {
		if (idx1 - 1 > 0) {
			int idx2 = idx1 - 1;
			if (table[idx2] == 0) {
				table[idx2] = table[idx1] + 1; 
			} else {
				table[idx2] = Math.min(table[idx2], table[idx1] + 1);
			}
		}
	}
	private static void di3(int idx1) {
		if (idx1%3 == 0) {
			int idx2 = idx1/3;
			if (table[idx2] == 0) {
				table[idx2] = table[idx1] + 1; 
			} else {
				table[idx2] = Math.min(table[idx2], table[idx1] + 1);
			}
		}
	}
	private static void di2(int idx1) {
		if (idx1%2 == 0) {
			int idx2 = idx1/2;
			if (table[idx2] == 0) {
				table[idx2] = table[idx1] + 1; 
			} else {
				table[idx2] = Math.min(table[idx2], table[idx1] + 1);
			}
		}
	}
}
