package com.rijkv.breaktimer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class Reminder {
	public Duration timeBefore;
	public String soundPath;

	public boolean isPlayed = false;

	public Reminder(JSONObject jsonObject)
	{
		timeBefore = Duration.parse((CharSequence) jsonObject.get("timeBefore"));
		soundPath = (String)jsonObject.get("soundPath");
	}
}

public class BreakInfo {
	public String name;
	public String description;
	public String soundPath;
	public Duration interval;
	public Duration duration;
	public ArrayList<Reminder> reminders;

	public BreakInfo(JSONObject jsonObject) {
		name = (String) jsonObject.get("name");
		description = (String) jsonObject.get("description");
		soundPath = (String) jsonObject.get("soundPath");
		interval = Duration.parse((CharSequence) jsonObject.get("interval"));
		duration = Duration.parse((CharSequence) jsonObject.get("duration"));

		reminders = new ArrayList<>();
		JSONArray reminderObjects = (JSONArray) jsonObject.get("reminders");
		@SuppressWarnings("unchecked") // Using legacy API
		Iterator<JSONObject> iterator = reminderObjects.iterator();

		while (iterator.hasNext()) {
			reminders.add(new Reminder(iterator.next()));
		}
	}

	public void resetReminders()
	{
		for(var reminder : reminders)
		{
			reminder.isPlayed = false;
		}
	}
}