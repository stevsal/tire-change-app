package com.example.smittirechangeapp.models;

public class Site {
	public String name;
	public String address;
	public String types[];
	public String url;

	public Site(String name, String address, String[] types, String url) {
		this.name = name;
		this.address = address;
		this.types = types;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
