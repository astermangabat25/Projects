package app.components;

import app.entities.Message;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteMessageComponent {
	@GET("http://localhost:9997/message/getquote")
	public Call<Message> test(@Query("c") String category);
}
