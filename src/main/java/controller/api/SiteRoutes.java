package controller.api;

import controller.BaseController;
import controller.logic.AdviceController;
import model.Advice;
import spark.ModelAndView;
import utils.template.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class SiteRoutes extends BaseController {

    @Override
    public void routes() {

        get("/", (request, response) -> {
            System.out.println("Выполняется index.html");
            return new ModelAndView(new HashMap(), "/public/index.html");
        }, new VelocityTemplateEngine());

        get("/hello", (request, response) -> {
            System.out.println("Выполняется /hello");
            HashMap<String, Object> model = new HashMap<>();
            model.put("name", request.queryParams("name"));
            return new ModelAndView(model, "/public/templates/hello.vtl");
        }, new VelocityTemplateEngine());

        get("/advice_form", (request, response) -> {
            System.out.println("Выполняется /advice_form");
            return new ModelAndView(new HashMap(), "/public/templates/form.vtl");
        }, new VelocityTemplateEngine());

        post("/add_advice", (request, response) -> {
            System.out.println("Выполняется /add_advice");
            HashMap<String, Object> model = new HashMap<>();
            Advice advice = new Advice(request.queryParams("text"), request.queryParams("category"));
            AdviceController adviceController = new AdviceController(advice);
            adviceController.addToDataBase();
            model.put("id", advice.getId());
            model.put("category", advice.getCategory());
            model.put("text", advice.getText());
            model.put("rating", advice.getRating());
            return new ModelAndView(model, "/public/templates/last_advice.vtl");
        }, new VelocityTemplateEngine());
    }
}
