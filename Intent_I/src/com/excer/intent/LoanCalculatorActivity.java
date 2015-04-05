package com.excer.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.TextureView;
import android.widget.TextView;
import android.widget.Toast;

public class LoanCalculatorActivity extends Activity {
	private double mLoanAmount = 100000, mAnnualInterestRateInPercent = 5.0;
	private long mLoanPeriodInMonths = 360;
	private String mCurrencySymbol = "$";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.loan_payment);
		setInputsFromExtras();
		setInputsFromUri();
		calculateAndSetOutputValues();
		
	}

/*
 * Set the instance variables from URI method.
 */
	private void setInputsFromUri() {
		Toast.makeText(this, "this ", 10).show();
		
		Uri uri = getIntent().getData();
	 	if(uri!=null){
			double loanAmount = getDoubleParam(uri,"loanAmount");
			double annualInterestRateInPercent =
					getDoubleParam(uri, "annualInterestRateInPercent");
			long loanPeriodInMonths = getLongParam(uri,"loanPeriodInMonths");
			String currencySymbol = uri.getQueryParameter("currencySymbol");
			
			setInputs(loanAmount, annualInterestRateInPercent, loanPeriodInMonths, currencySymbol);
			
		}
		
	}

private long getLongParam(Uri uri, String queryParam) {
	String rawValue = uri.getQueryParameter(queryParam);
	long value = 0;
	try{
		value = Long.parseLong(rawValue);
	}catch(Exception e){
		
	}
	return (value);
}

private double getDoubleParam(Uri uri, String string) {
		String rawValue = uri.getQueryParameter(string);
		double value = 0.0;
		
		value = Double.parseDouble(rawValue);
		
		return value;
	}

	/*
 * set instance variables from extra bundle
 * -Extract values from Bundle - loanInfo
 * -setInputs(.....)
 */
	private void setInputsFromExtras() {
		Intent intent = getIntent();
		Bundle loanInfo = intent.getExtras();
		
		if(loanInfo != null){
			double loanAmount = loanInfo.getDouble("loanAmount");
			double annualInterestRateInPercent = 
					loanInfo.getDouble("annualInterestRateInPercent");
			long loanPeriodInMonths = 
					loanInfo.getLong("loanPeriodInMonths");
			String currencySymbol = 
					loanInfo.getString("currencySymbol");
			
			setInputs(loanAmount, annualInterestRateInPercent,
					loanPeriodInMonths, currencySymbol);
			
		}
				
		
	}
	
	
	
	private void setInputs(double loanAmount, double annualInterestRateInPercent,
		long loanPeriodInMonths, String currencySymbol) {
	
		if(loanAmount > 0){
			mLoanAmount = loanAmount;
		}
		if(annualInterestRateInPercent>0){
			mAnnualInterestRateInPercent = annualInterestRateInPercent;
		}
		if(loanPeriodInMonths > 0){
			mLoanPeriodInMonths = loanPeriodInMonths; 
		}
		if(currencySymbol != null){
			mCurrencySymbol = currencySymbol;
		}
	
}

	private void calculateAndSetOutputValues() {
		//calculate the payment info. 
		PaymentInfo paymentInfo = 
				new PaymentInfo(mLoanAmount, mAnnualInterestRateInPercent,
						mLoanPeriodInMonths, mCurrencySymbol);

		//display by setting in the text view
		
		TextView loanAmountDisplay = (TextView)findViewById(R.id.loan_amount1);
		loanAmountDisplay.setText(paymentInfo.getFormattedLoanAmount());
	
		TextView interestRateDisplay = (TextView)findViewById(R.id.interest_rate);
		interestRateDisplay.setText(paymentInfo.getFormattedAnnualInterestRateInPercent());
		
		TextView monthlyPaymentDisplay = (TextView)findViewById(R.id.monthly_payment);
		monthlyPaymentDisplay.setText(paymentInfo.getFormattedMonthlyPayment());
		
		TextView loanPeriodDisplay = (TextView)findViewById(R.id.loan_period);
		loanPeriodDisplay.setText(paymentInfo.getFormattedLoanPeriodInMonths());
		
		TextView totalPaymentsDisplay = (TextView)findViewById(R.id.total_payments);
		totalPaymentsDisplay.setText(paymentInfo.getFormattedTotalPayments());
		
	}

}
