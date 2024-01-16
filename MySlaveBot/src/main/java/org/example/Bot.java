package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


public class Bot extends TelegramLongPollingBot {


    private boolean screaming = false;
    private InlineKeyboardMarkup keyboardM1;
    private InlineKeyboardMarkup keyboardM2;
    private InlineKeyboardMarkup keyboardM3;


    @Override
    public String getBotUsername() {
        return "MyTestSlave_bot";
    }

    @Override
    public String getBotToken() {
        return "6861123034:AAG7tpYmIdD7ivC4JCjIkSj9Ab9HIio_S-s";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        update.getUpdateId();
        var next = InlineKeyboardButton.builder()
                .text("Next").callbackData("next")
                .build();

        var back = InlineKeyboardButton.builder()
                .text("Back").callbackData("back")
                .build();

        var url = InlineKeyboardButton.builder()
                .text("Tutorial")
                .url("https://core.telegram.org/bots/api")
                .build();

        var movies = InlineKeyboardButton.builder()
                .text("Get movies").callbackData("movies")
                .build();

        keyboardM1 = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(next)).build();
        keyboardM2 = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(back))
                .keyboardRow(List.of(url))
                .build();
        keyboardM3 = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(movies)).build();

        var cbQuery = update.getCallbackQuery();
        var queryData = cbQuery.getData();
        var queryId = cbQuery.getId();

        var msg = update.getMessage();
        var user = msg.getFrom();
        var chat = msg.getFrom();
        var id = user.getId();
        var txt = msg.getText();
        if(msg.isCommand()) {
            if (txt.equals("/scream"))
                screaming = true;
            else if (txt.equals("/whisper"))
                screaming = false;
            else if (txt.equals("/menu"))
                sendMenu(id, "<b>Menu 1</b>", keyboardM1);
            else if(txt.equals("/moviestoday"))
                sendMenu(id, "<b>Menu 1</b>", keyboardM3);
            return;
        }
        //var chatid = chat.getChat();
        System.out.println(user.getUserName() + " said " + msg.getText());
        //sendText(-1248663382L, msg.getText());//-1248663382L
        //-4030378543L
        if(screaming)                            //If we are screaming
            scream(id, update.getMessage());     //Call a custom method
        //else
            //copyMessage(id, msg.getMessageId()); //Else proceed normally

        Logger logger = (Logger) LogManager.getLogger(Bot.class);
        logger.info("Id: "+id+"L" + " " + "said" + " : " + msg.getText());
        if(msg.isCommand()){
            if(msg.getText().equals("/scream"))         //If the command was /scream, we switch gears
                screaming = true;

            else if (msg.getText().equals("/whisper"))  //Otherwise, we return to normal
                screaming = false;
            else if (msg.getText().equals("/moviestoday")) {
                Getmoviename getmoviename = new Getmoviename();
                sendText(id, String.valueOf(getmoviename));

            }

            //Logger logger = (Logger) LogManager.getLogger(Bot.class);
            //logger.error(id + "said" +  msg.getText());
            //return;                                     //We don't want to echo commands, so we exit
        }

    }

    public void getYourId(){
    }
    private void scream(Long id, Message msg) {
        if(msg.hasText())
            sendText(id, msg.getText().toUpperCase());
       // else
         //   copyMessage(id, msg.getMessageId());  //We can't really scream a sticker
    }

    public void sendText(Long who, String text){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(text).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
    public void sendMenu(Long who, String txt, InlineKeyboardMarkup kb){
        SendMessage sm = SendMessage.builder().chatId(who.toString())
                .parseMode("HTML").text(txt)
                .replyMarkup(kb).build();

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    private void buttonTap(Long id, String queryId, String data, int msgId) {

        EditMessageText newTxt = EditMessageText.builder()
                .chatId(id.toString())
                .messageId(msgId).text("").build();

        EditMessageReplyMarkup newKb = EditMessageReplyMarkup.builder()
                .chatId(id.toString()).messageId(msgId).build();

        if(data.equals("next")) {
            newTxt.setText("MENU 2");
            newKb.setReplyMarkup(keyboardM2);
        } else if(data.equals("back")) {
            newTxt.setText("MENU 1");
            newKb.setReplyMarkup(keyboardM1);
        }else if(data.equals("movies")) {
            newTxt.setText("MENU 3");
            newKb.setReplyMarkup(keyboardM3);
        }

        AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                .callbackQueryId(queryId).build();

        //execute(close);
        //execute(newTxt);
        //execute(newKb);
    }

}




//<dependency>
//    <groupId>org.apache.logging.log4j</groupId>
//    <artifactId>log4j-core</artifactId>
//    <version>2.19.0</version>
//</dependency>
//<dependency>
//    <groupId>org.apache.logging.log4j</groupId>
//    <artifactId>log4j-core</artifactId>
//    <version>2.19.0</version>
//    <type>test-jar</type>
//    <scope>test</scope>
//</dependency>