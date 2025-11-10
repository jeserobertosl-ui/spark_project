package org.example.offer;

import org.example.utils.Path;
import spark.ModelAndView;
import spark.Route;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static org.example.Main.bids;
import static spark.Spark.*;

public class BidController {
    public static Route get_all = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("bids", bids);

        return new MustacheTemplateEngine().render(
            new ModelAndView(
                model,
                Path.Template.BIDS
            )
        );
    };

    public static Route get_by_id = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        int id = Integer.parseInt(request.params(":id"));
        int index = Bid.get_index_from_id(id);

        if (index == -1) {
            return "Bid with id: " + id + "doesn't exist";
        }

        model.put("bids", bids.get(index));

        return new MustacheTemplateEngine().render(
            new ModelAndView(
                model,
                Path.Template.BIDS
            )
        );
    };

    public static Route new_bid = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("item_id", request.params(":item_id"));

        return new MustacheTemplateEngine().render(
            new ModelAndView(
                model,
                Path.Template.NEW_BID
            )
        );
    };

    public static Route post = (request, response) ->
    {
        Map<String, Object> model = new HashMap<>();

        int id = Bid.generate_id();

        String email = request.params(":email");

        int item_id = Integer.parseInt(request.params(":item_id"));
        model.put("item_id", item_id);

        float amount = Float.parseFloat(request.params(":amount"));

        Bid bid = new Bid(id, email, item_id, amount);
        bids.add(bid);

        return new MustacheTemplateEngine().render(
            new ModelAndView(
                model,
                Path.Template.POST_BID
            )
        );
    };

    public static void set_routes() {

        get(Path.Web.BID_PATH, BidController.get_all);
        get(Path.Web.BID_GET_BY_ID, BidController.get_by_id);
        get(Path.Web.NEW_BID, BidController.new_bid);

        get(Path.Web.POST_NEW_BID, BidController.post);
    }
}
