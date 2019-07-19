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
	
	public double saveYearly (double amntNeeded, int age, int startAge, double employerMatch, double employerMatchCap) {
		int ageToSave = startAge - age;
		double result = amntNeeded / ageToSave;
	/*	double employerAdd = employerMatch / 100;
		employerAdd = result * employerAdd;
		System.out.println(employerAdd);
		if (employerAdd < employerMatchCap) {
			//i is one and not 0 because with 0 it would add in one more year of inflation where saving would already stop
			int i = 1;			
			while (i<ageToSave)
			{
				result = result - employerAdd;
				i++;
			}
		} else {
			//i is one and not 0 because with 0 it would add in one more year of inflation where saving would already stop
			int i = 1;			
			while (i<ageToSave)
			{
				result = result - employerMatchCap;
				i++;
			}
		}
		*/
		return result;
	}

	
	public double amntNeededAdvanced(double amntNeeded, int startAge, int age, double interestInflation, double ssStart, double ssAmnt, int deathAge) {
		interestInflation = interestInflation / 100;
		int yrsOfFire = startAge - age;
		//i is one and not 0 because with 0 it would add in one more year of inflation where saving would already stop
		int i = 1;
		double saveYInterest;
		
		while (i<yrsOfFire)
		{
			saveYInterest = amntNeeded * interestInflation;
			amntNeeded = amntNeeded + saveYInterest;
			i++;
		}
		
		
		/* while (ssStart < deathAge) {
			amntNeeded = amntNeeded - ssAmnt;
		} */
		
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
