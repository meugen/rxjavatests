package ua.meugen.rxjavatests.server.actions;

import play.mvc.Result;
import ua.meugen.rxjavatests.server.requests.DataWithDelayRequest;
import ua.meugen.rxjavatests.server.responses.DataWithDelayResponse;

import java.util.Random;

public class DataWithDelayAction extends AbstractControllerAction<DataWithDelayRequest, DataWithDelayResponse>
        implements ControllerAction<DataWithDelayRequest> {

    private static final Random RANDOM = new Random();

    @Override
    protected DataWithDelayResponse _execute(final DataWithDelayRequest request) {
        try {
            Thread.sleep(request.getDelay());
        } catch (InterruptedException e) {}
        return new DataWithDelayResponse(RANDOM, request.getCount());
    }
}
