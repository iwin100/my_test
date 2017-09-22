import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		Wall input[];
		
		int C = Integer.parseInt(br.readLine());
		for (int i = 0; i < C; i++) {
			// input
			int N = Integer.parseInt(br.readLine());
			input = new Wall[N];
			for (int j = 0; j < N; j++) {
				StringTokenizer token = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(token.nextToken());
				int y = Integer.parseInt(token.nextToken());
				int rad = Integer.parseInt(token.nextToken());
				input[j] = new Wall(x, y, rad);
			}
			
			// sort
			Arrays.sort(input);
			
			// build tree
			
			for (int j = N-1; j > 0; j--) {
				for (int k = j-1; k >= 0; k-- ) {
					//포함 하는가????
					if (compareWall( input[k], input[j]) > 0 ) {
						//포함
						input[k].child.add(input[j]);
						break;
					}
				}
			}
			
			// get depth
			depSum = 0;
			for (int j = 0; j < N; j++) {
				int rst = getBigDep(input[j]);
				if (rst > depSum) {
					depSum = rst;
				}
			}
			System.out.println(depSum);
		}
	}
	static int depSum;
	private static int getBigDep(Wall inputWall) {
		int subTreeNum = inputWall.child.size();
		if (subTreeNum == 0) {
			return 0;
		}
		
		int sub[] = new int[subTreeNum];
		
		for (int j = 0; j < subTreeNum ; j ++) {
			dep = 0;
			depth(1, inputWall.child.get(j));
			sub[j] = dep;
		}
		Arrays.sort(sub);
		
		// get sub tree
		if (sub.length < 2) {
			return sub[0];
		} else {
			return sub[subTreeNum-1] + sub[subTreeNum - 2];
		}
	}

	static int dep;
	private static void depth(int d, Wall wall) {
		if (wall.child.size() > 0 ) {
			for (int i = 0; i < wall.child.size(); i++) {
				depth(d+1, wall.child.get(i)); 
			}
		} else {
			if (d > dep) {
				dep = d;
			}
			return;
		}
	}

	private static int compareWall(Wall wall1, Wall wall2) {
		// 원 중심 간 거리
		int i = (wall2.x - wall1.x) * (wall2.x - wall1.x) + (wall2.y - wall1.y) * (wall2.y - wall1.y);
		int j = (wall2.rad - wall1.rad) * (wall2.rad - wall1.rad);
		if (i < j) {
			return 1; // 포함
		} else {
			return 0; // 포함 안함
		}
	}
}

class Wall implements Comparable<Wall> {
	int x, y, rad;
	List<Wall> child = new ArrayList<Wall>();
	
	public Wall(int x, int y, int rad) {
		this.x = x;
		this.y = y;
		this.rad = rad;
	}

	@Override
	public int compareTo(Wall o) {
		// TODO Auto-generated method stub
		if (this.rad > o.rad) {
			return -1;
		} else if (this.rad < o.rad) {
			return 1;
		} else {
			return 0;
		}
	}
}