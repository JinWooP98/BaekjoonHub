import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        System.out.println(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));
    }
}