package ua.meugen.rxjavatests.server.requests;

import play.mvc.QueryStringBindable;
import ua.meugen.rxjavatests.server.utils.QueryStringUtils;

import java.util.Map;
import java.util.Optional;

/**
 * Created by admin on 06.09.2017.
 */
public class StorageWithDelayRequest implements QueryStringBindable<StorageWithDelayRequest> {

    private static final String PARAM_ID = "id";
    private static final String PARAM_DELAY = "delay";

    private Integer id;
    private Long delay;

    public Integer getId() {
        return id;
    }

    public Long getDelay() {
        return delay;
    }

    @Override
    public Optional<StorageWithDelayRequest> bind(final String key, final Map<String, String[]> data) {
        id = QueryStringUtils.getSingleParam(PARAM_ID, data)
                .map(Integer::valueOf)
                .orElse(1);
        delay = QueryStringUtils.getSingleParam(PARAM_DELAY, data)
                .map(Long::valueOf)
                .orElse(0L);
        return Optional.of(this);
    }

    @Override
    public String unbind(final String key) {
        return String.join("&",
                PARAM_ID + "=" + id,
                PARAM_DELAY + "=" + delay);
    }

    @Override
    public String javascriptUnbind() {
        return null;
    }
}
