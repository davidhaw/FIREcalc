package application;

import java.util.ArrayList;

//This is defiantly very advanced math that includes trigonometry and calculus and not just some basic addition, subtraction, and multiplication
public class Calculator {

	public double amntNeeded (double preSave, double spendFi, int startAge, int deathAge) {
		double amntNeeded;
		int yrsOfFire = deathAge - startAge;
		amntNeeded = yrsOfFire * spendFi;
		amntNeeded = amntNeeded - preSave;
		return amntNeeded;
	}
	
	public double saveYearly (double amntNeeded, int age, int startAge) {
		int ageToSave = startAge - age;
		double result = amntNeeded / ageToSave;
		return result;
	}
	
	//TODO add in advanced mode calculations that include inflation and interest
	
	@SuppressWarnings("rawtypes")
	public ArrayList chartdata (int age, int startAge, double saveYearly, double usedYearly, int deathAge) {
		
		int i = age;
		ArrayList<Object> cash = new ArrayList<>();
		double lastCash = 0;
		while(i<=deathAge) {
			if (i <= startAge) {
				lastCash = lastCash + saveYearly;
				cash.add(lastCash);
				//System.out.println("Added" + lastCash);
				i++;
			} else {
				
				lastCash = lastCash - usedYearly;
				cash.add(lastCash);
				i++;
				//System.out.println("Removed" + lastCash);
			}
		}
		
		return cash;
	
	}
	
}
