package com.noonight.pc.schedule.signIn;

import android.app.Activity;
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
import com.noonight.pc.schedule.LocalVariable;
import com.noonight.pc.schedule.MainActivity;
import com.noonight.pc.schedule.R;
import com.noonight.pc.schedule.extensions.loger.Log;
import com.noonight.pc.schedule.localDB.DBManager;
import com.noonight.pc.schedule.localDB.UsersLocal;

public class SignInActivity extends AppCompatActivity {

    private EditText etSignInLogin;
    private EditText etSignInPas;
    private Button btnSignIn;
    private SharedPreferences sharedPreferences;
    private String userId;
    private Boolean newThis = true;
    public static final String NEW_INSTANCE = "newInstance";
    private boolean dbNull = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        init();
        //newThis = newThisInstance();
        checkDB();
        trySignIn();
    }

    private void init() {
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

    private boolean newThisInstance() {
        try {
            newThis = getIntent().getBooleanExtra(NEW_INSTANCE, false);
        }catch (Exception e) {
            Log.d(e.toString());
        }
        if (newThis) {
            return true;
        } else {
            return false;
        }
    }

    private void btnClick() {
        if (trySignIn()) {
            Log.d("GOOD");
        } else {
            Log.d("SOME ERROR");
        }
    }

    private void checkDB() {
        getSHDB();
        if (dbNull) {
            downloadDB();
            Log.d("download new db");
            dbNull = false;
            putSHDB();
        }
    }

    private void putSHDB() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LocalVariable.getDB_DATE(), dbNull);
        editor.apply();
        Log.d("putting shared preferences user id" + userId);
    }

    private void getSHDB() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        dbNull = sharedPreferences.getBoolean(LocalVariable.getDB_DATE(), true);
    }

    private void setSHDB() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LocalVariable.getDB_DATE(), true);
        dbNull = false;
        editor.apply();
        Log.d("set sh db = " + dbNull);
    }

    private void openMainActivity() {
        if (!userId.isEmpty()) {
            try {
                Bundle args = new Bundle();
                args.putString(LocalVariable.getID_USER(), userId);
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                intent.putExtras(args);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                Log.d(e.toString());
            }
        } else {
            Log.d("userId is empty");
        }
    }

    private Boolean trySignIn() {
        //newThis = getSPUser() ? false : true;
        if (newThis) {
            Log.d("we here1");
            if (getIntent().getBooleanExtra(NEW_INSTANCE, false)) {
                //delSPUser();
                Log.d("we here");
            }
            delSPUser();
            userId = findUser();
            openMainActivity();
            return true;
        } else if (!newThis) {
            userId = findUser();
            openMainActivity();
            return true;
        } else {
            return false;
        }
    }

    /*
    * return userId
    * */
    private String findUser() {
        DBManager db = DBManager.Companion.newInstance(this);
        String user = etSignInLogin.getText().toString();
        if (getSPUser()) {
            return userId;
        } else if (!getSPUser()) {
            try {
                String newUserId = String.valueOf(db.getUserByName(user).getId_user());
                userId = newUserId;
                putSPUser();
            } catch (Exception e) {
                if (user.length() >= 1) {
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
                }
                Log.d(e.toString());
            }
            return userId;
        } else {
            Log.d(userId);
            return userId;
        }
    }

    /*
    * Если найден в sharedPreferences user id то true иначе false
    * */
    private boolean getSPUser() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        try {
            userId = sharedPreferences.getString(LocalVariable.getID_USER(), "");
        } catch (Exception e) {
            Log.d(e.toString());
        }
        if (userId.length() >= 1) {
            return true;
        } else {
            return false;
        }
    }
    /*
    * Отправляем в sharedPreferences текущий user id
    * */
    private void putSPUser() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LocalVariable.getID_USER(), userId);
        editor.apply();
        Log.d("putting shared preferences user id" + userId);
    }

    private void delSPUser() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LocalVariable.getID_USER(), "");
        userId = null;
        editor.apply();

        //
        Log.d("Длина userId > 0 = " + getSPUser() + "\nuserId = " + userId);
        //
    }

    private void downloadDB() {
        startService(new Intent(this, LoadService.class));
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                TimeUnit.SECOND.wait(4000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*void init() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        newThis = getIntent().getBooleanExtra(USER_ID, false);
        if (newThis) {
            SharedPreferences.Editor ed = sharedPreferences.edit();
            ed.putString(USER_ID, "");
            ed.apply();
        }
        dbS = sharedPreferences.getBoolean(DB, false);
        try {
            if (!dbS) {
                new DBManager().deleteAllLocal();
                startService(new Intent(this, LoadService.class));

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
    }*/

    /*private void btnClick() {
        if (trySignIn()) {
            try {
                DBManager db = DBManager.Companion.newInstance(this);
                String name = etSignInLogin.getText().toString();
                UsersLocal user;
                try {
                    sharedPreferences = getPreferences(MODE_PRIVATE);
                    if (newThis) {
                        user = db.getUserByName(name);
                        dbS = true;
                        userId = String.valueOf(user.getId_user());
                        //newThis = true;
                    } else {
                        if (sharedPreferences.getString(USER_ID, "").length() > 0) {
                            userId = sharedPreferences.getString(USER_ID, "");
                            Log.d(userId);
                        } else {
                            user = db.getUserByName(name);
                            dbS = true;
                            userId = String.valueOf(user.getId_user());
                        }
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
    }*/

    /*private void openActivity(String id) {
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
    }*/

    /*private boolean trySignIn() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        if (!etSignInLogin.getText().toString().isEmpty() || sharedPreferences.getString(USER_ID, "").length() > 0) {
            return true;
        } else {
            return false;
            //return true;
        }
    }*/
}
