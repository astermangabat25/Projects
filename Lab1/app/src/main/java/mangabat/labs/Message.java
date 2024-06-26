package mangabat.labs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Message extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView text, editMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        text = findViewById(R.id.message);
        editMessage = findViewById(R.id.editMessage);
        editMessage.setText("Hello ".concat(sharedPreferences.getString("name", null)).concat("!"));
        if(sharedPreferences.getBoolean("remember", false)){
            editMessage.setText(editMessage.getText().toString().concat(" You will be remembered!"));
        }
    }
}