import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		
		dfs(0, "");
		
	}
	
	static void dfs(int depth, String answer) {
		if(depth == M) {
			System.out.println(answer);
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			dfs(depth+1, answer+i+" ");
			visited[i] = false;
		}
	}
}
