import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static LinkedList<Person> CANDI;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int C = Integer.parseInt(br.readLine());
		
		//tc
		for (int i=0; i<C; i++) {
			int total=0;
			int N = Integer.parseInt(br.readLine());
			CANDI = new LinkedList<Person>();
			for (int j=0; j<N; j++) {
				int p,q;
				StringTokenizer token = new StringTokenizer(br.readLine());
				p = Integer.parseInt(token.nextToken());
				q = Integer.parseInt(token.nextToken());
				Person per = new Person(p, q);
				
				if (total == 0) {
					CANDI.add(per);
				} else {
					for (int k = 0; k<CANDI.size(); k++) {
						Person t1 = CANDI.get(k);
						int rst = compare(t1, per);
						if (rst == 0) {
							// 도전자가 pq 다 크다
							CANDI.remove(k);
							//여기가 마지막이면 입력
							if (k == CANDI.size()) {
								CANDI.add(per);
								break;
							}
						} else if (rst == 1) {
							// 도전자 p 가 작고 q가 크다. 여기에 입력
							CANDI.add(k,  per);
							break;
						} else if (rst == 2) {
							// 도전자 p 가 크다.
							//여기가 마지막이면 입력
							if (k+1 == CANDI.size()) {
								CANDI.add(per);
								break;
							}
							//다음으로 이동
							continue;
						} else {
							// 도전자가 pq 다 작다.
						}
					}
				}
				total += CANDI.size();
			}
			System.out.println(total);
		}
	}

	private static int compare(Person t1, Person per) {
		if ((per.p > t1.p) && (per.q > t1.q)) {
			return 0;
		} else if ((per.p < t1.p) && (per.q > t1.q)) {
			return 1;
		} else if (per.p > t1.p) {
			return 2;
		} else {
			return 3;
		}
	}
}

class Person {
	int p,q;
	Person(int p, int q){
		this.p = p;
		this.q = q;
	}
}