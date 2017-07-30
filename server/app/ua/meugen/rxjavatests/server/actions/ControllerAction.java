package ua.meugen.rxjavatests.server.actions;

import play.mvc.Result;

import java.util.concurrent.CompletionStage;

public interface ControllerAction<R> {

    CompletionStage<Result> execute(R request);
}
