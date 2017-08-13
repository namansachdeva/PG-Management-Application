package com.naman.dev.royalgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.naman.dev.royalgroup.R.id.roomno;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private AutoCompleteTextView usernameView;
    private AutoCompleteTextView passwordView;
    private Button loginBtn;
    private Button guestmodeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            startActivity(new Intent(this,AdminActivity.class));
            finish();
        }

        usernameView = (AutoCompleteTextView) findViewById(roomno);
        passwordView = (AutoCompleteTextView) findViewById(R.id.roomcapacity);
        loginBtn = (Button) findViewById(R.id.login);
        guestmodeBtn = (Button) findViewById(R.id.guestmode);

        guestmodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GuestActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               attemptLogin();
            }
        });

    }

    private void attemptLogin() {
        usernameView.setError(null);
        passwordView.setError(null);

        View focusView = null;
        boolean cancel = false;

        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();
        if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordView.setError("Enter Correct Password");
            focusView = passwordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(username) && !isUsernameValid(username)) {
            usernameView.setError("Enter Correct Username");
            focusView = usernameView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Go to Dashboard
                                startActivity(new Intent(MainActivity.this, AdminActivity.class));
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Error logging you in.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private boolean isUsernameValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 6;
    }
}
