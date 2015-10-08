package utils.template;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import spark.ModelAndView;
import spark.TemplateEngine;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

public class VelocityTemplateEngine extends TemplateEngine {

    private final VelocityEngine velocityEngine;

    public VelocityTemplateEngine() {
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty(
                "class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine = new org.apache.velocity.app.VelocityEngine(properties);
    }

    public VelocityTemplateEngine(VelocityEngine velocityEngine) {
        if (velocityEngine == null){
            throw new IllegalStateException("velocityEngine must not be null");
        }
        this.velocityEngine = velocityEngine;
    }

    @Override
    public String render(ModelAndView modelAndView) {
        Template template = velocityEngine.getTemplate(modelAndView.getViewName(), "UTF-8");
        Object model = modelAndView.getModel();
        if (model instanceof Map){
            Map<?, ?> modelMap = (Map<?, ?>) model;
            VelocityContext context = new VelocityContext(modelMap);
            StringWriter writer = new StringWriter();
            template.merge(context, writer);
            return writer.toString();
        }else {
            throw new IllegalArgumentException("modelAndView must be of type java.util.Map");
        }
    }
}
