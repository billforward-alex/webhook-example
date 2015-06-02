package net.billforward;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.billforward.model.amendments.ServiceEndAmendment;
import net.billforward.model.notifications.Notification;
import net.billforward.model.notifications.NotificationHelper;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        JsonObject entity = new JsonParser().parse(notification.getEntity()).getAsJsonObject();
        String entityType = entity.get("type").getAsString();
        switch(entityType) {
            case "ServiceEndAmendment":
                ServiceEndAmendment amendment = BillForwardClient.GSON.fromJson(entity, ServiceEndAmendment.class);
                handleAmendmentUpdatedServiceEnd(webhookPayload, amendment);
        }
    }

    public void handleAmendmentUpdatedServiceEnd(String webhookPayload, ServiceEndAmendment amendment) {
        JsonObject notificationJson = new JsonParser().parse(webhookPayload).getAsJsonObject();
        String changesRaw = notificationJson.get("changes").getAsString();
        JsonObject changes = new JsonParser().parse(changesRaw).getAsJsonObject();
        JsonArray auditFieldChanges = changes.getAsJsonArray("auditFieldChanges");

        Iterable<JsonElement> iterable = () -> auditFieldChanges.iterator();
        Stream<JsonElement> auditFieldChangesStream = StreamSupport.stream(iterable.spliterator(), false);

        final Stream<JsonElement> filterForStateChanges = auditFieldChangesStream.filter(x -> x.getAsJsonObject().get("attributeName").getAsString().equals("state"));
        Optional<JsonElement> firstMatching = filterForStateChanges.findFirst();
        if (!firstMatching.isPresent()) {
            return;
        }

        JsonObject stateChange = firstMatching.get().getAsJsonObject();
        if (stateChange.get("newValue").getAsString().equals("Succeeded")) {
            System.out.println(String.format("The subscription '%s' has reached its end of service.", amendment.getSubscriptionID()));
        }
    }
}
