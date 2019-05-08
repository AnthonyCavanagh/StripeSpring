package com.lumar.paymentservice.services;

import com.lumar.paymentservice.model.CardDetails;
import com.lumar.paymentservice.model.PaymentForm;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Token;

public interface StripeService {
	public Charge charge(PaymentForm payment) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException;
	public Token getToken(CardDetails card) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException;
}
