package org.example;

import static spark.Spark.*;

import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<User> users = User.defaul_values();
        port(8080);
        initExceptionHandler(e -> System.out.println("Uh-oh"));
        init();

        get("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("users", users);

            return new MustacheTemplateEngine().render(
                    new ModelAndView(
                            model,
                            "users.mustache"
                    )
            );
        });

        get("/users/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("users", users.get(Integer.getInteger(request.params(":id"))));

            return new MustacheTemplateEngine().render(
                    new ModelAndView(
                            model,
                            "users.mustache"
                    )
            );
        });

        post("/users", (request, response) -> {

            String body = request.body();
            User user = new Gson().fromJson(body, User.class);

            for (var temp_user : users)
            {
                if (temp_user.getId() == user.getId()) {
                    return "User already exists";
                }
            }

            users.add(user);

            Map<String, Object> model = new HashMap<>();
            model.put("users", user);

            return new MustacheTemplateEngine().render(
                    new ModelAndView(
                            model,
                            "users.mustache"
                    )
            );
        });

        put("/users", (request, response) -> {
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
                            "users.mustache"
                    )
            );
        });

        options("/users/:id", (request, response) -> {
            return User.get_index_from_user_id(
                    users,
                    Integer.parseInt(
                            request.params(":id")
                    )
            ) != -1;
        });

        delete("users/:id", (request, response) -> {
            int user_id = Integer.parseInt(request.params(":id"));

            if (User.get_index_from_user_id(
                    users,
                    user_id
            ) == -1)
            {
                return "Deletion failed, no user with id: " + user_id;
            }

            return "User deleted";
        });

        System.out.println("Application started");
    }
}