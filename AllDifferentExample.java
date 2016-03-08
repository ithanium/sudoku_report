IntVar x = VF.enumerated("x", new int[]{1, 2}, solver); 
IntVar y = VF.enumerated("y", new int[]{1, 2}, solver);
IntVar z = VF.enumerated("z", new int[]{2, 3}, solver);

solver.post(ICF.alldifferent(new IntVar[]{x, y, z}));

try{
    solver.propagate();
} catch (ContradictionException e){
    // Handle the exception
}

System.out.println(x + "\n" + y + "\n" + z);

if(solver.findSolution()){
    do{
	int x_val = x.getValue();
	int y_val = y.getValue();
	int z_val = z.getValue();

	System.out.println(x_val + "; " + y_val + "; " + z_val);
    } while(solver.nextSolution());
}
