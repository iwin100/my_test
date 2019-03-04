import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static Row rows[];
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int min = 1;
		int max = 2147483647;
		int mid;
		int A,B,C;
		
		N = Integer.parseInt(br.readLine());
		rows = new Row[N];
		
		// �Է°� �ޱ�
		for (int i = 0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			A = Integer.parseInt(token.nextToken());
			C = Integer.parseInt(token.nextToken());
			B = Integer.parseInt(token.nextToken());
			rows[i] = new Row(A, C, B);
		}
		
		int flag = -1;
		while (min <= max) {
			if (min == max) {
				break;
			}
			// �߰��� ���ϱ�
			mid = (max - min) / 2 + min;
			
			// 1 ~ mid ���� ���� ���� �� ���ϱ�
			int value = binSearch(mid);
			
			if (value % 2 == 1) {
				// Ȧ�� �߰�
				flag = 1;
				// Ȧ�� �̸� mid ���� ���ʿ� �ִ�.
				max = mid;
			} else {
				// ¦�� �̸� mid ���� +1 �����ʿ� �ִ�.
				min = mid + 1;
			}
		}
		
		if (flag > 0) {
			int result = min;
			int count = binSearch(result) -binSearch(result-1);  
			System.out.println(result + " " + count);
		} else {
			System.out.println("NOTHING");
		}
	}

	private static int binSearch(int mid) {
		int sum = 0;
		for (int i = 0; i< N; i++) {
			if (rows[i].A <= mid) {
				sum += (Integer.min(rows[i].C, mid) - rows[i].A) / rows[i].B + 1;
			}
		}
		return sum;
	}
}
 class Row {
	 int A,B,C;
	 public Row(int A, int C, int B) {
		 this.A = A;
		 this.C = C;
		 this.B = B;
	 }
 }