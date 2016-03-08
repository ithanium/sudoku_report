import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.exception.*;

public class AllDifferentGoodExample {
    public static int solutions_num = 0;
    
    public static void main(String[] args){
	Solver solver = new Solver("AllDifferentExample");

	// x, y, z variables with domains from 1 to 10
	//IntVar x = VF.bounded("x", 1, 5, solver);
	//IntVar y = VF.bounded("y", 1, 5, solver);
	//IntVar z = VF.bounded("z", 1, 5, solver);

	IntVar x = VF.enumerated("x", new int[]{1, 2}, solver); 
	IntVar y = VF.enumerated("y", new int[]{1, 2}, solver);
	IntVar z = VF.enumerated("z", new int[]{2, 3}, solver);
	/*
	solver.post(ICF.arithm(x, "<", y));
	solver.post(ICF.arithm(x, "<", z));
	solver.post(ICF.arithm(y, "<", z));

	solver.post(ICF.sum(new IntVar[]{x, y, z}, VariableFactory.fixed(10, solver)));
	*/	
	solver.post(ICF.alldifferent(new IntVar[]{x, y, z}));

	System.out.println(x + "\n" + y + "\n" + z);
	System.out.println("below print the propagate");
	try{
	    solver.propagate();
	} catch (ContradictionException e){

	}

	System.out.println(x);
	System.out.println(y);
	System.out.println(z);
	// variable ordering
	solver.set(IntStrategyFactory.lexico_LB(new IntVar[]{x, y, z}));

	if(solver.findSolution()){
	    do{
		solutions_num++;
		
		int x_val = x.getValue();
		int y_val = y.getValue();
		int z_val = z.getValue();
		
		System.out.println("x: " + x_val + "\ty: " + y_val + "\tz: " + z_val);
	    } while(solver.nextSolution());
	}

	System.out.println("\nsolutions:" + solutions_num);
	System.out.println("nodes: " + solver.getMeasures().getNodeCount() + 
                           "   cpu: " + solver.getMeasures().getTimeCount());
    }
}

