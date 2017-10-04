package com.noonight.pc.schedule.signIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.noonight.pc.schedule.MainActivity;
import com.noonight.pc.schedule.R;

public class SignInActivity extends AppCompatActivity {

    private EditText etSignInLogin;
    private EditText etSignInPas;
    private Button btnSignIn;
    //private boolean signIn = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        init();

    }

    void init() {
        etSignInLogin = (EditText) findViewById(R.id.etSignInLogin);
        etSignInPas = (EditText) findViewById(R.id.etSignInPas);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnClick();
            }
        });
    }

    private void btnClick() {
        if (trySignIn()) {
            openActivity();
        } else {
            Toast.makeText(this, "Логин или пароль не верны.\nПовторите попытку", Toast.LENGTH_LONG).show();
            etSignInPas.setText("");
        }
    }

    private void openActivity() {
        Bundle args = new Bundle();
        args.putString("login", etSignInLogin.getText().toString());
        args.putString("pas", etSignInPas.getText().toString());
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    }

    private boolean trySignIn() {
        if (!etSignInLogin.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
