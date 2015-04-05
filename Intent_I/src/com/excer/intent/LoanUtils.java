package com.excer.intent;

public class LoanUtils {

	public static double monthlyPayment(
			double loanAmount, 
			double annualInterestRateInPercent,
			long loadPeriodInMonths){

		if(annualInterestRateInPercent <= 0){
			annualInterestRateInPercent = 0.000001;
		}
		
		double monthlyInterestRate = annualInterestRateInPercent/1200.0;
		double numerator = loanAmount * monthlyInterestRate;
		double denominator = 
				1 - Math.pow(1 + monthlyInterestRate, -1 * loadPeriodInMonths);
		
		return (numerator/denominator);

	}
}
