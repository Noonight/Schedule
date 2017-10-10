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
import com.noonight.pc.schedule.extensions.loger.Log;
import com.noonight.pc.schedule.localDB.DBManager;
import com.noonight.pc.schedule.localDB.UsersLocal;

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
            try {
                DBManager db = DBManager.Companion.newInstance(this);
                String name = etSignInLogin.getText().toString();
                UsersLocal user = null;
                try {
                    user = db.getUserByName(name);
                } catch (Exception e) {
                    Toast.makeText(this, "Пользователь не найден", Toast.LENGTH_SHORT).show();
                    Log.d("ошибка с получением ид юзера ( не найден ) -> " + e);
                }
                Log.d(" user =  " + user);
                Log.d(String.valueOf("user id = " + user.getId_user()));
                //db.getLessonsForUser(String.valueOf(id.getId_user()));
                openActivity(String.valueOf(user.getId_user()));
            } catch (Exception e) {
                Log.d("Some error -> " + e.toString());
            }
        } else {
            Toast.makeText(this, "Логин или пароль не верны.\nПовторите попытку", Toast.LENGTH_LONG).show();
            etSignInPas.setText("");
        }
    }

    private void openActivity(String id) {
        if (!id.isEmpty()) {
            try {
                Bundle args = new Bundle();
                args.putString("id_user", id);
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                intent.putExtras(args);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "We have some error with start activity", Toast.LENGTH_LONG).show();
                Log.d("some eror " + e);
            }
        }
    }

    private boolean trySignIn() {
        if (!etSignInLogin.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
            //return true;
        }
    }
}
