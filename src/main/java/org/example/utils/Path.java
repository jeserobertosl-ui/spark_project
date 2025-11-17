package org.example.utils;

public class Path {
    public static class Web {
        public static final String USERS_PATH = "/users";
        public static final String USERS_GET_ALL = USERS_PATH;
        public static final String USERS_GET_BY_ID = USERS_PATH + "/:id";
        public static final String USERS_POST = USERS_PATH;
        public static final String USERS_PUT = USERS_PATH;
        public static final String USERS_OPTIONS = USERS_GET_BY_ID;
        public static final String USERS_DELETE = USERS_GET_BY_ID;

        public static final String ITEMS_PATH = "/items";
        public static final String ITEMS_GET_ALL = ITEMS_PATH;
        public static final String ITEMS_GET_BY_ID = ITEMS_PATH + "/:id";
        public static final String ITEMS_POST = ITEMS_PATH;
        public static final String ITEMS_PUT = ITEMS_PATH;
        public static final String ITEMS_OPTIONS = ITEMS_GET_BY_ID;
        public static final String ITEMS_DELETE = ITEMS_GET_BY_ID;

        public static final String BID_PATH = "/bid";
        public static final String BID_GET_ALL = BID_PATH;
        public static final String BID_GET_BY_ID = BID_PATH + "/:id";
        public static final String BID_POST = BID_PATH;
        public static final String BID_PUT = BID_PATH;
        public static final String BID_OPTIONS = BID_GET_BY_ID;
        public static final String BID_DELETE = BID_GET_BY_ID;
        public static final String NEW_BID = "/new_bid/:item_id";
        public static final String POST_NEW_BID = "/post_bid/:email/:item_id/:amount";
    }

    public static class Template {
        public static final String USERS = "users.mustache";
        public static final String ITEMS = "items.mustache";
        public static final String ITEMS_DESCRIPTION = "item_description.mustache";

        public static final String NEW_BID = "new_bid.mustache";
        public static final String BIDS = "bids.mustache";
        public static final String POST_BID = "post_bid.mustache";
    }
}
