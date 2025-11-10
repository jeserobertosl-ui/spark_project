package org.example.user;

import com.google.gson.Gson;
import org.example.utils.Path;
import spark.ModelAndView;
import spark.Route;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static org.example.Main.users;

public class UserController {
    public static Route get_all = ((request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("users", users);

        return new MustacheTemplateEngine().render(
                new ModelAndView(
                        model,
                        Path.Template.USERS
                )
        );
    });

    public static Route get_by_id = ((request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("users", users.get(Integer.getInteger(request.params(":id"))));

        return new MustacheTemplateEngine().render(
                new ModelAndView(
                        model,
                        Path.Template.USERS
                )
        );
    });

    public static Route post = ((request, response) -> {
        String body = request.body();
        User user = new Gson().fromJson(body, User.class);

        if (User.user_id_exists(users, user.getId())) {
            return "User already exists";
        }

        users.add(user);

        Map<String, Object> model = new HashMap<>();
        model.put("users", user);

        return new MustacheTemplateEngine().render(
                new ModelAndView(
                        model,
                        Path.Template.USERS
                )
        );
    });

    public static Route put = ((request, response) -> {
        String body = request.body();
        User user = new Gson().fromJson(body, User.class);

        int user_index = User.get_index_from_user_id(users, user.getId());

        if (user_index == -1)
        {
            return "User with id: " + user.getId() + " doesn't exist";
        }

        users.set(user_index, user);

        Map<String, Object> model = new HashMap<>();
        model.put("users", user);

        return new MustacheTemplateEngine().render(
                new ModelAndView(
                        model,
                        Path.Template.USERS
                )
        );
    });

    public static Route options = ((request, response) -> {
        return User.user_id_exists(
                users,
                Integer.parseInt(
                        request.params(":id")
                )
        );
    });

    public static Route delete = ((request, response) -> {
        int user_id = Integer.parseInt(request.params(":id"));

        if (User.user_id_exists(users, user_id))
        {
            return "Deletion failed, no user with id: " + user_id;
        }

        return "User deleted";
    });
}
