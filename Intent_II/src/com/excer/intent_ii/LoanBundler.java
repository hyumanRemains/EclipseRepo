package com.excer.intent_ii;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;

public class LoanBundler {

	/*
	 * Creates and returns Bundle that stores values 
	 */

	public static Bundle makeLoanInfoBundle(double loanAmount, double annualInterestRateInPercent,
			long loanPeriodInMonths, String currencySymbol) {

		Bundle loanInfo = new Bundle();
		loanInfo.putDouble("loanAmount", loanAmount);
		loanInfo.putDouble("annualInterestRateInPercent", annualInterestRateInPercent);
		loanInfo.putLong("loanPeriodInMonths", loanPeriodInMonths);
		loanInfo.putString("currencySymbol", currencySymbol);

		return loanInfo;
	}

	public static Bundle makeLoanInfoBundle(double loanAmount, double annualInterestRateInPercent,
			long loanPeriodInMonths) {
	
		return(makeLoanInfoBundle(loanAmount, annualInterestRateInPercent, loanPeriodInMonths, "$"));
	}
	
	public static Bundle makeRandomizedLoanInfoBundle(){
		
		Random randomizer = new Random();
		
		double loanAmount = 25000 * (1 + randomizer.nextInt(10));
        double interestRate = 0.25 * (1 + randomizer.nextInt(60));
        long loanPeriod = 12 * (1 + randomizer.nextInt(30));
        return(LoanBundler.makeLoanInfoBundle(loanAmount, 
                                              interestRate, loanPeriod));
		
	}

}
