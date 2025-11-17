package org.example.item;

import com.google.gson.Gson;
import org.example.offer.Bid;
import org.example.utils.Path;
import org.example.web_sockets.ItemWebSocket;
import spark.ModelAndView;
import spark.Route;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.Main.bids;
import static org.example.Main.items;
import static spark.Spark.*;

public class ItemController {

    public static Route get_all = ((request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("items", items);

        return new MustacheTemplateEngine().render(
            new ModelAndView(
                model,
                Path.Template.ITEMS
            )
        );
    });

    public static Route get_by_id = ((request, response) -> {
        Map<String, Object> model = new HashMap<>();

        int id = Integer.parseInt(request.params(":id"));
        int index = Item.get_index_from_item_id(items, id);

        if (index == -1) {
            return "Item with id: " + id + " doesn't exist";
        }

        Item item = items.get(index);

        model.put("id", item.getId());
        model.put("name", item.getName());
        model.put("description", item.getDescription());
        model.put("price", item.getPrice());

        List<Bid> bids_for_item = new ArrayList<>();

        for (var bid : bids) {
            if (bid.getItem_id() == id) {
                bids_for_item.add(bid);
            }
        }

        model.put("bids", bids_for_item);

        return new MustacheTemplateEngine().render(
            new ModelAndView(
                model,
                Path.Template.ITEMS_DESCRIPTION
            )
        );
    });

    public static Route post = ((request, response) -> {
        String body = request.body();
        Item item = new Gson().fromJson(body, Item.class);

        item.setId(
            items.getLast().getId() + 1
        );

        if (Item.item_id_exists(items, item.getId())) {
            return "Item already exists";
        }

        items.add(item);

        Map<String, Object> model = new HashMap<>();
        model.put("items", item);

        ItemWebSocket.broadcast_new_item(item);

        return new MustacheTemplateEngine().render(
            new ModelAndView(
                model,
                Path.Template.ITEMS
            )
        );
    });

    public static Route put = ((request, response) -> {
        String body = request.body();
        Item item = new Gson().fromJson(body, Item.class);

        int item_index = Item.get_index_from_item_id(items, item.getId());

        if (item_index == -1)
        {
            return "Item with id: " + item.getId() + " doesn't exist";
        }

        items.set(item_index, item);

        Map<String, Object> model = new HashMap<>();
        model.put("item", item);

        return new MustacheTemplateEngine().render(
                new ModelAndView(
                        model,
                        Path.Template.ITEMS
                )
        );
    });

    public static Route options = ((request, response) -> {
        return Item.item_id_exists(
                items,
                Integer.parseInt(
                        request.params(":id")
                )
        );
    });

    public static Route delete = ((request, response) -> {
        int id = Integer.parseInt(request.params(":id"));
        int index = Item.get_index_from_item_id(items, id);

        if (index == -1) {
            return "Deletion failed, no item with id: " + id;
        }

        items.remove(index);

        return "Item deleted";
    });

    public static void set_routes() {
        get(Path.Web.ITEMS_GET_ALL, ItemController.get_all);
        get(Path.Web.ITEMS_GET_BY_ID, ItemController.get_by_id);

        post(Path.Web.ITEMS_POST, ItemController.post);

        put(Path.Web.ITEMS_PUT, ItemController.put);

        options(Path.Web.ITEMS_OPTIONS, ItemController.options);

        delete(Path.Web.ITEMS_DELETE, ItemController.delete);
    }
}
