package org.example;

import static spark.Spark.*;

import org.example.item.Item;
import org.example.item.ItemController;
import org.example.offer.Bid;
import org.example.offer.BidController;
import org.example.user.User;
import org.example.web_sockets.ItemWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static List<User> users = User.defaul_values();
    public static List<Item> items = Item.default_values();
    public static List<Bid> bids = Bid.default_values();

    public static void main(String[] args) {
        port(8080);
        webSocket("/ws/bid", ItemWebSocket.class);

        initExceptionHandler(e -> System.out.println("Uh-oh"));
        init();
        ItemController.set_routes();
        BidController.set_routes();

        System.out.println("Application started");
    }
}