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
 		if (databaseSelection == true) {
 			
 			DBuser db = new DBuser();	
 			db.insertData(amntNeeded, saveYearly);
 			System.out.println("Saved data into DB");
 			
 		} else {	
 			System.out.println("Didn't save into DB");
 			System.out.println(databaseSelection);
 			
 		}
     }
}