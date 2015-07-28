package com.example.blunter;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseUser;

public class LoginActivity extends Activity {
	
	protected EditText mUsername;
	protected EditText mPassword;
	protected Button mLoginButton;
	
	protected TextView mSignUpTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//adding progress indicator
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_login);
		
		mSignUpTextView = (TextView)findViewById(R.id.signUpText1);
		mSignUpTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
				startActivity(intent);
			}
		});
		mUsername= (EditText)findViewById(R.id.usernameField);
		mPassword= (EditText)findViewById(R.id.passwordField);
		mLoginButton= (Button)findViewById(R.id.login1Button);
		mLoginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();
				
				username = username.trim();
				password = password.trim();
				
				if(username.isEmpty() || password.isEmpty()) {
					AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
					builder.setMessage(R.string.login_error_message);
					builder.setTitle(R.string.login_error_title);
					builder.setPositiveButton(android.R.string.ok, null);
					
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				else {
					//login
					//on or off progress indicator
					//true to make it visible
					setProgressBarIndeterminateVisibility(true);
					ParseUser.logInInBackground(username, password, new LogInCallback() {
						public void done(ParseUser user, ParseException e) {
							//false hides progress indicator
							setProgressBarIndeterminateVisibility(false);
							
							if(e==null){
								//success
								Intent intent = new Intent(LoginActivity.this,MainActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
							startActivity(intent);
						}
						else {
							AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
							builder.setMessage(e.getMessage())
								.setTitle(R.string.login_error_title)
								.setPositiveButton(android.R.string.ok, null);
							AlertDialog dialog = builder.create();
							dialog.show();
						}
						}

						@Override
						public void done(ParseUser arg0,
								com.parse.ParseException arg1) {
							// TODO Auto-generated method stub
							
						}
					});
					}
				}
					});

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.signUpText, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			return rootView;
		}
	}

}
