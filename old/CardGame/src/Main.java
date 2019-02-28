import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10835
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input1"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int N = Integer.parseInt(br.readLine());
		int left[] = new int[N];
		int right[] = new int[N];
		int table[][] = new int[N+1][N+1];
		
		
		token = new StringTokenizer(br.readLine());
		for (int i = 0; i<N; i++) {
			left[i] = Integer.parseInt(token.nextToken());
			Arrays.fill(table[i], -1);
		}
		
		token = new StringTokenizer(br.readLine());
		for (int i = 0; i<N; i++) {
			right[i] = Integer.parseInt(token.nextToken());
		}
		
		table[0][0] = 0;
		for (int y = 0; y<N; y++) {
			for (int x = 0; x<N; x++) {
				if (table[x][y] == -1) {
					break;
				}
				if (left[y] > right[x]) {
					if (table[x+1][y] < table[x][y] + right[x]) {
						table[x+1][y] = table[x][y] + right[x];
					}
					if (table[x+1][y+1] < table[x][y]) {
						table[x+1][y+1] = table[x][y];
					}
					if (table[x][y+1] < table[x][y]) {
						table[x][y+1] = table[x][y];
					}
				} else {
					if (table[x+1][y+1] < table[x][y]) {
						table[x+1][y+1] = table[x][y];
					}
					if (table[x][y+1] < table[x][y]) {
						table[x][y+1] = table[x][y];
					}
				}
			}
		}
		int bigbig = -1;
		for (int i = 0; i<=N; i++) {
			if (bigbig < table[i][N]) {
				bigbig = table[i][N];
			}
			if (bigbig < table[N][i]) {
				bigbig = table[N][i];
			}
		}
		System.out.println(bigbig);
	}

}
