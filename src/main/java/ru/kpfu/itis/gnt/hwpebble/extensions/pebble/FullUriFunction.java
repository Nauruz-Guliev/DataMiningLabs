package ru.kpfu.itis.gnt.hwpebble.extensions.pebble;


import io.pebbletemplates.pebble.extension.AbstractExtension;
import io.pebbletemplates.pebble.extension.Function;
import io.pebbletemplates.pebble.template.EvaluationContext;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class FullUriFunction  implements Function {

    private final ServletContext servletContext;

    @Override
    public List<String> getArgumentNames() {
        return List.of("uri");
    }

    @Override
    public Object execute(Map<String, Object> args, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) {
        String input = (String)args.get("uri");
        StringBuilder fullUri = new StringBuilder(input);
        fullUri.insert(0, servletContext.getContextPath());
        return fullUri.toString();
    }
}
