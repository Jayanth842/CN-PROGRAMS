import java.util.Scanner;

public class Ford {
    private int D[];
    private int numVer;
    public static final int MAX_VALUE = 999;

    public Ford(int numVer) {
        this.numVer = numVer;
        D = new int[numVer + 1]; // using 1-based indexing
    }

    public void BellmanFordEvaluation(int source, int A[][]) {
        
        for (int node = 1; node <= numVer; node++) {
            D[node] = MAX_VALUE;
        }
        D[source] = 0;

        
        for (int i = 1; i <= numVer - 1; i++) {
            for (int sn = 1; sn <= numVer; sn++) {
                
                if (D[sn] == MAX_VALUE) continue;
                for (int dn = 1; dn <= numVer; dn++) {
                    if (A[sn][dn] != MAX_VALUE) { 
                        if (D[dn] > D[sn] + A[sn][dn]) {
                            D[dn] = D[sn] + A[sn][dn];
                        }
                    }
                }
            }
        }

        
        boolean negativeCycle = false;
        for (int sn = 1; sn <= numVer; sn++) {
            if (D[sn] == MAX_VALUE) continue;
            for (int dn = 1; dn <= numVer; dn++) {
                if (A[sn][dn] != MAX_VALUE) {
                    if (D[dn] > D[sn] + A[sn][dn]) {
                        negativeCycle = true;
                        break;
                    }
                }
            }
            if (negativeCycle) break;
        }

        if (negativeCycle) {
            System.out.println("The graph contains a negative-weight cycle.");
        } else {
            
            for (int vertex = 1; vertex <= numVer; vertex++) {
                if (D[vertex] == MAX_VALUE) {
                    System.out.println("Distance from source " + source + " to " + vertex + " is: INF");
                } else {
                    System.out.println("Distance from source " + source + " to " + vertex + " is: " + D[vertex]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVer = scanner.nextInt();

        int A[][] = new int[numVer + 1][numVer + 1]; 
        System.out.println("Enter the adjacency matrix (enter 0 for no edge; diagonal entries ignored):");
        for (int sn = 1; sn <= numVer; sn++) {
            for (int dn = 1; dn <= numVer; dn++) {
                int val = scanner.nextInt();
                
                if (sn == dn) {
                    A[sn][dn] = 0; 
                } else {
                    if (val == 0) {
                        A[sn][dn] = MAX_VALUE; 
                    } else {
                        A[sn][dn] = val;
                    }
                }
            }
        }

        System.out.print("Enter the source vertex (1 to " + numVer + "): ");
        int source = scanner.nextInt();

        Ford b = new Ford(numVer);
        b.BellmanFordEvaluation(source, A);

        scanner.close();
    }
}
