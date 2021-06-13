package com.binance.client.MyClasses;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.model.enums.CandlestickInterval;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;



public class Main  extends  Thread{
   public static Binance binance;
    public static SyncRequestClient syncRequestClient;
    public  static  String pair;
    public static CandlestickInterval interval;

    public static void main(String[] args) {

        try {ApiContextInitializer.init();

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

            telegramBotsApi.registerBot(new Telegram());
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestOptions options = new RequestOptions();
         syncRequestClient= SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);


        for(int pai=0;pai<PrivateConfig.pairs.length;pai++){
            for(int inter=0;inter<PrivateConfig.intervals.length;inter++){
                Thread thread = new Main();
                pair = PrivateConfig.pairs[pai];
                interval = PrivateConfig.intervals[inter];
                thread.setName(pai+" "+inter);
                thread.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

    }
    public void run(){
        binance = new Binance();
        binance.Main(interval,pair,syncRequestClient);
    }


}
