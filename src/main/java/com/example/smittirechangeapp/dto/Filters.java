package com.example.smittirechangeapp.dto;

import java.time.LocalDateTime;

public class Filters {
	private String name;
	private boolean car;
	private boolean truck;
	private LocalDateTime from;
	private LocalDateTime until;

	public boolean isCar() {
		return car;
	}

	public void setCar(boolean car) {
		this.car = car;
	}

	public boolean isTruck() {
		return truck;
	}

	public void setTruck(boolean truck) {
		this.truck = truck;
	}

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getUntil() {
		return until;
	}

	public void setUntil(LocalDateTime until) {
		this.until = until;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
