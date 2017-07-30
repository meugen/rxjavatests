package ua.meugen.rxjavatests.server.controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.mvc.Controller;
import play.mvc.Result;
import ua.meugen.rxjavatests.server.actions.ControllerAction;
import ua.meugen.rxjavatests.server.requests.DataWithDelayRequest;

import java.util.concurrent.CompletionStage;

@Singleton
public class RestController extends Controller {

    @Inject
    private ControllerAction<DataWithDelayRequest> dataWithDelayAction;

    public CompletionStage<Result> dataWithDelay(final DataWithDelayRequest request) {
        return dataWithDelayAction.execute(request);
    }
}
