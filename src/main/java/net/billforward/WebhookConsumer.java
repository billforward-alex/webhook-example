package net.billforward;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.billforward.model.notifications.Notification;
import net.billforward.model.notifications.NotificationHelper;

/**
 * Created by birch on 02/06/2015.
 */
public class WebhookConsumer {
    public void receiveWebhookNotification(String webhookPayload) {
        Notification notification = NotificationHelper.parse(webhookPayload);
        switch (notification.getDomain()) {
            case Amendment:
                handleAmendment(notification, webhookPayload);
        }
    }

    public void handleAmendment(Notification notification, String webhookPayload) {
        switch (notification.getAction()) {
            case Updated:
                handleAmendmentUpdated(notification, webhookPayload);
        }
    }

    public void handleAmendmentUpdated(Notification notification, String webhookPayload) {
        JsonObject notificationJson = new JsonParser().parse(webhookPayload).getAsJsonObject();
        System.out.println(notificationJson.get("changes"));
    }
}
