package com.example.smittirechangeapp.controllers;

import com.example.smittirechangeapp.dto.AvailableTime;
import com.example.smittirechangeapp.dto.ContactInfo;
import com.example.smittirechangeapp.dto.Filters;
import com.example.smittirechangeapp.dto.TireChangeBookingResponse;
import com.example.smittirechangeapp.services.ChangeTimeService;
import com.google.common.primitives.Ints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Comparator;
import java.util.List;

@Controller
public class ChangeTimeController {

	@Autowired
	public ChangeTimeService changeTimeService;

	@GetMapping(value = "allAvailableTimes")
	public String getAllAvailableTimes(@ModelAttribute Filters filter, Model model){
		try {
			List<AvailableTime> times = changeTimeService.getAll(filter);
			times.sort(Comparator.comparing(t -> t.getTime()));
			model.addAttribute("Filters", filter);
			model.addAttribute("allTimes", times);
			return "allAvailableTimes";
		}
		catch (HttpClientErrorException e) {
			model.addAttribute("Filters", filter);
			model.addAttribute("erMessage", e.getMessage());
			model.addAttribute("prettyErrorMsg", errorBeautify(e.getRawStatusCode()));
			return "allAvailableTimes";
		}
	}

	@GetMapping(value = "/book")
	public String bookTime(@RequestParam String contactInfo,@RequestParam String id, @RequestParam String bookingInfo,Model model){
		TireChangeBookingResponse response;
		try {
			ContactInfo info = new ContactInfo();
			info.setContactInformation(contactInfo);
			if (Ints.tryParse(id) != null) {
				response = changeTimeService.bookTimeWithPost(Integer.parseInt(id), info);
				model.addAttribute("response", response);
			} else {
				response = changeTimeService.bookTimeWithPut(id, info);
				model.addAttribute("response", response);
			}
			response.fullInfo = bookingInfo;
			return "book";
		}
		catch (HttpClientErrorException e){
			model.addAttribute("prettyError", errorBeautify(e.getRawStatusCode()) );
			model.addAttribute("error", e.getMessage());
			return "book";
		}
	}

	public String errorBeautify(int status){
		switch (status) {
			case 400:
				return "Something went wrong with the request. Check if email is set";
			case 422:
				return "This tire change time has already been booked";
			case 500:
				return "Something broke in the booking server";
			default:
				return "Ru-Roh! Unknown Error!";
		}
	}
}

