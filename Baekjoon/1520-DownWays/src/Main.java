import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map, memo, visit;
	static int M,N;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(token.nextToken());
		N = Integer.parseInt(token.nextToken());
		
		map = new int[M+1][N+1];
		memo = new int[M+1][N+1];
		
		// 입력 받기
		for (int i = 0; i<M; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				memo[i][j] = -1;
			}
		}

		int value = getPathCnt(-1, M-1, N-1);
		System.out.println(value);
	}

	private static int getPathCnt(int value, int x, int y) {
		if (value >= map[x][y]) {
			return 0;
		}
		
		if (memo[x][y] != -1) {
			return memo[x][y];
		}
		
		if (x == 0 && y== 0) {
			return 1;
		}
			
		int output = 0;
		//동
		if (y+1 < N) {
			output += getPathCnt(map[x][y], x, y+1);
		}
		//서
		if (y-1 >= 0) {
			output += getPathCnt(map[x][y], x, y-1);			
		}
		//남
		if (x+1 < M) {
			output += getPathCnt(map[x][y], x+1, y);
		}
		//북
		if (x-1 >= 0) {
			output += getPathCnt(map[x][y], x-1, y);
		}
		
		return memo[x][y] = output;
	}
}