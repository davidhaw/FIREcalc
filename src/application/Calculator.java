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

	
	public double amntNeededAdvanced(double amntNeeded, int startAge, int age, double interestInflation) {
		interestInflation = interestInflation / 100;
		int yrsOfFire = startAge - age;
		//i is one and not 0 because with 0 it would add in one more year of inflation where saving would already stop
		int i = 1;
		double saveYInterest;
		
		while (i<yrsOfFire)
		{
			saveYInterest = amntNeeded * interestInflation;
			amntNeeded = amntNeeded - saveYInterest;
			i++;
		}
		
		
		return amntNeeded;
	}
	
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
