import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 좌표 클래스
class Point {
	int x;
	int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
}

public class Main {
	static Point hong;
	// 남(0), 서(1), 북(2), 동(3)
	static int dir = 0;
	static List<Point> path = new ArrayList<Point>();
	static int rowMin = 0;
	static int rowMax = 0;
	static int calMin = 0;
	static int calMax = 0;
	
    static int[] dx = {1, 0, -1, 0}; 
    static int[] dy = {0, -1, 0, 1};
    
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String str = sc.next();
		char[] move = str.toCharArray();
		hong = new Point(0,0);
		path.add(new Point(0,0));
		
		for(int i = 0; i < n; i++) {
			switch (move[i]) {
			case 'F':
				move();
				break;
			case 'L':
				turn(-1);
				break;
			case 'R':
				turn(1);
				break;
			}
		}
		int row = Math.abs(rowMax - rowMin) + 1;
		int cal = Math.abs(calMax - calMin) + 1;
		char[][] map = new char[row][cal];
		
		for(Point p : path) {
			int rx = p.x + (-1 * rowMin);
			int ry = p.y + (-1 * calMin);
			map[rx][ry] = '.';
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < cal; j++) {
				if(map[i][j] != '.')
					map[i][j] = '#';
			}
		}
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < cal; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	// 명령이 'F'일 경우 바라보고 있는 방향으로 이동
	static void move() {
		int rx = hong.x + dx[dir];
		int ry = hong.y + dy[dir];
		hong.x = rx;
		hong.y = ry;
		
		path.add(new Point(rx,ry));
		rowMax = Math.max(rx, rowMax);
		calMax = Math.max(ry, calMax);
		rowMin = Math.min(rx, rowMin);
		calMin = Math.min(ry, calMin);
		
	}
	
	// 명령이 'L'이나 'R'일 경우 바라보는 방향 회전
	static void turn(int d) {
		int rd = dir + d;
		
		if(rd == -1)
			rd = 3;
		if(rd == 4)
			rd = 0;
		dir = rd;
	}
}