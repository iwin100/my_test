import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

// 2017-10-28 pro
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		ArrayList<PAY> pay;
		int output = 0;
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-- >0) {
			pay = new ArrayList<>();
			output = 0;
			token = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(token.nextToken());
			int K = Integer.parseInt(token.nextToken());
			
			token = new StringTokenizer(br.readLine());
			for (int i = 0; i<N;i++) {
				pay.add(new PAY(Integer.parseInt(token.nextToken())));
			}
			
			for (int i = 0; i<K; i++) {
				token = new StringTokenizer(br.readLine());
				int src = Integer.parseInt(token.nextToken()) -1;
				int dest = Integer.parseInt(token.nextToken()) -1;
				if (pay.get(src).pay > pay.get(dest).pay) {
					pay.get(dest).path.add(pay.get(src));
				} else if (pay.get(src).pay < pay.get(dest).pay) {
					pay.get(src).path.add(pay.get(dest));
				}
			}
						
			pay.sort(new Comparator<PAY>() {
				@Override
				public int compare(PAY o1, PAY o2) {
					if (o2.pay > o1.pay) {
						return 1;
					} else if (o2.pay < o1.pay) {
						return -1;
					} else {
						return 0;
					}
				}
			});
			
			for (int i = 0; i<N; i++) {
				int size = pay.get(i).path.size();
				int total = 0;
				if (size != 0) {
					for (int j = 0; j<size; j++) {
						total += pay.get(i).path.get(j).total +1;
					}
				}
				pay.get(i).total = total;
				output = (total + output)%1000000007;
			}
			
			System.out.println(output);
		}
	}
}

class PAY {
	int pay = 0;
	int total = 0;
	ArrayList<PAY> path;
	public PAY(int p) {
		this.pay = p;
		this.path = new ArrayList<>();
	}
}