package com.binance.client.MyClasses;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;



public class Telegram extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().getText().equals("/start")){
            SentMessage(update.getMessage().getChatId().toString(),update.getMessage().getChatId().toString());
        }
    }
    public void SentMessage(String id, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setText(text);
        sendMessage.setChatId(id);
        try {
            sendMessage(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public String getBotUsername() {
        return "GeorgTradingBot";
    }

    @Override
    public String getBotToken() {
        return "1843926769:AAGCA6cW0OWNZ0Oft3bgjStbRsb4nbLk5Z0";
    }

}
