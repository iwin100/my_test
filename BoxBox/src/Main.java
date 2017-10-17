import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.naming.InterruptedNamingException;

public class Main {

	static int maxmax = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		// input
		int NUM = Integer.parseInt(br.readLine());
		int input[] = new int[NUM];
		int output[] = new int[NUM];
		String in[] = br.readLine().split(" ");
		for (int i = 0; i< NUM; i++) {
			input[i] = Integer.parseInt(in[i]);
		}
		
		// init
		int idx = 0;
		output[0] = input[0];
		
		// build
		for (int i =1; i<NUM; i++) {
			if (input[i] > output[idx]) {
				output[++idx] = input[i];
			} else {
				output[findLowIdx(output, idx, input[i]) + 1] = input[i];
			}
			if (maxmax < idx+1) {
				maxmax = idx+1;
			}
			
//			System.out.println(i);
//			for (int j = 0; j<NUM; j++) {
//				System.out.print(output[j] + " ");
//			}
//			System.out.println();
		}
		
		//print
		System.out.println(maxmax);
	}

	private static int findLowIdx(int[] output, int idx, int val) {
		for (; idx>=0; idx--) {
			if (output[idx] >= val) {
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
