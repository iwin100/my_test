import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2156

public class Main {
	static int bigbig = -1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine());
		int table[][] = new int[6][N];
		int input[] = new int[N];
		
		for(int y = 0; y<N; y++) {
			input[y] = Integer.parseInt(br.readLine());
			go001(table, input, y);
			go010(table, input, y);
			go011(table, input, y);
			go100(table, input, y);
			go101(table, input, y);
			go110(table, input, y);
		}
		System.out.println(bigbig);
	}

	private static void go110(int[][] table, int[] input, int y) {
		if (y == 0) {
			table[5][y] = 0;
		} else if (y == 1) {
			table[5][y] = input[y-1];
		} else if (y == 2) {
			table[5][y] = input[y-2] + input[y-1];
		} else {
			// 이전단계에서 제일 큰 숫자 찾기
			int big = -1;
			for (int x = 0; x< 6; x++) {
				if (x == 0 || x ==2 || x ==4) {
					continue;
				}
				if (big < table[x][y-3]) {
					big = table[x][y-3];
				}
			}
			table[5][y] = input[y-2] + input[y-1] + big;
		}
		if (bigbig < table[5][y]) {
			bigbig = table[5][y];
		}
	}

	private static void go101(int[][] table, int[] input, int y) {
		if (y == 0) {
			table[4][y] = input[y];
		} else if (y == 1) {
			table[4][y] = input[y];
		} else if (y == 2) {
			table[4][y] = input[y] + input[y-2];
		} else {
			// 이전단계에서 제일 큰 숫자 찾기
			int big = -1;
			for (int x = 0; x< 6; x++) {
				if (x == 2) {
					continue;
				}
				if (big < table[x][y-3]) {
					big = table[x][y-3];
				}
			}
			table[4][y] = input[y-2] + input[y] + big;
		}
		if (bigbig < table[4][y]) {
			bigbig = table[4][y];
		}
	}

	private static void go100(int[][] table, int[] input, int y) {
		if (y == 0) {
			table[3][y] = 0;
		} else if (y == 1) {
			table[3][y] = 0;
		} else if (y == 2) {
			table[3][y] = input[y-2];
		} else {
			// 이전단계에서 제일 큰 숫자 찾기
			int big = -1;
			for (int x = 0; x< 6; x++) {
				if (x == 2) {
					continue;
				}
				if (big < table[x][y-3]) {
					big = table[x][y-3];
				}
			}
			table[3][y] = input[y-2] + big;
		}
		if (bigbig < table[3][y]) {
			bigbig = table[3][y];
		}
	}

	private static void go011(int[][] table, int[] input, int y) {
		if (y == 0) {
			table[2][y] = input[y];
		} else if (y == 1) {
			table[2][y] = input[y-1] + input[y];
		} else if (y == 2) {
			table[2][y] = input[y-1] + input[y];
		} else {
			// 이전단계에서 제일 큰 숫자 찾기
			int big = -1;
			for (int x = 0; x< 6; x++) {
				if (big < table[x][y-3]) {
					big = table[x][y-3];
				}
			}
			table[2][y] = input[y-1] + input[y] + big;
		}
		if (bigbig < table[2][y]) {
			bigbig = table[2][y];
		}
	}

	private static void go010(int[][] table, int[] input, int y) {
		if (y == 0) {
			table[1][y] = 0;
		} else if (y == 1) {
			table[1][y] = input[y-1];
		} else if (y == 2) {
			table[1][y] = input[y-1];
		} else {
			// 이전단계에서 제일 큰 숫자 찾기
			int big = -1;
			for (int x = 0; x< 6; x++) {
				if (big < table[x][y-3]) {
					big = table[x][y-3];
				}
			}
			table[1][y] = input[y-1] + big;
		}
		if (bigbig < table[1][y]) {
			bigbig = table[1][y];
		}
	}

	private static void go001(int[][] table, int[] input, int y) {
		if (y == 0) {
			table[0][y] = input[y];
		} else if (y == 1) {
			table[0][y] = input[y];
		} else if (y == 2) {
			table[0][y] = input[y];
		} else {
			// 이전단계에서 제일 큰 숫자 찾기
			int big = -1;
			for (int x = 0; x< 6; x++) {
				if (big < table[x][y-3]) {
					big = table[x][y-3];
				}
			}
			table[0][y] = input[y] + big;
		}
		if (bigbig < table[0][y]) {
			bigbig = table[0][y];
		}
	}

}
