package mangabat.labs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {
    Button save;
    Button cancel;
    EditText username;
    EditText password;
    EditText password2;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }
    public void save(){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(username.getText().length()==0){
            Toast.makeText(this, "Name must not be blank", Toast.LENGTH_SHORT).show();
        }
        else if(!password.getText().toString().equals(password2.getText().toString())){
            Toast.makeText(this, "Confirm password does not match", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().length()==0 || password2.getText().length()==0) {
            Toast.makeText(this, "Password must not be blank", Toast.LENGTH_SHORT).show();
        }
        else{
            editor.putString("name", username.getText().toString());
            editor.putString("pass", password.getText().toString());
            editor.apply();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    public void cancel(){
        finish();
    }
}