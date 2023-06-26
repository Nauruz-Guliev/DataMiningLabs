package ru.kpfu.itis.gnt.hwpebble.constants;

public class ErrorMessageConstants {


    public static final String DELETION_ERROR = "Unable to delete due to internal server error";
    public static final String CREATION_ERROR = "Unable to create due to internal server error";
    public static final String UPDATE_ERROR = "Unable to update due to internal server error";
    public static final String GET_LIST_ERROR = "Unable to retrieve the data due to internal server error";



    public static String getPostNotFoundMessage(String postId) {
        return "Post with id " + postId + "was not found";
    }
}
