package com.example.smittirechangeapp.controllers;

import com.example.smittirechangeapp.dto.AvailableTime;
import com.example.smittirechangeapp.models.ContactInfo;
import com.example.smittirechangeapp.models.Filters;
import com.example.smittirechangeapp.services.ChangeTimeService;
import com.google.common.primitives.Ints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

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
		catch (Exception e) {
			model.addAttribute("err", "Something went wrong with the request");
			model.addAttribute("erMessage", e.getMessage());
			return "allAvailableTimes";
		}
	}

	@GetMapping(value = "/book")
	public String bookTime(@RequestParam String contactInfo,@RequestParam String id, Model model){
		try {
			ContactInfo info = new ContactInfo();
			info.setContactInformation(contactInfo);
			if (Ints.tryParse(id) != null) {
				model.addAttribute("response",changeTimeService.bookTimeWithPost(Integer.parseInt(id), info));
			} else {
				model.addAttribute("response",changeTimeService.bookTimeWithPut(id, info));
			}
			return "book";
		}
		catch (Exception e){
			model.addAttribute("error", e.getMessage());
			return "book";
		}
	}
}

