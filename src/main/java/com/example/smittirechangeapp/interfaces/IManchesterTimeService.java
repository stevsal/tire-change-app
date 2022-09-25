package com.example.smittirechangeapp.interfaces;

import com.example.smittirechangeapp.models.ContactInfo;
import com.example.smittirechangeapp.models.changeTime;

import java.util.List;

public interface IManchesterTimeService {
	List<changeTime> getAll();
	String bookTimeWithPost(int id, ContactInfo info);
}
