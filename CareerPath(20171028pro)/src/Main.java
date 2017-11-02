import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2017-10-28 pro
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-- >0) {
			token = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(token.nextToken());
			int K = Integer.parseInt(token.nextToken());
			
		}
	}
}
