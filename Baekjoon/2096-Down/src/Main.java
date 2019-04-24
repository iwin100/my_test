import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(token.nextToken());
		
		int[] min = new int[3];
		int[] max = new int[3];
		int[] input = new int[3];
		
		// �ʱ�ȭ
		token = new StringTokenizer(br.readLine());
		max[0] = min[0] = Integer.parseInt(token.nextToken());
		max[1] = min[1] = Integer.parseInt(token.nextToken());
		max[2] = min[2] = Integer.parseInt(token.nextToken());
		
		for (int i = 1; i<N; i++) {
			int[] temp = new int[3];

			// �� ���� �Է� �ޱ�
			token = new StringTokenizer(br.readLine());
			input[0] = Integer.parseInt(token.nextToken());
			input[1] = Integer.parseInt(token.nextToken());
			input[2] = Integer.parseInt(token.nextToken());
			
			// �ִ밪 ã��
			// �� ���� 0,1�� �ε��� �� ū �� ����
			temp[0] = input[0] + Math.max(max[0], max[1]);
			// �� ���� 0,1,2�� �ε��� �� ū �� ����
			temp[1] = input[1] + Math.max(max[0], Math.max(max[1], max[2]));
			// �� ���� 1,2�� �ε��� �� ū �� ����
			temp[2] = input[2] + Math.max(max[1], max[2]);
			max = temp.clone();
			
			// �ּҰ� ã��
			temp[0] = input[0] + Math.min(min[0], min[1]);
			temp[1] = input[1] + Math.min(min[0], Math.min(min[1], min[2]));
			temp[2] = input[2] + Math.min(min[1], min[2]);
			min = temp.clone();
		}

		Arrays.sort(max);
		Arrays.sort(min);
		System.out.println(max[2] + " " + min[0]);
	}
}
