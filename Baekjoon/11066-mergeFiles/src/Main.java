import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(token.nextToken());

		for (int i = 0; i<TC; i++) {
			token = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(token.nextToken());

			int input[] = new int[N+1];
			int map[][] = new int[N+1][N+1];
			int ref[][] = new int[N+1][N+1];
			
			token = new StringTokenizer(br.readLine());
			for (int j = 1; j<=N; j++) {
				int val = Integer.parseInt(token.nextToken());
				input[j] = val;

				// 자기 자신 채우기
				map[j][j] = val;
			}
			
			// 2개짜리 채우기
			for (int j = 1; j<N; j++) {
				ref[j][j+1] = map[j][j+1] = map[j][j] + map[j+1][j+1];
			}
			
			// ref 채우기
			for (int a = 1; a <=N; a++) {
				for (int b = a+2; b<=N; b++) {
					ref[a][b] = ref[a][b-1] + input[b];
				}
			}
			
			// 3개 이상 짜리 채우기
			// 남서풍 방향으로 채워간다.
			for (int delta = 2; delta<=N; delta++) {
				for (int y = 1; y<=N; y++) {
					int limit = y + delta;
					if (limit > N) {
						break;
					}
					int min_value = Integer.MAX_VALUE;
					
					for (int diff = 0; diff <= limit; diff++) {
						if (diff+y+1 >limit) {
							break;
						}
						int total = map[y][y+diff] + ref[y][y+diff] + map[y+diff+1][limit] + ref[y+diff+1][limit];
						if (total < min_value) {
							min_value = total;
						}
					}
					map[y][limit] = min_value;
				}
			}
			System.out.println(map[1][N]);
			
		}
	}
}
