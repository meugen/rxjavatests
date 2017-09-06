package ua.meugen.rxjavatests.server.actions;

import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

abstract class AbstractControllerAction<Req, Resp> extends Results {

    private static final Logger.ALogger LOGGER = Logger.of(AbstractControllerAction.class);

    public CompletionStage<Result> execute(final Req request) {
        return CompletableFuture.supplyAsync(() -> executeResult(request));
    }

    private Result executeResult(final Req request) {
        try {
            return ok(Json.toJson(_execute(request)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return internalServerError(e.getMessage());
        }
    }

    protected abstract Resp _execute(Req request) throws Exception;
}
