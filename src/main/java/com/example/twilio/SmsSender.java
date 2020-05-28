package com.example.twilio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.repository.CustomerService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
@Service
public class SmsSender {

	//@Value("${spring.twilioAccountSid}")
	String TwilioAccountSID= "";

	//@Value("${spring.twilioAuthToken}")
	String TwilioAuthToken = "";

	//@Value("${spring.twilioNumber}")
	String TwilioNumber = "";

	//@Value("${spring.myNumber}")
	String MyNumber = "";
	
	@Autowired
	CustomerService customerRepo;
	
	/*public void sendToSubscribers(String messageToSend){
		List<String> list = new ArrayList<String>();
		list = customerRepo.findSubscribedCustomers();
		
		for (String number: list) {
			
		}
	}*/
	
	public void sendMessage(String messageToSend) {
		messageToSend.replace("%", " ");
		Twilio.init(TwilioAccountSID, TwilioAuthToken);
		
		List<String> list = new ArrayList<String>();
		list = customerRepo.findSubscribedCustomers();
		
		for (String number: list) {
			Message message = Message.creator(new PhoneNumber(number), new PhoneNumber(TwilioNumber),
					messageToSend.replace("%", "").replace("20", " ").replace("0", "\n"))
					.create();
			System.out.println(message.getSid());
			System.out.println(message);
		}
		
		/*Message message = Message.creator(new PhoneNumber(MyNumber), new PhoneNumber(TwilioNumber),
				messageToSend.replace("%", "").replace("20", " ").replace("0", "\n"))
				.create();
		System.out.println(message.getSid());
		System.out.println(message);*/
	}

	/*public void sendMessage(String messageToSend) {
		messageToSend.replace("%", " ");
		Twilio.init(TwilioAccountSID, TwilioAuthToken);
		Message message = Message.creator(new PhoneNumber(MyNumber), new PhoneNumber(TwilioNumber),
				messageToSend.replace("%", "").replace("20", " ").replace("0", "\n"))
				.create();
		System.out.println(message.getSid());
		System.out.println(message);
	}*/

	public String processRequest(String request) {
		String result = null;
		switch (request.toLowerCase()) {
		case "supply":
			break;
		case "lastround":
			break;
		case "helpme":
			break;
		default:
		}
		return result;
	}

	public String processRequestForVoice(String request) {
		String result = null;
		switch (request.toLowerCase()) {
		case "supply":
			break;
		case "lastround":
			break;
		default:
			result = "Your request was not understood, please call again";
		}
		return result;
	}

}
