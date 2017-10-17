import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11568

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("input2"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int Length = Integer.parseInt(br.readLine());
		int input[] = new int[Length];
		int output[] = new int[Length];
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		for (int i = 0; i<Length; i++) {
			input[i] = Integer.parseInt(token.nextToken());
		}
		
		int idx = 0;
		output[0] = input[0];
		
		for (int i = 1; i<Length; i++) {
			if (input[i] > output[idx]) {
				output[++idx] = input[i];
			} else {
				int temp_idx = getLower(output, idx-1, input[i]);
				output[temp_idx + 1] = input[i];
			}
			
//			for (int j = 0; j< Length; j++) {
//				System.out.print(output[j] + " " );
//			}
//			System.out.println();
			
		}
		
		System.out.println(idx+1);
	}

	private static int getLower(int[] output, int idx, int input_value) {
		for (;idx >= 0; idx--) {
			if (output[idx] >= input_value) {
				if (idx == 0) {
					return -1;
				}
			} else {
				break;
			}
		}
		return idx;
	}
}
