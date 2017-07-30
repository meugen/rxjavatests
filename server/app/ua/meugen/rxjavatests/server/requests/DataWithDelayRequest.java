package ua.meugen.rxjavatests.server.requests;

import play.mvc.QueryStringBindable;
import ua.meugen.rxjavatests.server.utils.QueryStringUtils;

import java.util.Map;
import java.util.Optional;

public class DataWithDelayRequest implements QueryStringBindable<DataWithDelayRequest> {

    private static final String PARAM_DELAY = "delay";
    private static final String PARAM_COUNT = "count";

    private static final Long DEFAULT_DELAY = 0L;
    private static final Integer DEFAULT_COUNT = 10;

    private long delay;
    private int count;

    public long getDelay() {
        return delay;
    }

    public int getCount() {
        return count;
    }

    @Override
    public Optional<DataWithDelayRequest> bind(final String key, final Map<String, String[]> data) {
        this.delay = QueryStringUtils.getSingleParam(PARAM_DELAY, data)
                .map(Long::valueOf).orElse(DEFAULT_DELAY);
        this.count = QueryStringUtils.getSingleParam(PARAM_COUNT, data)
                .map(Integer::valueOf).orElse(DEFAULT_COUNT);
        return Optional.of(this);
    }

    @Override
    public String unbind(final String key) {
        return String.join("&",
                PARAM_DELAY + "=" + delay,
                PARAM_COUNT + "=" + count);
    }

    @Override
    public String javascriptUnbind() {
        return null;
    }
}
