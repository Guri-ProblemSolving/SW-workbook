import java.util.Scanner;

public class Main { 
	// 톱니 바퀴 수
	static final int OBJECT_CNT = 4;
	// 톱니 바퀴의 돌기 수
	static final int CNT = 8;
	// 톱니 바퀴들의 상태
	static int[][] map;
    
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		map = new int[OBJECT_CNT + 1][CNT];
		for(int i = 1; i <= OBJECT_CNT; i++) {
			String str = sc.next();
			for(int j = 0; j < CNT; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		int k = sc.nextInt();
		while(k-- > 0) {
			int t = sc.nextInt();
			int dir = sc.nextInt();

			rightTurn(t, t + 1, dir * -1);
			leftTurn(t - 1, t,  dir * -1);
			if(dir == -1)
				leftShift(t);
			if(dir == 1)
				rightShift(t);
		}
		
		int ans = 0;
		if(map[1][0] == 1)
			ans += 1;
		if(map[2][0] == 1)
			ans += 2;
		if(map[3][0] == 1)
			ans += 4;
		if(map[4][0] == 1)
			ans += 8;
		System.out.println(ans);
	}

	// 기준 톱니바퀴의 오른쪽에 있는 톱니바퀴들의 회전여부 판별
	static void rightTurn(int left, int right, int dir) {
		if(right > OBJECT_CNT)
			return;
		if(map[left][2] == map[right][6])
			return;
		
			rightTurn(right, right + 1, dir * -1);
			if(dir == -1)
				leftShift(right);
			if(dir == 1)
				rightShift(right);
	}

	// 기준 톱니바퀴의 왼쪽에 있는 톱니바퀴들의 회전여부 판별
	static void leftTurn(int left, int right, int dir) {
		if(left < 0)
			return;
		if(map[left][2] == map[right][6])
			return;
			
			leftTurn(left - 1, left, dir * -1);
			if(dir == -1)
				leftShift(left);
			if(dir == 1)
				rightShift(left);
	}
	
	// 시계방향으로 톱니바퀴 회전
	static void rightShift(int t) {
		int next = map[t][0];
		for(int i = 1; i < CNT; i++) {
			int temp = map[t][i];
			map[t][i] = next;
			next = temp;
		}
		map[t][0] = next;
	}
	
	// 반시계방향으로 톱니바퀴 회전
	static void leftShift(int t) {
		int next = map[t][CNT-1];
		for(int i = CNT-2; i >= 0; i--) {
			int temp = map[t][i];
			map[t][i] = next;
			next = temp;
		}
		map[t][CNT-1] = next;
	}
}