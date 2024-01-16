package org.example;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();
        botsApi.registerBot(bot);
        bot.sendText(6774167360L, "Hello ,My Lord!");
        Getmoviename getmoviename = new Getmoviename();
//Update(updateId=115769694, message=Message(messageId=137, from=User(id=, firstName=Арсений, isBot=false, lastName=null, userName=null, languageCode=null, canJoinGroups=null, canReadAllGroupMessages=null, supportInlineQueries=null), date=1699986103, chat=Chat(id=-4030378543, type=group, title=Bot test, firstName=null, lastName=null, userName=null, photo=null, description=null, inviteLink=null, pinnedMessage=null, stickerSetName=null, canSetStickerSet=null, permissions=null, slowModeDelay=null, bio=null, linkedChatId=null, location=null, messageAutoDeleteTime=null, hasPrivateForwards=null, HasProtectedContent=null), forwardFrom=null, forwardFromChat=null, forwardDate=null, text=null, entities=null, captionEntities=null, audio=null, document=null, photo=null, sticker=null, video=null, contact=null, location=null, venue=null, animation=null, pinnedMessage=null, newChatMembers=[], leftChatMember=User(id=1248663382, firstName=Арсений, isBot=false, lastName=null, userName=null, languageCode=null, canJoinGroups=null, canReadAllGroupMessages=null, supportInlineQueries=null), newChatTitle=null, newChatPhoto=null, deleteChatPhoto=null, groupchatCreated=null, replyToMessage=null, voice=null, caption=null, superGroupCreated=null, channelChatCreated=null, migrateToChatId=null, migrateFromChatId=null, editDate=null, game=null, forwardFromMessageId=null, invoice=null, successfulPayment=null, videoNote=null, authorSignature=null, forwardSignature=null, mediaGroupId=null, connectedWebsite=null, passportData=null, forwardSenderName=null, poll=null, replyMarkup=null, dice=null, viaBot=null, senderChat=null, proximityAlertTriggered=null, messageAutoDeleteTimerChanged=null, isAutomaticForward=null, hasProtectedContent=null, webAppData=null, videoChatStarted=null, videoChatEnded=null, videoChatParticipantsInvited=null, videoChatScheduled=null), inlineQuery=null, chosenInlineQuery=null, callbackQuery=null, editedMessage=null, channelPost=null, editedChannelPost=null, shippingQuery=null, preCheckoutQuery=null, poll=null, pollAnswer=null, myChatMember=null, chatMember=null, chatJoinRequest=null)



       // Logger logger = (Logger) LogManager.getLogger(Bot.class);
        //logger.info("Test message");
        //logger.error("Error message");



    }
}