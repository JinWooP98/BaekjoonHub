import java.util.*;
import java.io.*;

/**
 * 
 * 며칠이 지나면 토마토들이 모두 익는지..
 * 상자, 토마토 1개당 1칸, 익은 토마토/안익은 토마토
 * 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 됨
 * 인접한곳 -> 상하좌우
 * 
 * 토마토 -> 위치, 시간을 가지는 객체로 만들기 / 우선순위 큐에 담기(시간기준)
 * 큐가 비면 배열을 검사하여 안익은 토마토가 있으면 -1반환 아니면 정답 제출
 */

public class Main {
	
	static class Tomato implements Comparable<Tomato>{
		int x;
		int y;
		int time;
		
		public Tomato(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Tomato o) {
			// TODO Auto-generated method stub
			return this.time - o.time;
		}
	}
	
	// 상자의 세로, 가로
	static int N;
	static int M;
	
	// 박스, 우선순위 큐
	static int[][] box;
	static PriorityQueue<Tomato> pq = new PriorityQueue<>();
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine().trim());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine().trim());
			for(int j=0; j<M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				
				if(box[i][j] == 1) {
					pq.offer(new Tomato(i, j, 0));
				}
			}
		}
		
		answer = 0;
		
		while(!pq.isEmpty()) {
			Tomato tomato = pq.poll();
			
			if(answer < tomato.time) {
				answer = tomato.time;
			}
			
			for(int i=0; i<4; i++) {
				int nx = tomato.x + dx[i];
				int ny = tomato.y + dy[i];
				
				if(!inRange(nx, ny)) continue;
				if(box[nx][ny] != 0) continue;
				
				box[nx][ny] = 1;
				pq.offer(new Tomato(nx, ny, tomato.time + 1));
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(box[i][j] == 0) answer = -1;
			}
		}
		
		System.out.println(answer);
	}
	
	static public boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < M);
	}
	
	
}