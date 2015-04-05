package com.excer.intent_ii;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IntentFilter2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial);
	}
	
	/*
	 * Switch to LoanCalculatorActivity in Intent_I project
	 * Sends data in "extra" bundle
	 */
	public void showLoanPayments1(View clickedButton){
		
		System.out.println("here");

		Uri uri = Uri.parse("test://thishost.com");
		Toast.makeText(this, uri.toString(), 10).show();
		
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	
		intent.putExtras(LoanBundler.makeRandomizedLoanInfoBundle());
		startActivity(intent);
	}
	
	/*
	 * Sends data in query parameters of URI.
	 */
	
	public void showLoanPayments2(View clickedButton){
		
		String address = makeLoanAddressFromEditTextInputs();
		
		Uri uri = Uri.parse(address);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
		
	}
	
/*
 * Build String in Format used by LoanCalculatorActivity from IntentI
 * 
 */
	private String makeLoanAddressFromEditTextInputs(){
		
		EditText loanAmountInput = (EditText)findViewById(R.id.loan_amount);
		Editable loanAmount = loanAmountInput.getText();
		String loanAmountParam =
				String.format("loanAmount=%s", loanAmount);
		
		EditText interestRateInput = (EditText)findViewById(R.id.interest_rate);
		Editable interestRate = interestRateInput.getText();
		String interestRateParam = 
				String.format("annualInterestRateInPercent=%s", interestRate);
		
		EditText loanPeriodInput = (EditText)findViewById(R.id.loan_period);
		Editable loanPeriod = loanPeriodInput.getText();
		String loanPeriodParam =
				String.format("loanPeriodInMonths = %s", loanPeriod);
		
		String baseAddress = "test://thishost.com/";

		String address = 
				String.format("%s?%s&%s&%s", baseAddress, loanAmountParam,
						interestRateParam, loanPeriodParam);
		return (address);
	}
	
}
 