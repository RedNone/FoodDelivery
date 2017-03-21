package com.example.rednone.fooddelivery;




import android.app.ProgressDialog;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editEmail;
    private EditText editPassword;

    private Button signIn;
    private Button createAcc;

    private LinearLayout Layout;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Intent intent;

    private final String TAG = "LoginTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG, "Start");
        intent = new Intent(this,MainActivity.class);

        //EditTexts
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);

        //Buttons
        signIn = (Button) findViewById(R.id.buttonSign);
        signIn.setOnClickListener(this);

        createAcc = (Button) findViewById(R.id.buttonCreate);
        createAcc.setOnClickListener(this);

        //Layout
        Layout = (LinearLayout) findViewById(R.id.conteiner);

        //initialize mAuth
        mAuth = FirebaseAuth.getInstance();

        //auth state listener
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };


    }
    private void createAccount(String email,String password)
    {
        if (!CheckInput()){return;}

        Log.d(TAG, "email " + email + " password" + password);
        createAcc.setEnabled(false);
        signIn.setEnabled(false);

        Log.d(TAG," task: " + "");
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG," task: " + task.isSuccessful());
                if(task.isSuccessful())
                {

                    startActivity(intent);
                    createAcc.setEnabled(true);
                    signIn.setEnabled(true);
                }
                else
                {
                    Snackbar.make(Layout,R.string.errorCreateAcc,Snackbar.LENGTH_SHORT).show();

                }
                createAcc.setEnabled(true);
                signIn.setEnabled(true);

            }
        });
    }
    private void signIn(String email, String password)
    {
        if (!CheckInput()){return;}

        createAcc.setEnabled(false);
        signIn.setEnabled(false);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG," task: " + task.isSuccessful());
                if(task.isSuccessful())
                {
                    startActivity(intent);
                    createAcc.setEnabled(true);
                    signIn.setEnabled(true);
                }
                else
                {
                    Snackbar.make(Layout,R.string.errorSign,Snackbar.LENGTH_SHORT).show();
                }
                createAcc.setEnabled(true);
                signIn.setEnabled(true);
            }
        });

    }

    private boolean CheckInput()
    {
        if(editEmail.getText().toString().equals(""))
        {
            Snackbar.make(Layout,R.string.incorrectInput,Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if(editPassword.getText().toString().equals(""))
        {
            Snackbar.make(Layout,R.string.incorrectInput,Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onClick(View v)
    {


        switch (v.getId())
        {
            case R.id.buttonSign:
                signIn(editEmail.getText().toString(),editPassword.getText().toString());
                break;
            case R.id.buttonCreate:
                createAccount(editEmail.getText().toString(),editPassword.getText().toString());
                break;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null)
        {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
