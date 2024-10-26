package app.components;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Message;
import app.entities.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@Component
public class MessagingComponent {
	@Autowired
    private MessageComponent mc;

    @Autowired
    private UserComponent md;
    
    Message m;
    
    public String messaging(Long pk, String category) throws Exception{
    	User u = md.findUser(pk);
    	if(u != null) {
    		m = mc.getQuote(category);
    	}
    	else {
    		return "No user found";
    	}
    	return sendMessage(u.getCellphoneNumber(), "Hello "+u.getName()+ ", "+m.getMessage());
    }
    
    public String sendMessage(String number, String message) throws Exception {
    	final String creds = "AC8cb6e5ccb2016389a5fac10815484787:1ae5b0025e73b6f02f1ca9a0198a0890";
    	final String msgsid = "MG82a26c0e775a2ab75f69b4eb614633dc";
    	final String url = "https://api.twilio.com/2010-04-01/Accounts/AC8cb6e5ccb2016389a5fac10815484787/Messages.json";
    	
    	Retrofit retrofit = new Retrofit.Builder()
	               .baseUrl("https://bogus")
//	               .addConverterFactory(GsonConverterFactory.create())
	               .build();

		// Basic Authentication
		byte[] encodedAuth= Base64.getEncoder().encode(creds.getBytes());
		final String authorization = "Basic " + new String(encodedAuth);
		
		TwilioRequests req = retrofit.create(TwilioRequests.class);
		Call<ResponseBody> call = req.testSMS(number, 
								msgsid,
								message,
								authorization,
								url);
		
		Response<ResponseBody> resp = call.execute();
		
		System.out.println(resp.code());
		
		if (resp.code()==201)
		{
			System.out.println(resp.body().string());
			return "done";
		}
		else
		{
			System.out.println(resp.errorBody().string());
			return "error";
			
		}

    }
}
