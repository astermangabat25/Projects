package mangabat.labs;

import android.Manifest;
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

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

import io.realm.Case;
import io.realm.Realm;

public class Login extends AppCompatActivity {
    Button signin;
    Button admin;
    Button clear;
    EditText user;
    EditText password;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    Intent intent;
    Realm realm;

    public void checkPermissions()
    {

        // REQUEST PERMISSIONS for Android 6+
        // THESE PERMISSIONS SHOULD MATCH THE ONES IN THE MANIFEST
        Dexter.withContext(this)
                .withPermissions(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA

                )

                .withListener(new BaseMultiplePermissionsListener()
                {
                    public void onPermissionsChecked(MultiplePermissionsReport report)
                    {
                        if (report.areAllPermissionsGranted())
                        {
                            // all permissions accepted proceed
                            init();
                        }
                        else
                        {
                            // notify about permissions
                            toastRequirePermissions();
                        }
                    }
                })
                .check();

    }


    public void toastRequirePermissions()
    {
        Toast.makeText(this, "You must provide permissions for app to run", Toast.LENGTH_LONG).show();
        finish();
    }
    public void init(){
        signin = findViewById(R.id.signin);
        admin = findViewById(R.id.admin);
        clear = findViewById(R.id.clear);
        user = findViewById(R.id.username);
        password = findViewById(R.id.password);
        checkBox = findViewById(R.id.remember);
        sharedPreferences = getSharedPreferences("user_details",MODE_PRIVATE);

        realm =Realm.getDefaultInstance();

        if (sharedPreferences.getBoolean("remember", false)){
            checkBox.toggle();
            String uuid = sharedPreferences.getString("uuid", null);
            User profile = realm.where(User.class)
                    .equalTo("uuid", uuid)
                    .findFirst();
            if(profile != null) {
                user.setText(profile.getName());
                password.setText(profile.getPassword());
            }
        }


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }
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
        checkPermissions();
    }
    public void signin(){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        User checkUser = realm.where(User.class)
                .equalTo("name", user.getText().toString(), Case.INSENSITIVE)
                .findFirst();
        if(checkUser!=null){
            if(password.getText().toString().equals(checkUser.getPassword())){
                editor.putBoolean("remember", checkBox.isChecked());
                editor.putString("uuid", checkUser.getUuid());
                editor.apply();

                intent = new Intent(this,Message.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "No User found", Toast.LENGTH_SHORT).show();
        }
    }
    public void admin(){
        intent = new Intent(this, Admin.class);
        startActivity(intent);
    }
    public void clear(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Toast.makeText(this, "Preferences cleared", Toast.LENGTH_SHORT).show();
        editor.clear();
        editor.apply();

    }
}