import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10167
public class Main {
	static Mine mine[];
	static int map[];
	static ArrayList<Mine> mapList;
	static long big;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer token;
		
		int N = Integer.parseInt(br.readLine());
		mine = new Mine[N];		
		
		for (int i = 0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());
			int z = Integer.parseInt(token.nextToken());
			
			mine[i] = new Mine(x,y,z);
		}
		
		// x축 기준으로 정렬
		Arrays.sort(mine, new Comparator<Mine>() {
			@Override
			public int compare(Mine o1, Mine o2) {
				if (o1.x > o2.x) {
					return 1;
				} else if (o1.x < o2.x) {
					return -1;
				} else {
					if (o1.y > o2.y) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		});
		
		for (int i = 0; i< N; i++) {
			if (i+1<N && mine[i].x == mine[i+1].x) {
				continue;
			}
			
			mapList = new ArrayList<>();
			for (int j = 0; j <= i; j++) {
				insert(mine[j]);
			}
			getBig();
		}
		
		System.out.println(big);
	}

	private static void insert(Mine mine) {
		int size = mapList.size();
		if (size == 0) {
			mapList.add(mine);
			return;
		}
		int i;
		for (i = 0; i < size; i++) {
			if (mapList.get(i).y == mine.y) {
				mapList.remove(i);
				mapList.add(i, mine);
				return;
			} else if (mapList.get(i).y < mine.y) {
				mapList.add(i, mine);
				return;
			} else {	
			}
		}
		mapList.add(mine);
		return;
	}

	private static void getBig() {
		int size = mapList.size();
		if (size == 1) {
			big = mapList.get(0).value;
			return;
		} else {
			long bigbig = mapList.get(0).value;
			for (int i = 1; i<size; i++) {
				if (bigbig + mapList.get(i).value > mapList.get(i).value) {
					bigbig += mapList.get(i).value;
				} else {
					bigbig = mapList.get(i).value;
				}
				big = Math.max(bigbig, big);
			}	
		}
	}
}

class Mine {
	int x,y,value;
	public Mine(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}