import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long arr[];
	static Data tree[];
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		int N, M;
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		arr = new long[N];
		tree = new Data[N*4];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		//build
		build(1, 0, N-1);

		for (int i = M; i > 0; i--) {
			token = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(token.nextToken())-1;
			int right = Integer.parseInt(token.nextToken())-1;
			//query
			Data result = query(1, left, right, 0, N-1);
			System.out.println(result.getLow() + " " + result.getHigh());
		}
	}

	private static Data query(int node, int left, int right, int start, int end) {
		if (right < start || left > end) {
		    return null;
		}
		
		if (left <= start && right >= end) {
			return tree[node];
		}
		
		Data ld = query(node*2, left, right, start, (start+end)/2);
		Data rd = query(node*2+1, left, right, (start+end)/2+1, end);
		long high, low;
		
		if (ld == null) {
			return rd;
		}
		if (rd == null) {
			return ld;
		}
		
		low = ld.getLow();
		if (low > rd.getLow()) {
			low = rd.getLow();
		}
		
		high = ld.getHigh();
		if (high < rd.getHigh()){
			high = rd.getHigh();
		}
		return new Data(high, low);
	}

	private static Data build(int node, int start, int end) {
		if (start == end) {
			long iii = arr[start];
			tree[node] = new Data(arr[start], arr[end]);
			return tree[node];
		}
		
		Data left = build(node*2, start, (start+end)/2);
		Data right = build(node*2+1, (start+end)/2+1, end);
		long high, low;
		
		low = left.getLow();
		if (low > right.getLow()) {
			low = right.getLow();
		}
		
		high = left.getHigh();
		if (high < right.getHigh()){
			high = right.getHigh();
		}
		tree[node] = new Data(high, low);
		return tree[node];
	}
}

class Data {
	long high, low;
	public Data (long h, long l){
		this.high = h;
		this.low = l;
	}
	public long getLow() {
		return this.low;
	}
	public long getHigh() {
		return this.high;
	}
	public void setLow(long l) {
		this.low = l;
	}
	public void setHigh(long h) {
		this.high = h;
	}
}