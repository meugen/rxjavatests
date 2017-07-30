package ua.meugen.rxjavatests.server.actions;

import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

abstract class AbstractControllerAction<Req, Resp> extends Results {

    public CompletionStage<Result> execute(final Req request) {
        return CompletableFuture.supplyAsync(() -> executeResult(request));
    }

    private Result executeResult(final Req request) {
        return ok(Json.toJson(_execute(request)));
    }

    protected abstract Resp _execute(Req request);
}
