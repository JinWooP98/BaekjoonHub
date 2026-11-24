
import java.util.*;
import java.io.*;


public class Main {
	
	static int R;
	static int C;
	static int M;
	
	static int[][] map;
	
	static class Shark implements Comparable<Shark>{
		int x; // x좌표
		int y; // y좌표
		int s; // 스피드
		int d; // 방향
		int z; // 무게
		int num;
		int time;
		
		public Shark(int x, int y, int s, int d, int z, int time, int num) {
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
			this.time = time;
			this.num = num;
		}

		@Override
		public int compareTo(Shark o) {
			if(this.time == o.time) return this.z - o.z;
			return this.time - o.time;
		}
	}
	
	static PriorityQueue<Shark> pq;
	static int[] sizes;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine().trim());
	
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];
		sizes = new int[M+1];
		pq = new PriorityQueue<>();
		
		answer = 0;
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(in.readLine().trim());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			
			sizes[i] = size;
			Shark shark = new Shark(x, y, speed, dir, size, 0, i);
			
			pq.offer(shark);
			
			map[x][y] = i;
		}
		
		for(int i=1; i<=C; i++) {
			
			for(int j=1; j<=R; j++) {
				if(map[j][i] != 0 && sizes[map[j][i]] != -1) {
					answer += sizes[map[j][i]];
					sizes[map[j][i]] = -1;
					map[j][i] = 0;
					break;
				}
			}
			int n = pq.size();
			
			for(int j=0; j<n; j++) {
				Shark s = pq.poll();
				if(sizes[s.num] == -1) continue;
				move(s);
			}
		}
		
		System.out.println(answer);
	}
	
	static void move(Shark s) {
		int speed = s.s;
		
		if(map[s.x][s.y] == s.num) map[s.x][s.y] = 0;
		
		while(speed > 0) {
			if(s.d == 1) {
				if(s.x > speed) {
					s.x -= speed;
					speed = 0;
					continue;
				} else {
					speed -= (s.x - 1);
					s.x = 1;
					s.d = 2;
					continue;
				}
			} else if (s.d == 2) {
				if(R - s.x > speed) {
					s.x += speed;
					speed = 0;
				} else {
					speed -= (R - s.x);
					s.x = R;
					s.d = 1;
					continue;
				}
			}else if (s.d == 4) {
				if(s.y > speed) {
					s.y -= speed;
					speed = 0;
					continue;
				} else {
					speed -= (s.y - 1);
					s.y = 1;
					s.d = 3;
					continue;
				}
			} else if (s.d == 3) {
				if(C - s.y > speed) {
					s.y += speed;
					speed = 0;
				} else {
					speed -= (C - s.y);
					s.y = C;
					s.d = 4;
					continue;
				}
			}
		}
		
		if(map[s.x][s.y] != 0) {
			if(s.z > sizes[map[s.x][s.y]]) sizes[map[s.x][s.y]] = -1;
		}
		
		map[s.x][s.y] = s.num;
		s.time += 1;
		pq.offer(s);
	}
}
