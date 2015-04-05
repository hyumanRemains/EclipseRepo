package com.excer.intent;

import java.io.Serializable;

public class PaymentInfo implements Serializable{

	/** Represents the monthly payments and total payment amount
	 *  for loans with a given principal, interest rate, and period. 
	 *  Used by LoanCalculatorActivity and RotationsActivity.
	 *  It is Serializable so that it can be stored in a Bundle 
	 *  (as done in RotationsActivity).
	 *  <p>
	 *  From <a href="http://www.coreservlets.com/android-tutorial/">
	 *  the coreservlets.com Android programming tutorial series</a>.
	 */

	private final double mLoanAmount, mAnnualInterestRateInPercent, 
			mMonthlyPayment, mTotalPayments;
	private final long mLoanPeriodInMonths;
	private final String mCurrencySymbol;


	public PaymentInfo(double loanAmount, double annualInterestRateInPercent, 
			long loanPeriodInMonths, String currencySymbol){

		/*
		 * Init all final variables in the constructor
		 */
		mLoanAmount = loanAmount;
		mAnnualInterestRateInPercent = annualInterestRateInPercent;
		mLoanPeriodInMonths = loanPeriodInMonths;
		mCurrencySymbol = currencySymbol;

		mMonthlyPayment = LoanUtils.monthlyPayment(loanAmount, annualInterestRateInPercent, loanPeriodInMonths);

		mTotalPayments = mMonthlyPayment * mLoanPeriodInMonths;

	}

		public PaymentInfo(double loanAmount, double annualInterestRateInPercent,
				long loanPeriodInMonths) {
			this(loanAmount, annualInterestRateInPercent, loanPeriodInMonths, "$");
		}

	public double getLoanAmount() {
		return mLoanAmount;
	}

	public String getFormattedLoanAmount(){
		return(String.format("%s%,.2f", mCurrencySymbol, mLoanAmount));
//		return String.valueOf(mLoanAmount);
	}

	public double getAnnualInterestRateInPercent() {
		return mAnnualInterestRateInPercent;
	}

	public String getFormattedAnnualInterestRateInPercent() {
		return (String.format("%,.2f%%", mAnnualInterestRateInPercent));
//		return String.valueOf(mAnnualInterestRateInPercent);
	}

	public double getTotalPayments() {
		return mTotalPayments;
	}

	public String getFormattedTotalPayments() {
		return (String.format("%s%,.2f", mCurrencySymbol, mTotalPayments));
//		return String.valueOf(mTotalPayments);
	}

	public long getLoanPeriodInMonths() {
		return mLoanPeriodInMonths;
	}

	public String getFormattedLoanPeriodInMonths() {
		return (String.format("%s", mLoanPeriodInMonths));
	}

	public String getCurrencySymbol() {
		return mCurrencySymbol;
	}

	public double getmMonthlyPayment() {
		return mMonthlyPayment;
	}

	public String getFormattedMonthlyPayment() {
		return (String.format("%s%,.2f", mCurrencySymbol, mMonthlyPayment));
	}	


}
