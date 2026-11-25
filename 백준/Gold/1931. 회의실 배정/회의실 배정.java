
import java.util.*;
import java.io.*;


public class Main {
	
	static int N;
	
	static class TimeTable implements Comparable<TimeTable>{
		int start;
		int finish;
		
		public TimeTable(int start, int finish) {
			this.start = start;
			this.finish = finish;
		}

		@Override
		public int compareTo(TimeTable o) {
			// TODO Auto-generated method stub
			if(this.finish == o.finish) return this.start - o.start;
			return this.finish - o.finish;
		}
	}
	
	static PriorityQueue<TimeTable> pq = new PriorityQueue<>();
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine().trim());
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine().trim());
			pq.offer(new TimeTable(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		int time = 0;
		int answer = 0;
		while(!pq.isEmpty()) {
			TimeTable t = pq.poll();
			if(t.start >= time) {
				time = t.finish;
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	
	
	
}
