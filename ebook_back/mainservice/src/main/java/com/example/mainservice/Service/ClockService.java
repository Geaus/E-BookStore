package com.example.mainservice.Service;

import com.example.mainservice.Entity.JSON.TimerResult;
import com.fasterxml.jackson.databind.util.JSONPObject;

public interface ClockService {

    public String startClock();
    public TimerResult endClock();
}
