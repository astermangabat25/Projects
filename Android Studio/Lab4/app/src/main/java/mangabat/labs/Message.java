package mangabat.labs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.io.File;

import io.realm.Realm;

public class Message extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView text, editMessage;
    Realm realm;
    ImageView imageView;
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
        imageView = findViewById(R.id.imageView2);
        realm = Realm.getDefaultInstance();

        String uuid = sharedPreferences.getString("uuid", null);
        User user = realm.where(User.class)
                .equalTo("uuid", uuid)
                .findFirst();

        File getImageDir = getExternalCacheDir();
        File file = new File(getImageDir, user.getPath());
        if(user.getPath()!=null) {
            if (file.exists()) {
                Picasso.get()
                        .load(file)
                        .into(imageView);
            } else {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        }
        text.setText("Welcome "+user.getName()+"!!!");
        if(sharedPreferences.getBoolean("remember", false)){
            editMessage.setText(" You will be remembered!");
        }
        else{
            editMessage.setText("");
        }
    }
}