package com.lumar.paymentservice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lumar.paymentservice.model.CardDetails;
import com.lumar.paymentservice.model.PaymentForm;
import com.lumar.paymentservice.services.StripeService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Token;


import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
@Controller
public class PaymentController {

	@Autowired
	StripeService paymentService;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	 
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
    public ModelAndView showTypeForm() {
        return new ModelAndView("TokenForm", "cardDetails", new CardDetails());
    }
	
	@RequestMapping(value = "/createPayment", method = RequestMethod.POST)
	public ModelAndView submit(@Valid @ModelAttribute("cardDetails") CardDetails card, BindingResult result, ModelMap model) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		if (result.hasErrors()) {
			
		}
		Token token = paymentService.getToken(card);
		PaymentForm form = new PaymentForm();
		logger.info(token.toString());
		form.setStripeToken(token.getId());
		return new ModelAndView("PaymentForm", "paymentForm", form);
	}
	
	@RequestMapping(value = "/sendPayment", method = RequestMethod.POST)
	public ModelAndView submitPayment(@Valid @ModelAttribute("paymentForm") PaymentForm payment, BindingResult result, ModelMap model) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		if (result.hasErrors()) {
			
		}
		Charge charge = paymentService.charge(payment);
		logger.info(charge.toString());
		 return new ModelAndView("completed");
	}
	
	
}
