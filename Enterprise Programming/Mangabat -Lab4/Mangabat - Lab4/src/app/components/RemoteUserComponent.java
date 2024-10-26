package app.components;

import app.entities.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteUserComponent {
	@GET("http://localhost:9998/user/finduser")
	public Call<User> test(@Query("p") Long pk);
}
