import java.util.Stack;

public class Tarjan {
    int[][] A; // adjacency matrix, will be used only for displaying output
    int n; // order of graph
    int pre = 0;
    int count = 0;

    Stack<Integer> S; // stack for algo
    boolean[] stacked; // visited?, used in bfs
    
    int[] id, low;

    Tarjan (int[][] A, int n){
	this.A = A;
	this.n = n;

	S = new Stack<Integer>();
	stacked = new boolean[n];
	
	id = new int[n];
	low = new int[n];

	for (int v = 0; v < n; v++) {
            if(!stacked[v]){
		//System.out.println("DFS from MAIN " + v);
		System.out.println("DFS from MAIN " + (v+1) + " (low " + (low[v]+1)  + ")");
		dfs(v);
	    }
        }
    } 

    void dfs(int u){
	stacked[u] = true;

	low[u] = pre;
	pre = pre + 1;
	int min = low[u];
	
	S.push(u);
	System.out.println("PRE " + (pre) + " gets assigned as low");
	System.out.print("\tPUSHED " + (u+1) + " (low " + (low[u]+1)  + ")" + " on the stack, now S: [");

	for(int i=0; i<S.size(); i++){
	    System.out.print((S.get(i) + 1) + ", ");
	}
	System.out.print("]");
	System.out.println();
	
	for(int i=0; i<A[u].length; i++){
	    int w = A[u][i];

	    if(w == 1 && u!=i){
		if(!stacked[i]){
		    //System.out.println("DFS from " + u + " to " + i);
		    System.out.println("DFS from " + (u+1) + " (low " + (low[u]+1)  + ")" + " to " + (i+1) + " (low " + (low[i]+1)  + ")");
		    dfs(i);
		}
		
		if(low[i] < min){
		    min = low[i];
		}
	    }
	}

	if(min < low[u]){
	    System.out.print("\tLow for " + (u+1) + " got updated from " + (low[u]+1) + " to ");
	    low[u] = min;
	    System.out.print(low[u] + 1);
	    System.out.println();
	    //System.out.println("RETURN FROM " + u);
	    System.out.println("RETURN FROM " + (u+1) + " (low " + (low[u]+1) + ")");
	    return;
	}
	
	int w;
	
	do{
	    w = S.pop();
	    System.out.print("\tPOP " + (w+1) + " (low " + (low[w]+1)  + ")" + " from the stack, now S: [");
	    for(int i=0; i<S.size(); i++){
		System.out.print((S.get(i) + 1) + ", ");
	    }
	    System.out.print("]");
	    System.out.println();
	    
	    System.out.println("\tAdd " + (w+1) + " to SCC (low from now on " + (n+1)  + ")");
	    
	    id[w] = count;
	    low[w] = n;
	} while (w != u);
	
	count = count + 1;

	System.out.println("Finished SCC " + (count-1) + " from " + (u+1));
	System.out.println();
    }

    public static void main(String[] args){
	int[][] a = new int[18][18];

	for (int i = 0; i < 18; i++) {
	    for (int j = 0; j < 18; j++) {
		a[i][j] = 0;
	    }
	}

	a[0][16] = 1;
	a[1][10] = 1;
	a[2][11] = 1;
	a[3][10] = 1;
	a[3][13] = 1;
	a[4][12] = 1;
	a[4][13] = 1;	
	a[5][12] = 1;
	a[5][14] = 1;	
	a[6][10] = 1;
	a[6][17] = 1;	
	a[7][11] = 1;
	a[7][15] = 1;
	a[8][10] = 1;
	a[8][11] = 1;
	a[8][13] = 1;	
	a[8][16] = 1;

	a[9][0] = 1;
	a[10][2] = 1;
	a[11][1] = 1;
	a[12][3] = 1;
	a[13][5] = 1;
	a[14][4] = 1;
	a[15][6] = 1;
	a[16][7] = 1;
	a[17][8] = 1;

	Tarjan t = new Tarjan(a, 18);

	System.out.println("count:" + t.count);
	
	for (int i = 0; i < 18; i++) {
	    System.out.println((i+1) + " is in component " + t.id[i]);
	}
    }
}
