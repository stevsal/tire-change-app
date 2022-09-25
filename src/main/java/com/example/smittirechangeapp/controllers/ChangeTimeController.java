package com.example.smittirechangeapp.controllers;

import com.example.smittirechangeapp.interfaces.ILondonTimeService;
import com.example.smittirechangeapp.interfaces.IManchesterTimeService;
import com.example.smittirechangeapp.models.ContactInfo;
import com.example.smittirechangeapp.models.changeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ChangeTimeController {

	@Autowired
	private final ILondonTimeService londonTimeService;
	@Autowired
	private IManchesterTimeService manchesterTimeService;

	public ChangeTimeController(ILondonTimeService londonTimeService) {
		this.londonTimeService = londonTimeService;
	}

	@GetMapping(value = "/manchestertimes")
	public String getManchesterTimes(Model model) {
		//List<changeTime> londonTimes = manchesterTimeService.getAll();
		model.addAttribute("manchesterTimes", manchesterTimeService.getAll());
		model.addAttribute("contactinfo", new ContactInfo());
		return "manchestertimes";
	}

	@GetMapping(value="londontimes")
	public String getLondonTimes(Model model) {
		try {
			model.addAttribute("londonTimes",londonTimeService.getAll());
			return "londontimes";
		}
		catch (Exception e) {
			model.addAttribute("err", "Something went wrong with the request");
			model.addAttribute("errMsg", e.getMessage());
			System.out.println(e.getLocalizedMessage());
			return "londontimes";
		}
	}

	@GetMapping(value = "allAvailableTimes")
	public String getAllAvailableTimes(Model model){
		try {
			List<changeTime> times = londonTimeService.getAll();
			times.addAll(manchesterTimeService.getAll());
			times.sort(Comparator.comparing(t -> t.getTime()));
			model.addAttribute("allTimes", times);
			return "allAvailableTimes";
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("err", "Something went wrong with the request");
			model.addAttribute("errMsg", e.getMessage());
			return "allAvailableTimes";
		}
	}


	@PostMapping(value = "/book")
	public String bookTime(@ModelAttribute ContactInfo contactinfo, Model model){
		try {
			ContactInfo info = new ContactInfo();
			info.setContactInformation("Hellodddd____");
			return manchesterTimeService.bookTimeWithPost(23, info);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			String err = "---Errr___";
			return e.getMessage();
		}

	}
}

