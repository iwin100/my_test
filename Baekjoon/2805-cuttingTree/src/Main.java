import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static Integer[] input;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		token = new StringTokenizer(br.readLine());
		input = new Integer[N];
		for (int i = 0; i<N; i++) {
			input[i] = Integer.parseInt(token.nextToken());
		}
		
		int min = 0;
		int max = 1000000001;
		int mid = 0;
		int output = 0;
		while (min <= max) {
			// �߰���
			mid = min + ((max - min) / 2);
			if (binSearch( mid ) >= M) {
				// ��ǥġ ���� ���� ���
				// ��� �� ����
				if (output < mid) {
					output = mid;
				}
				// �ּҰ� ���� �ø���
				min = mid+1;
			} else {
				// ��ǥġ ���� ���� ���
				//
				max = mid-1;
			}
		}
		System.out.println(output);
	}
	private static long binSearch(int mid) {
		// �հ谡 Integer ������ �Ѿ��...
		long sum = 0;
		for (int i = 0; i < N; i++) {
			if (input[i] > mid) {
				sum += input[i] - mid;
			}
		}
		return sum;
	}
}