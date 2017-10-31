import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10167
public class Main {
	static Mine mine[];
	static int table[][];
	static long big;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int N = Integer.parseInt(br.readLine());
		mine = new Mine[N];		
		int idx = 0;
		
		for (int i = 0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());
			int z = Integer.parseInt(token.nextToken());
			
			mine[i] = new Mine(x,y,z);
			if (z > 0) {
				idx++;
			}
		}
		
		Arrays.sort(mine, new Comparator<Mine>() {
			@Override
			public int compare(Mine o1, Mine o2) {
				if (o1.x > o2.x) {
					return 1;
				} else if (o1.x < o2.x) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		table = new int[idx+1][idx+1];
		for (int i = 0; i<N; i++) {
			if (mine[i].x > 0) {
				table[0][i] = mine[i-1].x;
				table[i][0] = mine[i-1].x;
				table[i][i] = mine[i-1].x;
			}
		}
		
		
		for (int i = 2; i <= idx; i++) {
			
		}
		
		System.out.println(mine[0]);
	}
}

class Mine {
	int x,y,value;
	public Mine(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}