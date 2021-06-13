package com.binance.client.MyClasses;

import com.binance.client.model.enums.CandlestickInterval;

public class PrivateConfig {

    public static final String API_KEY = "1iG8KxuxdLeiSBhWU0v0JvXmvLFjml1Arf4TLV9FJeKTnhtxwtedTcnqPxnAhfLH";
    public static final String SECRET_KEY = "QqGWAdzrOyFAxC9Zy9lqKD6jyhVJtPSTpYGWYEqzJ5E4RVpbsVNmPwg7kWexce3s";
    public static final String [] pairs = {"BTCUSDT","DOGEUSDT","ETHUSDT","ETCUSDT","LTCUSDT","XRPUSDT","EOSUSDT","BCHUSDT"};
    public static final String [] links = {"BTCUSDT","DOGEUSDT",};
    public static final CandlestickInterval [] intervals = {CandlestickInterval.FIFTEEN_MINUTES,CandlestickInterval.HOURLY,CandlestickInterval.FOUR_HOURLY,CandlestickInterval.DAILY};


}
