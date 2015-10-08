package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BaseController;
import controller.logic.AdviceController;
import model.Advice;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.get;

public class MobileRoutes  extends BaseController{

    @Override
    public void routes() {
        get("/test", (request, response) -> "test is ok");

        get("/get_advices", (request, response) -> {
            List<Advice> advices = new ArrayList<>();
            AdviceController adviceController = new AdviceController();

            advices = adviceController.getFromDataBase();

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            return gson.toJson(advices);
        });
    }
}
