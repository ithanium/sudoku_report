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
	
	for(int i=0; i<A[u].length; i++){
	    int w = A[u][i];

	    if(w == 1 && u!=i){
		if(!stacked[i]){
		    dfs(i);
		}
		
		if(low[i] < min){
		    min = low[i];
		}
	    }
	}

	if(min < low[u]){
	    low[u] = min;
	    return;
	}
	
	int w;
	
	do{
	    w = S.pop();
	    id[w] = count;
	    low[w] = n;
	} while (w != u);
	
	count = count + 1;
    }
}
