package net.billforward;

import net.billforward.model.notifications.Notification;
import net.billforward.model.notifications.NotificationHelper;

/**
 * Created by birch on 02/06/2015.
 */
public class WebhookConsumer {
    public void receiveWebhookNotification(String webhookPayload) {
        Notification notification = NotificationHelper.parse(webhookPayload);
        System.out.println(notification.getDomain());
        System.out.println(notification.getAction());
    }
}
