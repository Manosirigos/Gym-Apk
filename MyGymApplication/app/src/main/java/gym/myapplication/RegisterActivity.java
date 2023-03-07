package gym.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText, repeatPasswordEditText;
    Button registerBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // set title
        setTitle("Register to myGym App");

        db = new DatabaseHelper(this);

        //---------------------------------------------------------------
        // make text clickable and start register activity
        String text = "Back To Login";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                // start activity register
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };

        //set spannable text to textView
        ss.setSpan(clickableSpan, 0,13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView mTextView = findViewById(R.id.backToLoginTextView);
        mTextView.setText(ss);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        //-----------------------------------------------------------------

        usernameEditText = (EditText) findViewById(R.id.usernameRegisterEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordRegisterEditText);
        repeatPasswordEditText = (EditText) findViewById(R.id.repeatPassaordRegistereditText);
        registerBtn = (Button) findViewById(R.id.RegisterButton);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameStr = usernameEditText.getText().toString();
                String passwordStr = passwordEditText.getText().toString();
                String repeatPasswordStr = repeatPasswordEditText.getText().toString();

                // first check if passwordStr and repeatPasswordStr match
                if(passwordStr.equals(repeatPasswordStr)) {
                    // check if user exist
                    if(db.isUserExist(usernameStr)) {
                        Toast.makeText(getApplicationContext(),"Username exist", Toast.LENGTH_LONG).show();
                    }
                    else {
                        //insert user to db
                        boolean returnInsertValue = db.insert(usernameStr,passwordStr);

                        if(returnInsertValue == true){
                            // user registered
                            Toast.makeText(getApplicationContext(),"User registered", Toast.LENGTH_LONG).show();
                        }
                        else {
                            // problem to register new user
                            Toast.makeText(getApplicationContext(),"We have a problem, please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Password doesn't match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
