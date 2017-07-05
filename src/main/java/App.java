import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/change", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Float inputDollarAmount = Float.parseFloat(request.queryParams("dollarAmount"));

      ChangeMachine myChangeMachine = new ChangeMachine();
      model.put("myChangeMachine", myChangeMachine);
      model.put("result", myChangeMachine.makeChange(inputDollarAmount));

      model.put("template", "templates/change.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
