import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2965
public class Main {
	static int result;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(token.nextToken());
		int B = Integer.parseInt(token.nextToken());
		int C = Integer.parseInt(token.nextToken());
		
		result = 0;
		junpToRight(A, B, C, 0);
		junpToLeft(A, B, C, 0);
		System.out.println(result);
	}

	private static void junpToRight(int A, int B, int C, int depth) {
		result = Math.max(result,  depth);
		A = B+1;
		if (C == A) {
			return;
		} else {
			junpToRight(B, A, C, depth+1);
			junpToLeft(B, A, C, depth+1);
		}
	}

	private static void junpToLeft(int A, int B, int C, int depth) {
		result = Math.max(result,  depth);
		C = B - 1;
		if (C == A) {
			return;
		} else {
			junpToRight(A, C, B, depth+1);
			junpToLeft(A, C, B, depth+1);
		}
	}
}