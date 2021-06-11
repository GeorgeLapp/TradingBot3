package com.binance.client.MyClasses;

import com.binance.client.SyncRequestClient;
import com.binance.client.model.enums.CandlestickInterval;
import com.binance.client.model.market.Candlestick;

import java.util.ArrayList;
import java.util.List;

public class Binance {
    public  SyncRequestClient syncRequestClient;
    public CandlestickInterval interval ;
    public String pair;
    public float Squoat;
    public float Rquoat;
    public Candlestick StartCandle;
    public  int x=0;
    public Telegram telegram;
    public void Analyzer(CandlestickInterval interval , String pair,SyncRequestClient syncRequestClient){
        Candlestick currentCandle= syncRequestClient.getCandlestick(pair, interval, null, null, 1).get(0);
        System.out.println("\n"+currentCandle.getClose());
        System.out.println(StartCandle.getClose());
        System.out.println(Squoat);
        System.out.println(Rquoat);
        if (StartCandle.getClose().floatValue()<Rquoat && currentCandle.getClose().floatValue()>Rquoat){
            System.out.println("Пробито сопротивление");
            telegram.SentMessage("517552587","Пробито сопротивление ");
            telegram.SentMessage("561172746","Пробито сопротивление ");
            Lines();

        }
        if (StartCandle.getClose().floatValue()>Squoat && currentCandle.getClose().floatValue()<Squoat){
            System.out.println("Пробита поддержка");
            telegram.SentMessage("517552587","Пробита поддержка ");
            telegram.SentMessage("561172746","Пробита поддержка ");
            Lines();
        }
        x++;
        if(x==10){
            Resistance_Line(syncRequestClient,pair,interval);
            Support_Line(syncRequestClient,pair,interval);
            x=0;
        }
    }
    public  void  Main(CandlestickInterval interval1 , String pair1,SyncRequestClient syncRequestClient1){
         telegram = new Telegram();
        interval = interval1;
        pair = pair1;
        syncRequestClient = syncRequestClient1;
        Resistance_Line(syncRequestClient,pair,interval);
        Support_Line(syncRequestClient,pair,interval);
        while (true){
            Analyzer(interval,pair,syncRequestClient);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void Lines(){
        Resistance_Line(syncRequestClient,pair,interval);
        Support_Line(syncRequestClient,pair,interval);
    }
    public void Resistance_Line(SyncRequestClient syncRequestClient, String pair, CandlestickInterval interval){

        List<Candlestick> CandleList = new ArrayList<Candlestick>();
        ArrayList<Float> extremums = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();
        CandleList=syncRequestClient.getCandlestick(pair,interval,null,null,100);
        StartCandle =CandleList.get(99);
        System.out.println();

        for(int x = 97;x>0;x--){
            if (CandleList.get(x).getClose().floatValue()>CandleList.get(x+1).getClose().floatValue()&&CandleList.get(x).getClose().floatValue()>CandleList.get(x+2).getClose().floatValue()&&CandleList.get(x).getClose().floatValue()>CandleList.get(x+1).getClose().floatValue()&&/*CandleList.get(x).getClose().floatValue()>CandleList.get(x+3).getClose().floatValue()&&*/CandleList.get(x).getClose().floatValue()>CandleList.get(x-1).getClose().floatValue()&&CandleList.get(x).getClose().floatValue()>CandleList.get(x-2).getClose().floatValue()/*&&CandleList.get(x).getClose().floatValue()>CandleList.get(x-3).getClose().floatValue()*/){
                extremums.add(CandleList.get(x).getClose().floatValue());
                nums.add(x);

            }
            if (extremums.size()==2){
                break;
            }

        }
        Rquoat =extremums.get(1)+ (extremums.get(1)-extremums.get(0))/(nums.get(1)-nums.get(0))*(100-nums.get(1));
        System.out.println(extremums);

    }
    public void Support_Line(SyncRequestClient syncRequestClient, String pair, CandlestickInterval interval){
        List<Candlestick> CandleList = new ArrayList<Candlestick>();
        ArrayList<Float> extremums = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();
        CandleList=syncRequestClient.getCandlestick(pair,interval,null,null,100);
        for(int x = 97;x>0;x--){
            if (CandleList.get(x).getClose().floatValue()<CandleList.get(x+1).getClose().floatValue()&&CandleList.get(x).getClose().floatValue()<CandleList.get(x+2).getClose().floatValue()/*&&CandleList.get(x).getClose().floatValue()<CandleList.get(x+3).getClose().floatValue()*/&&CandleList.get(x).getClose().floatValue()<CandleList.get(x-1).getClose().floatValue()&&CandleList.get(x).getClose().floatValue()<CandleList.get(x-2).getClose().floatValue()/*&&CandleList.get(x).getClose().floatValue()<CandleList.get(x-3).getClose().floatValue()*/){
                extremums.add(CandleList.get(x).getClose().floatValue());
                nums.add(x);
            }
            if (extremums.size()==2){
                break;
            }

        }
        Squoat =extremums.get(1)+ (extremums.get(1)-extremums.get(0))/(nums.get(1)-nums.get(0))*(100-nums.get(1));
        System.out.println(Squoat);
        System.out.println(extremums);

    }
}

