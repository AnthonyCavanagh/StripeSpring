package com.lumar.paymentservice.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lumar.paymentservice.model.CardDetails;
import com.lumar.paymentservice.model.PaymentForm;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Token;


@Service
public class StripeServiceImpl implements StripeService{

	@Override
	public Charge charge(PaymentForm payment) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		
		Stripe.apiKey = "sk_test_8jKxIefdjLaRhy2phDsrWNfb00tiP5v93a";
		
		Map<String, Object> chargeParams = new HashMap<>();
	        chargeParams.put("amount", payment.getAmount());
	        chargeParams.put("currency", payment.getCurrency());
	        chargeParams.put("description", payment.getDescription());
	        chargeParams.put("source", payment.getStripeToken());
	        return Charge.create(chargeParams);
	}

	@Override
	public Token getToken(CardDetails card) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Stripe.apiKey = "sk_test_8jKxIefdjLaRhy2phDsrWNfb00tiP5v93a";
		Map<String, Object> cardParams = new HashMap<String, Object>();
		cardParams.put("number", card.getCardNumber());
		cardParams.put("exp_month", card.getExpMonth());
		cardParams.put("exp_year", card.getExpYear());
		cardParams.put("cvc", card.getCsv());
		
		Map<String, Object> tokenParams = new HashMap<String, Object>();
		tokenParams.put("card", cardParams);
		Token token = Token.create(tokenParams);
		return token;
	}

}
