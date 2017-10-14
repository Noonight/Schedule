package com.noonight.pc.schedule.signIn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.TimeUnit;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.noonight.pc.schedule.LoadService;
import com.noonight.pc.schedule.MainActivity;
import com.noonight.pc.schedule.R;
import com.noonight.pc.schedule.extensions.loger.Log;
import com.noonight.pc.schedule.localDB.DBManager;
import com.noonight.pc.schedule.localDB.UsersLocal;

public class SignInActivity extends AppCompatActivity {

    private EditText etSignInLogin;
    private EditText etSignInPas;
    private Button btnSignIn;
    private boolean signIn = false;
    private SharedPreferences sharedPreferences;
    private String userId;
    public Boolean dbS = false;
    public static final String USER_ID = "USERID";
    public static final String DB = "DB";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        init();
        btnClick();
    }

    void init() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        dbS = sharedPreferences.getBoolean(DB, false);
        try {
            if (!dbS) {
                new DBManager().deleteAllLocal();
                startService(new Intent(this, LoadService.class));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    TimeUnit.SECOND.wait(4000);
                }
            }
        } catch (Exception e) {
            Log.d(e.toString());
        }
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
                UsersLocal user;
                try {
                    sharedPreferences = getPreferences(MODE_PRIVATE);
                    if (sharedPreferences.getString(USER_ID, "").length() > 0) {
                        userId = sharedPreferences.getString(USER_ID, "");
                        Log.d(userId);
                    } else {
                        user = db.getUserByName(name);
                        dbS = true;
                        userId = String.valueOf(user.getId_user());
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Пользователь не найден", Toast.LENGTH_SHORT).show();
                    Log.d("ошибка с получением ид юзера ( не найден ) -> " + e);
                }
                SharedPreferences.Editor ed = sharedPreferences.edit();
                ed.putBoolean(DB, true);
                ed.putString(USER_ID, userId);
                ed.commit();
                openActivity(String.valueOf(userId));
            } catch (Exception e) {
                Log.d("Some error -> " + e.toString());
            }
        } else {
            Toast.makeText(this, "Логин или пароль не верны.\nПовторите попытку", Toast.LENGTH_LONG).show();
            openActivity(etSignInLogin.getText().toString());
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
                finish();
            } catch (Exception e) {
                Toast.makeText(this, "We have some error with start activity", Toast.LENGTH_LONG).show();
                Log.d("some eror " + e);
            }
        }
    }

    private boolean trySignIn() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        if (!etSignInLogin.getText().toString().isEmpty() || sharedPreferences.getString(USER_ID, "").length() > 0) {
            return true;
        } else {
            return false;
            //return true;
        }
    }
}
