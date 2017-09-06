package ua.meugen.rxjavatests.server.actions;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import play.Logger;
import ua.meugen.rxjavatests.server.requests.StorageWithDelayRequest;
import ua.meugen.rxjavatests.server.responses.DataResponse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 06.09.2017.
 */
public class StorageWithDelayAction extends AbstractControllerAction<StorageWithDelayRequest, DataResponse>
        implements ControllerAction<StorageWithDelayRequest> {

    private static final Random RANDOM = new Random();
    private static final int COUNT = 20;

    private final Cache<Integer, List<String>> cache = CacheBuilder
            .from("expireAfterAccess=2h").build();

    @Override
    protected DataResponse _execute(final StorageWithDelayRequest request) throws Exception {
        List<String> data = cache.get(request.getId(), this::generateData);

        final DataResponse response = new DataResponse();
        response.setData(data);

        try {
            Thread.sleep(request.getDelay());
        } catch (InterruptedException e) {}
        return response;
    }

    private List<String> generateData() {
        final List<String> data = new ArrayList<>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            data.add(new BigInteger(100, RANDOM).toString(26));
        }
        return data;
    }
}
