package com.example.smittirechangeapp.models;

import java.time.LocalDateTime;

public class changeTime {

	private String name;
	private LocalDateTime time;
	private String uuid;
	private int id;
	private boolean available;

	public ErrorModel errorStatus;

	public changeTime(){}

	public changeTime(String name, LocalDateTime time, String uuid) {
		this.name = name;
		this.time = time;
		this.uuid = uuid;
	}
	public changeTime (String name, LocalDateTime time, int id, boolean available) {
		this.name = name;
		this.time = time;
		this.id = id;
		this.available = available;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean getAvailable() { return available; }

	public void setAvailable(boolean available) {this.available = available; }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

