import java.util.*;

public class Tarjan {
    int[][] A; // adjacency matrix, will be used only for displaying output
    int n; // order of graph
    int pre = 0;
    int count = 0;

    Stack<Integer> S; // stack for algo
    boolean[] stacked; // visited?, used in bfs
    
    int[] id, low;
    static int[] low2, low_index;

    static StringBuilder output = new StringBuilder();
    static ArrayList<String> row_colors = new ArrayList<String>();
    
    Tarjan (int[][] A, int n){
	this.A = A;
	this.n = n;

	row_colors.add("babyblue");
	row_colors.add("yellow");
	row_colors.add("yellow");
	row_colors.add("amber");
	row_colors.add("amber");
	row_colors.add("amber");
	row_colors.add("amber");
	row_colors.add("yellow");
	row_colors.add("yellow");
	row_colors.add("yellow");
	row_colors.add("yellow");
	row_colors.add("applegreen");
	row_colors.add("applegreen");
	row_colors.add("applegreen");
	row_colors.add("applegreen");
	row_colors.add("applegreen");
	row_colors.add("applegreen");
	row_colors.add("americanrose");
	
	S = new Stack<Integer>();
	stacked = new boolean[n];
	
	id = new int[n];
	low = new int[n];
	low2 = new int[n];
	low_index = new int[n];

	for (int v = 0; v < n; v++) {
            if(!stacked[v]){
		//System.out.println("DFS from MAIN " + v);
		//System.out.println("DFS from MAIN " + (v+1) + " (low " + (low[v]+1)  + ")");
		dfs(v);
	    }
        }
    } 

    void dfs(int u){
	stacked[u] = true;

	low[u] = pre;
	low2[u] = pre;
	pre = pre + 1;
	low_index[pre-1] = u;
	int min = low[u];
	
	S.push(u);
	//output.append("\\rowcolor{LightCyan}");
	//output.append("\n");
	for(int i=0; i<S.size(); i++){
	    S.setElementAt((S.get(i) + 1), i);
	}
	output.append((u+1) + "& " + pre + " & lowZ & " + S + " & & \\cellcolor{blue!25} \\\\");
	output.append("\n");
	for(int i=0; i<S.size(); i++){
	    S.setElementAt((S.get(i) - 1), i);
	}
        output.append("\\hline");
	output.append("\n");

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
	    low2[u] = min;

	    return;
	}
	
	int w;

	StringBuilder component = new StringBuilder("[");
	
	do{
	    w = S.pop();
	    component = component.append("" + (w+1) + ", ");
	    id[w] = count;
	    low[w] = n;
	} while (w != u);
	
	count = count + 1;

	component.setLength(component.length() - 2);
	component = component.append("]");

	//output.append("\\rowcolor{LightCyan}");
	//output.append("\n");
	for(int i=0; i<S.size(); i++){
	    S.setElementAt((S.get(i) + 1), i);
	}
	output.append("& & & " + S + " & " + component.toString() + " & \\\\");
	output.append("\n");
	for(int i=0; i<S.size(); i++){
	    S.setElementAt((S.get(i) - 1), i);
	}
	output.append("\\hline");
	output.append("\n");
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

	output.append("\\begin{table}[ht]");
	output.append("\n");
        output.append("\\centering");
	output.append("\n");
        output.append("\\begin{tabular}{|l|l|l|l|l|l|}");
	output.append("\n");
        output.append("\\hline");
	output.append("\n");
        output.append("node & pre & low & stack & new component & color \\\\");
	output.append("\n");
	output.append("\\hline");
	output.append("\n");
	
	Tarjan t = new Tarjan(a, 18);

        output.append("\\end{tabular}");
	output.append("\n");
	output.append("\\end{table}");
	output.append("\n");
	
	for (int i = 0; i < 18; i++) {
	    //output.append((low2[low_index[i]] + 1) + " ");
	    output = new StringBuilder(output.toString().replaceFirst("lowZ", "" + (low2[low_index[i]] + 1) + ""));
	    output = new StringBuilder(output.toString().replaceFirst("blue!25", "" + (row_colors.get(i)) + ""));
	}

	System.out.println(output.toString());

    }
}
