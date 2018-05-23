package com.skilldealteam.skilldeal.helpers;

public class NotificationMessage {

    private static final String SENT_LESSON_REQUEST = " has sent you a lesson request.";
    private static final String CONFIRMED_LESSON_REQUEST = " has confirmed your lesson request.";
    private static final String CANCELED_LESSON_REQUEST = " has canceled your lesson request.";
    private static final String CANCELED_LESSON = " has cancelled the lesson.";
    private static final String MODIFIED_PROFILE = "You have successfully modified your profile info!";
    private static final String REGISTERED_ACCOUNT = " You have successfully registered your account!";

    public static final String SENT_LESSON_REQUEST_ICON = "fa-bell";
    public static final String CONFIRMED_LESSON_REQUEST_ICON = "fa-check-circle";
    public static final String CANCELED_LESSON_REQUEST_ICON = "fa-times-circle";
    public static final String CANCELED_LESSON_ICON = "fa-ban";
    public static final String MODIFIED_PROFILE_ICON = "fa-user-edit";
    public static final String REGISTERED_ACCOUNT_ICON = "fa-user-plus";


    public static String sentLessonRequestMessage(String name, String surname) {
        return name + " " + surname + SENT_LESSON_REQUEST;
    }

    public static String confirmedLessonRequestMessage(String name, String surname) {
        return name + " " + surname + CONFIRMED_LESSON_REQUEST;
    }

    public static String canceledLessonRequestMessage(String name, String surname) {
        return name + " " + surname + CANCELED_LESSON_REQUEST;
    }

    public static String canceledLessonMessage(String name, String surname) {
        return name + " " + surname + CANCELED_LESSON;
    }

    public static String modifiedProfileMessage() {
        return MODIFIED_PROFILE;
    }

    public static String registeredAccountMessage() {
        return REGISTERED_ACCOUNT;
    }

}
