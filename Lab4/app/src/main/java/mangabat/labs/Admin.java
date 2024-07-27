package mangabat.labs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;

public class Admin extends AppCompatActivity {
    Button clear;
    Button add;
    RecyclerView recyclerView;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.userList);
        clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        realm = Realm.getDefaultInstance();
        RealmResults<User> list = realm.where(User.class).findAll();
        UserAdapter adapter = new UserAdapter(this, list, true);
        recyclerView.setAdapter(adapter);
    }
    public void onDestroy() {
        super.onDestroy();
        if (!realm.isClosed())
        {
            realm.close();
        }
    }
    public void clear(){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }
    public void add(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    public void delete(User u){
        if (u.isValid()) {
            realm.beginTransaction();
            u.deleteFromRealm();
            realm.commitTransaction();
        }
    }
    public void edit(User u){
        Intent intent = new Intent(this, Edit.class);
        intent.putExtra("uuid", u.getUuid());
        startActivity(intent);
    }

}