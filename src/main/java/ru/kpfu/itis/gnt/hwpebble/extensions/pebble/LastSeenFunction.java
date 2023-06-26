package ru.kpfu.itis.gnt.hwpebble.extensions.pebble;

import io.pebbletemplates.pebble.extension.Function;
import io.pebbletemplates.pebble.template.EvaluationContext;
import io.pebbletemplates.pebble.template.PebbleTemplate;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class LastSeenFunction implements Function {

    @Override
    public Object execute(Map<String, Object> args, PebbleTemplate self, EvaluationContext context, int lineNumber) {
        try {
            Long currentTime = Calendar.getInstance().getTimeInMillis();
            Long time = (Long) args.get("date");
            if (currentTime - time < 60000) {
                return "Created recently";
            } else if (currentTime - time < (60000 * 2)) {
                return "Created 1 minute ago";
            } else if (currentTime - time < (60000 * 10)) {
                return "Created several minutes ago";
            } else {
                return new Date(time);
            }
        } catch (Exception ex) {
            return "Unable to parse date. It should be in milliseconds";
        }
    }

    @Override
    public List<String> getArgumentNames() {
        return List.of("date");
    }
}
