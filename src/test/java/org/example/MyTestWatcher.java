package org.example;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class MyTestWatcher implements TestWatcher {

    private final TelegramNotificationSender notificationSender = new TelegramNotificationSender();
    private static final String OK = "\u2705";
    private static final String NOT_OK = "\u274C";


    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        System.out.println("test Failed ===>");
        String testName = extensionContext.getDisplayName();
        System.out.println( "Test failed by name: " + testName);
        sendNotification(testName, NOT_OK + "failed");    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        TestWatcher.super.testSuccessful(extensionContext);
        System.out.println("test Passed ===>");

        String testName = extensionContext.getDisplayName();
        System.out.println("Test passed by name: " + testName);

        sendNotification(testName, OK +"passed");

    }

    private void sendNotification(String testName, String status) {
        System.out.println("Test " + status + " by name: " + testName);
        notificationSender.sendMessage("Test " + status + " by name: " + testName);
    }
}
