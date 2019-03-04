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
			// 중간값
			mid = min + ((max - min) / 2);
			if (binSearch( mid ) >= M) {
				// 목표치 보다 많을 경우
				// 결과 값 갱신
				if (output < mid) {
					output = mid;
				}
				// 최소값 끌어 올리기
				min = mid+1;
			} else {
				// 목표치 보다 적을 경우
				//
				max = mid-1;
			}
		}
		System.out.println(output);
	}
	private static long binSearch(int mid) {
		// 합계가 Integer 단위를 넘어간다...
		long sum = 0;
		for (int i = 0; i < N; i++) {
			if (input[i] > mid) {
				sum += input[i] - mid;
			}
		}
		return sum;
	}
}