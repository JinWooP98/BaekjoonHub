import java.util.*;
import java.io.*;

/**
 * 
 * 빙산이 두 덩어리 이상으로 분리되는 최초의 시간
 * 상하좌우로 만나는 바다의 수만큼 높이가 감소
 * 
 * 빙산 객체로 만들기 -> x, y, height, time
 * 우선순위큐에 담기 -> time기준, time이 같다면 height기준
 * 
 * 각 시간별로 녹으면 일단 -1로 지정
 * 같은 시간대 다 돌면 -1은 우선순위큐에서 빼고 0으로 바꾸기
 * 두 덩어리로 나뉘는지 확인(bfs)
 *
 */

public class Main {
	
	static class Iceberg implements Comparable<Iceberg>{
		int x;
		int y;
		int height;
		int time;
		
		public Iceberg(int x, int y, int height, int time) {
			this.x = x;
			this.y = y;
			this.height = height;
			this.time = time;
		}

		@Override
		public int compareTo(Iceberg o) {
			if(this.time == o.time) return this.height - o.height;
			return this.time - o.time;
		}
		
	}
	
	static int N;
	static int M;
	
	static PriorityQueue<Iceberg> pq = new PriorityQueue<>();
	static Deque<int[]> dq = new ArrayDeque<>();
	
	static int answer;
	static boolean[][] visited;
	static int[][] map;
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine().trim());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] != 0) {
					pq.offer(new Iceberg(i, j, map[i][j], 0));
				}
			}
		}
		
		int time = 0;
		int answer = 0;
		
		while(!pq.isEmpty()) {
			Iceberg iceberg = pq.poll();
			
			if(iceberg.height == -1) {
				map[iceberg.x][iceberg.y] = 0;
				continue;
			}
			
			if(iceberg.time > time) {
				time = iceberg.time;
				
				// 여기서 2덩어리인지 검사
				if(check(iceberg)) {
					answer = time;
					break;
				}
			}
			
			int height = iceberg.height;
			
			for(int i=0; i<4; i++) {
				int nx = iceberg.x + dx[i];
				int ny = iceberg.y + dy[i];
				
				if(map[nx][ny] != 0) continue;
				
				height--;
			}
			
			if(height <= 0) height = -1;
			
			map[iceberg.x][iceberg.y] = height;
			
			pq.offer(new Iceberg(iceberg.x, iceberg.y, height, iceberg.time + 1));
			
		}
		
		System.out.println(answer);
	}
	
	static public boolean check(Iceberg icegerg) {
		int count = 1;
		
		dq.offer(new int[] {icegerg.x, icegerg.y});
		visited = new boolean[N][M];
		visited[icegerg.x][icegerg.y] = true;
		
		while(!dq.isEmpty()) {
			int[] ice = dq.poll();
			
			for(int i=0; i<4; i++) {
				int nx = ice[0] + dx[i];
				int ny = ice[1] + dy[i];
				
				if(visited[nx][ny]) continue;
				if(map[nx][ny] == 0) continue;
				
				visited[nx][ny] = true;
				count++;
				dq.offer(new int[] {nx, ny});
			}
		}
		
		dq.clear();
		
		return count-1 != pq.size();
		
	}
}