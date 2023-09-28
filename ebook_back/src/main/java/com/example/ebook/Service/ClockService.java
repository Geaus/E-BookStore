package com.example.ebook.Service;

import com.example.ebook.Entity.JSON.TimerResult;
import com.fasterxml.jackson.databind.util.JSONPObject;

public interface ClockService {

    public String startClock();
    public TimerResult endClock();
}
