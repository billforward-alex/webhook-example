package net.billforward;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigorous Test :-)
     */
    public void testMockWebhookReceipt()
    {
        WebhookConsumer consumer = new WebhookConsumer();
        consumer.receiveWebhookNotification(serviceEndAmendmentSucceeds);
        assertTrue( true );
    }

    private static final String serviceEndAmendmentSucceeds = String.join(System.getProperty("line.separator"),
            "{",
            "   \"type\" : \"notification\",",
            "   \"created\" : \"2015-06-02T15:49:08Z\",",
            "   \"id\" : \"NOT-ABEDFDAB-1098-4B99-A175-FB342E11\",",
            "   \"domain\" : \"Amendment\",",
            "   \"action\" : \"Updated\",",
            "   \"organizationID\" : \"958C9C72-FDB6-4BDB-9C23-4894B4201D6E\",",
            "   \"entityID\" : \"99FF4FCF-8BCF-49DF-9C38-26CC0AACB751\",",
            "   \"entity\" : \"{\\\"type\\\":\\\"ServiceEndAmendment\\\",\\\"created\\\":\\\"2015-06-02T15:49:05Z\\\",\\\"changedBy\\\":\\\"System\\\",\\\"updated\\\":\\\"2015-06-02T15:49:06Z\\\",\\\"id\\\":\\\"99FF4FCF-8BCF-49DF-9C38-26CC0AACB751\\\",\\\"organizationID\\\":\\\"958C9C72-FDB6-4BDB-9C23-4894B4201D6E\\\",\\\"subscriptionID\\\":\\\"0BA31F74-05F7-44C0-BD7D-ACDF46FFF695\\\",\\\"amendmentType\\\":\\\"ServiceEnd\\\",\\\"actioningTime\\\":\\\"2015-06-02T15:49:04Z\\\",\\\"actionedTime\\\":\\\"2015-06-02T15:49:05Z\\\",\\\"state\\\":\\\"Succeeded\\\",\\\"deleted\\\":false}\",",
            "   \"changes\" : \"{\\\"auditFieldChanges\\\":[{\\\"attributeName\\\":\\\"actionedTime\\\",\\\"newValue\\\":\\\"Tue Jun 02 15:49:05 UTC 2015\\\"},{\\\"attributeName\\\":\\\"internalState\\\",\\\"previousValue\\\":\\\"Pending\\\",\\\"newValue\\\":\\\"Succeeded\\\"},{\\\"attributeName\\\":\\\"state\\\",\\\"previousValue\\\":\\\"Pending\\\",\\\"newValue\\\":\\\"Succeeded\\\"}]}\"",
            "}"
    );
}
