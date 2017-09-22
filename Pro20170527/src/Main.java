import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static GUL gul[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int C = Integer.parseInt(br.readLine());
		for (int i = 0; i<C; i++) {
			int N, M;
			StringTokenizer token = new StringTokenizer(br.readLine());
			N = Integer.parseInt(token.nextToken());
			M = Integer.parseInt(token.nextToken());
			
			// �� �����
			gul = new GUL[N+1];
			for (int j = 1; j<=N; j++) {
				gul[j] = new GUL(j);
			}
			// �� �����
			for (int j = 1; j<=M; j++) {
				token = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(token.nextToken());
				int d = Integer.parseInt(token.nextToken());
				int w = Integer.parseInt(token.nextToken());
				gul[s].gil.add(new GIL(d,w));
				gul[d].gil.add(new GIL(s,w));
			}
			
			// ã��
			int output = 0;
			for (int j = 1; j<=N; j++) {
				bigbig = -1;
				gogo(1, j, 0, 0);
				output += bigbig;
			}
			System.out.println(output);
		}
	}
	
	static int bigbig;
	private static void gogo(int idx, int dest, int beforeW, int total) {
		// �����±� ���� ���� ū��? ũ�� �� ������Ʈ
		if ((idx == dest) && (bigbig < total)) {
			bigbig = total;
		}
		
		//�����ִ� ���� ����.
		if (gul[idx].gil.size() < 1) {
			return;
		} else {
			// ���� �� �� �ִ� �� ��ȸ
			for (int i = 0; i < gul[idx].gil.size(); i++) {
				// ������ �Դ��ź��� �䰡?
				if (gul[idx].gil.get(i).w > beforeW) {
					gogo(gul[idx].gil.get(i).d, dest, gul[idx].gil.get(i).w, total + gul[idx].gil.get(i).w);
				}
			}
			return;
		}
	}
}

class GUL {
	int idx;
	List<GIL> gil = new ArrayList<GIL>();
	GUL(int idx) {
		this.idx = idx;
	}
}

class GIL {
	int d,w;
	GIL(int d, int w){
		this.d = d;
		this.w = w;
	}
}