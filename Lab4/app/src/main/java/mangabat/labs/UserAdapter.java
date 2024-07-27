package mangabat.labs;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class UserAdapter extends RealmRecyclerViewAdapter<User, UserAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView password;
        ImageButton delete;
        ImageButton edit;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            name  = itemView.findViewById(R.id.name);
            password = itemView.findViewById(R.id.pass);
            delete = itemView.findViewById(R.id.deleteButton);
            edit = itemView.findViewById(R.id.editButton);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    Admin activity;

    public UserAdapter(Admin activity, @Nullable OrderedRealmCollection<User> data, boolean autoUpdate){
        super(data, autoUpdate);

        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = activity.getLayoutInflater().inflate(R.layout.row_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        User u = getItem(position);

        File getImageDir = activity.getExternalCacheDir();
        File file = new File(getImageDir, u.getPath());

        holder.name.setText(u.getName());
        holder.password.setText(u.getPassword());
        if(u.getPath()!=null) {
            if (file.exists()) {
                Picasso.get()
                        .load(file)
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .into(holder.imageView);
            } else {
                holder.imageView.setImageResource(R.mipmap.ic_launcher);
            }
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.delete(u);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.edit(u);
            }
        });
    }
}
