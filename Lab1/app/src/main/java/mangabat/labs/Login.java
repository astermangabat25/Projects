package mangabat.labs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    Button signin;
    Button register;
    Button clear;
    EditText user;
    EditText password;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        signin = findViewById(R.id.signin);
        register = findViewById(R.id.register);
        clear = findViewById(R.id.clear);
        user = findViewById(R.id.username);
        password = findViewById(R.id.password);
        checkBox = findViewById(R.id.remember);
        sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);

        if (sharedPreferences.getBoolean("remember", false)){
            checkBox.toggle();
            user.setText(sharedPreferences.getString("name", null));
            password.setText(sharedPreferences.getString("pass", null));
        }


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }
    public void signin(){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String init_username = sharedPreferences.getString("name", null);
        String init_password = sharedPreferences.getString("pass", null);

        if(init_username!=null && init_password!=null){
            if(init_username.equals(user.getText().toString()) && init_password.equals(password.getText().toString())){
                editor.putBoolean("remember", checkBox.isChecked());
                editor.apply();
                intent = new Intent(this,Message.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,"Nothing saved", Toast.LENGTH_SHORT).show();
        }
    }
    public void register(){
        intent = new Intent(this,Register.class);
        startActivity(intent);
    }
    public void clear(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Toast.makeText(this, "Preferences cleared", Toast.LENGTH_SHORT).show();
        editor.clear();
        editor.apply();

    }
}