import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr =  new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			token = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(token.nextToken());
			int C = Integer.parseInt(token.nextToken());
			
			int input[] = new int[C+1];
			token = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= C; i++) {
				input[i] = Integer.parseInt(token.nextToken()); 
			}
			Arrays.sort(input);
			
			long total[] = new long[M+1];
			long table[][] = new long[C+1][M+1];
			
			for (int y = 1; y<=M; y++) {
				for (int x = 1; x<=C; x++) {
					 if (y < input[x]) {
					 } else if (y == input[x]) {
						 table[x][y] = 1;
						 total[y]++;
					 } else {
						 long gogo = getget(table, y - input[x], x) ;
						 table[x][y] = gogo;
						 total[y] += gogo;
					 }
				}
			}
			System.out.println(total[M]%1000000007);
		}
	}

	private static long getget(long[][] table, int rest, int x) {
		long total = 0;
		for (int i = 1; i<=x; i++) {
			total += table[i][rest];
		}
		return total;
	}
}
