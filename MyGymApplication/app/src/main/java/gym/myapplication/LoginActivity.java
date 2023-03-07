package gym.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_FILE_NAME = "myGymAppPref";

    private DatabaseHelper db;
    private EditText usernameEditText, passwordEditText;
    private Button loginBtn;

    // user credentials
    private String usernameStr;
    private String passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // set title
        setTitle("myGym App - login");

        db = new DatabaseHelper(this);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginBtn = (Button) findViewById(R.id.loginButton);

        // fill username and password from user preferences
        fillUsernamePasswordFromPreferences();

        //---------------------------------------------------------------
        // make text clickable and start register activity
        String text = "Don't have an account yet? \nCreate one by clicking here";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                // start activity register
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        };

        //set spannable text to textView
        ss.setSpan(clickableSpan, 51,55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView mTextView = findViewById(R.id.createAccountTextView);
        mTextView.setText(ss);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        //-----------------------------------------------------------------

        loginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                usernameStr = usernameEditText.getText().toString();
                passwordStr = passwordEditText.getText().toString();

                if(db.loginUser(usernameStr, passwordStr)) {
                    // correct username and password

                    // save username and password in preferences for more quick login
                    saveUserCredentialsToPreferences();

                    // start activity ExercisesCategoriesActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    // wrong username or password
                    Toast.makeText(getApplicationContext(),"Wrong username or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void saveUserCredentialsToPreferences() {

        SharedPreferences appSharedPreferences = getSharedPreferences(PREFS_FILE_NAME,0);
        SharedPreferences.Editor editor = appSharedPreferences.edit();
        editor.putString("username", usernameStr);
        editor.putString("password", passwordStr);
        editor.commit();
    }

    private void fillUsernamePasswordFromPreferences() {

        SharedPreferences appSharedPreferences = getSharedPreferences(PREFS_FILE_NAME,0);
        String prefUsername = appSharedPreferences.getString("username", "");
        String prefPassword = appSharedPreferences.getString("password", "");
        usernameEditText.setText(prefUsername);
        passwordEditText.setText(prefPassword);
    }
}
