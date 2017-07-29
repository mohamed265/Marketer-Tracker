package com.mohamed265.marketertracker.service;

import com.mohamed265.marketertracker.entity.Video;

public interface WeatherDayService {

	public boolean save(Video video);

	public Video getCurrentWeatherDay();

}