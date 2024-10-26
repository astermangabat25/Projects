package app.components;

import org.springframework.stereotype.Component;

import app.entities.User;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class UserComponent {
	private Retrofit retrofit;
	
	public User findUser(Long pk) throws Exception{
		OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://localhost:9999/")  // App1's base URL
                .addConverterFactory(GsonConverterFactory.create())  // Gson for JSON conversion
                .build();

        // Update to call the renamed method
        RemoteUserComponent service = retrofit.create(RemoteUserComponent.class);
        Call<User> call = service.test(pk);  // Updated call
        Response<User> response = call.execute();

        // Return the Message object from the response body
        return response.body();

	}
}
