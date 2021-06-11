package com.binance.client.MyClasses;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.model.enums.CandlestickInterval;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;



public class Main  {
    public static SyncRequestClient syncRequestClient;
    public  static String pair;
    public static  CandlestickInterval interval;
    public static void main(String[] args) {

        try {ApiContextInitializer.init();

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

            telegramBotsApi.registerBot(new Telegram());
        } catch (Exception e) {
            e.printStackTrace();
        }
        pair = "BTCUSDT";
        interval = CandlestickInterval.FIFTEEN_MINUTES;
        RequestOptions options = new RequestOptions();
        syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        Binance binance = new Binance();
        binance.Main(CandlestickInterval.FIFTEEN_MINUTES,"BTCUSDT",syncRequestClient);


    }

}
