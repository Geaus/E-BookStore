package com.example.ebook.ServiceImpl;

import com.example.ebook.Entity.JSON.TimerResult;
import com.example.ebook.Service.ClockService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Scope("session")
public class ClockServiceImpl implements ClockService {

    long startTime=0;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String startStr="";
    String endStr="";


    @Override
    public String startClock(){

        if(startTime==0){

            startTime=System.currentTimeMillis();
            Date start =new Date(startTime);
            startStr=simpleDateFormat.format(start);

            System.out.println("登录时间:"+startStr);
            return "登录时间:"+startStr;
        }
        else{
            return "登录时间:"+startStr;
        }
    }

    @Override
    public TimerResult endClock(){

        if(startTime>0){
            long endTime=System.currentTimeMillis();
            long interval=endTime-startTime;
            interval /= 1000;
            long h=interval/3600;
            long m=interval%3600/60;
            long s=(interval%3600)%60;
            Date end =new Date(endTime);
            endStr=simpleDateFormat.format(end);

            String hh= h==0?"":String.valueOf(h)+"h";
            String mm= m==0?"":String.valueOf(m)+"m";
            String ss= s==0?"":String.valueOf(s)+"s";

            this.startTime=0;
            String str= "登录时间:"+startStr+"\n"+
                    "登出时间:"+endStr+"\n"+
                    "在线时间: "+hh+mm+ss;

            TimerResult result=new TimerResult();
            result.setResult(str);
            return result;
        }
        else{
            return null;
        }
    }

}
