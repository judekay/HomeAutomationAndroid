package com.example.judekayode.homeautomation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import butterknife.ButterKnife;
//import butterknife.Bind;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private EditText _firstnameText;
    private EditText _lastnameText;
    private EditText _usernameText;
//    private Spinner  _usertypeValue;
    private EditText _passwordText;
    private Button _signupButton;
    private TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        _firstnameText = (EditText) findViewById(R.id.input_firstname);
        _lastnameText = (EditText) findViewById(R.id.input_lastname);
        _usernameText = (EditText) findViewById(R.id.input_usernamesignup);
        _passwordText = (EditText) findViewById(R.id.input_passwordsignup);
//        _usertypeValue = (Spinner) findViewById(R.id.spinner);
        _signupButton = (Button) findViewById(R.id.btn_signup);
        _loginLink = (TextView) findViewById(R.id.link_login);


//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.user_types, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        _usertypeValue.setAdapter(adapter);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String firstname = _firstnameText.getText().toString();
        String lastname = _lastnameText.getText().toString();
        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();
        String usertype = "1";

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String firstname = _firstnameText.getText().toString();
        String lastname = _lastnameText.getText().toString();
        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (firstname.isEmpty()) {
            _firstnameText.setError("Field cannot be empty");
            valid = false;
        } else {
            _firstnameText.setError(null);
        }
        if (lastname.isEmpty()) {
            _lastnameText.setError("Field cannot be empty");
            valid = false;
        } else {
            _lastnameText.setError(null);
        }

        if (username.isEmpty() ) {
            _usernameText.setError("username cannot be empty");
            valid = false;
        } else {
            _usernameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            _passwordText.setError("password must be greater than 5");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}