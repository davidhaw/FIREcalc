package application;


class dbThreadSave implements Runnable {
	
	double amntNeeded;
	double saveYearly;
	boolean databaseSelection;
	
    public dbThreadSave(double amntNeeded, double saveYearly, boolean databaseSelection) {
		this.amntNeeded = amntNeeded;
		this.saveYearly = saveYearly;
		this.databaseSelection = databaseSelection;
	}
     public void run() {
    	//TODO make database saving concurrent because this is not a ui frontend thing, this can just easily run in the background becasue the user won't have any immediate effect from it
 		if (databaseSelection == true) {
 			
 			DBuser db = new DBuser();	
 			db.insertEarnings(amntNeeded, saveYearly);
 			System.out.println("Saved data into DB");
 			
 		} else {	
 			System.out.println("Didn't save into DB");
 			System.out.println(databaseSelection);
 			
 		}
     }
}