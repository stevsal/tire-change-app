package com.example.smittirechangeapp.interfaces;

import com.example.smittirechangeapp.dto.TireChangeTimesResponse;
import com.example.smittirechangeapp.models.changeTime;

import java.io.IOException;
import java.util.List;

public interface ILondonTimeService {
	List<changeTime> getAll() throws IOException;
}
