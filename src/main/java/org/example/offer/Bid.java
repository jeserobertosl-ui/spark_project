package org.example.offer;

import java.util.ArrayList;
import java.util.List;

import static org.example.Main.bids;
import static org.example.Main.users;

public class Bid {
    private int id;
    private String email;
    private int item_id;
    private float amount;

    public Bid(int id, String email, int item_id, float amount) {
        this.id = id;
        this.email = email;
        this.item_id = item_id;
        this.amount = amount;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public static List<Bid> default_values() {
        List<Bid> bids = new ArrayList<>();

        bids.add(new Bid(1, users.get(0).getEmail(), 2, 850));
        bids.add(new Bid(2, users.get(1).getEmail(), 3, 600));
        bids.add(new Bid(3, users.get(2).getEmail(), 5, 400));

        return bids;
    }

    public static int get_index_from_id(int _id) {
        for (int i = 0; i < bids.size(); i++) {
            if (bids.get(i).getId() == _id) {
                return i;
            }
        }
        return -1;
    }

    public static boolean bid_id_exists(int _id) {
        for (Bid bid : bids) {
            if (bid.getId() == _id) {
                return true;
            }
        }
        return false;
    }

    public static int generate_id() {
        return bids.getLast().getId() + 1;
    }
}
