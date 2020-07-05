import java.util.Arrays;
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
	static Point king;
	static Point stone;
	static final int ROW = 8;
	static final int CAL = 8;
	
    static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1}; 
    static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};
    
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.next();
		king = new Point((str1.charAt(0) - 'A') + 1, str1.charAt(1) - '0');
		String str2 = sc.next();
		stone = new Point((str2.charAt(0) - 'A') + 1, str2.charAt(1) - '0');
		int n = sc.nextInt();
		String[] move = new String [n];
		
		for(int i = 0; i < n; i++) {
			move[i] = sc.next();
		}
		for(int i = 0; i < n; i++) {
			int x = king.x;
			int y = king.y;
			switch (move[i]) {
			case "R":
				move(dx[0], dy[0]);
				break;
			case "L":
				move(dx[1], dy[1]);
				break;
			case "B":
				move(dx[2], dy[2]);
				break;
			case "T":
				move(dx[3], dy[3]);
				break;
			case "RT":
				move(dx[4], dy[4]);
				break;
			case "LT":
				move(dx[5], dy[5]);
				break;
			case "RB":
				move(dx[6], dy[6]);
				break;
			case "LB":
				move(dx[7], dy[7]);
				break;
			}
		}
		System.out.print((char)((king.x - 1) + 'A'));
		System.out.print(king.y);
		System.out.println();
		System.out.print((char)((stone.x - 1) + 'A'));
		System.out.print(stone.y);
	}
	
	static void move(int moveX, int moveY) {
		int rx = king.x + moveX;
		int ry = king.y + moveY;
		
		if(!isVaild(rx, ry))
			return;
		// 킹의 위치와 돌의 위치가 겹쳤을 경우 돌의 위치를 이동
		if(rx == stone.x && ry == stone.y) {
			int sx = stone.x + moveX;
			int sy = stone.y + moveY;
			
			if(!isVaild(sx, sy))
				return;
			stone.x = sx;
			stone.y = sy;
		}
		king.x = rx;
		king.y = ry;
	}
	
	// 맵의 범위를 벗어났는지 체크
	static boolean isVaild(int x, int y) {
		if(x <= 0 || x > ROW || y <= 0 || y > CAL)
			return false;
		return true;
		
	}
}