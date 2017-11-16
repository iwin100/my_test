import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3392
public class Main {
	static XPOINT input[];
	static long tree[] = new long[30000*4];
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int N = Integer.parseInt(br.readLine());
		input = new XPOINT[N*2];
		int i = 0;
		for (int j = 0; j<N; j++) {
			token = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(token.nextToken());
			int y1 = Integer.parseInt(token.nextToken());
			int x2 = Integer.parseInt(token.nextToken());
			int y2 = Integer.parseInt(token.nextToken())-1;
			input[i++] = new XPOINT(x1, y1, y2, true);
			input[i++] = new XPOINT(x2, y1, y2, false);
		}
		Arrays.sort(input, new Comparator<XPOINT>() {
			@Override
			public int compare(XPOINT o1, XPOINT o2) {
				if (o1.x > o2.x) {
					return 1;
				} else if (o1.x < o2.x) {
					return -1;
				} else {
					if (o1.y1 > o2.y1 ) {
						return 1;
					} else {
						return -1;
					}
				}
			}
		});
		
		int start = 0;
		int end = 30000;
		long sum = 0;
		int startX = 0;
		for (i = 0; i<N*2; i++) {
			int dx = input[i].x - startX;
			sum += dx * tree[1];
			
			rangeUpdate(1, input[i].y1, input[i].y2, input[i].isStart, start, end);
			startX = input[i].x;
		}
		System.out.println(sum);
	}
	private static long rangeUpdate(int idx, int left, int right, boolean isStart, int start, int end) {
		if (right < start || left > end) {
			if (start == end) {
				if (tree[idx] == 0) {
					return 0;
				} else {
					return 1;
				}
			}
			return tree[idx];
		}
		
		if (start == end) {
			if (isStart) {
				tree[idx] += 1;
				return 1;
			} else {
				tree[idx] -= 1;
				if (tree[idx] == 0) {
					return 0;
				} else {
					return 1;
				}
			}
		}

		return tree[idx] = rangeUpdate(idx*2, left, right, isStart, start, (start+end)/2) + 
				               rangeUpdate(idx*2+1, left, right, isStart, (start+end)/2+1, end);
	}

}
class XPOINT {
	int x,y1,y2;
	boolean isStart = false;
	
	public XPOINT(int x, int y1, int y2, boolean flag) {
		this.x = x;
		this.y1 = y1;
		this.y2 = y2;
		if (flag) {
			isStart = true;
		}
	}
}