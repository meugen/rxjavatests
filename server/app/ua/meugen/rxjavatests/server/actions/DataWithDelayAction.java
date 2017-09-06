package ua.meugen.rxjavatests.server.actions;

import ua.meugen.rxjavatests.server.requests.DataWithDelayRequest;
import ua.meugen.rxjavatests.server.responses.DataResponse;

import java.util.Random;

public class DataWithDelayAction extends AbstractControllerAction<DataWithDelayRequest, DataResponse>
        implements ControllerAction<DataWithDelayRequest> {

    private static final Random RANDOM = new Random();

    @Override
    protected DataResponse _execute(final DataWithDelayRequest request) throws Exception {
        try {
            Thread.sleep(request.getDelay());
        } catch (InterruptedException e) {}
        return new DataResponse(RANDOM, request.getCount());
    }
}
