import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	// https://www.acmicpc.net/problem/9251
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		char UP[];
		char DOWN[];
		
		UP = br.readLine().toCharArray();
		DOWN = br.readLine().toCharArray();
		
		int X = UP.length + 1;
		int Y = DOWN.length + 1;
		
		int table[][] = new int[X][Y];
		
		for (int y = 1; y<Y; y++) {
			for (int x = 1; x<X; x++) {
				if (UP[x-1] == DOWN[y-1]) {
					table[x][y] = table[x-1][y-1] +1;	
				} else {
					table[x][y] = Math.max(table[x-1][y], table[x][y-1]);
				}
			}
		}
		
//		for (int y = 0; y<Y; y++) {
//			for (int x = 0; x<X; x++) {
//				System.out.print(table[x][y] );
//			}
//			System.out.println();
//		}
		System.out.println(table[X-1][Y-1]);
	}
}