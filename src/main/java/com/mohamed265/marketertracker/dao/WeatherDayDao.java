package com.mohamed265.marketertracker.dao;

import java.util.Date;

import com.mohamed265.marketertracker.entity.Video;

public interface WeatherDayDao {

	public void save(Video video) throws Exception;

	public Video getWeatherDayByDate(Date date);

}
