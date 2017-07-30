package ua.meugen.rxjavatests.server.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import ua.meugen.rxjavatests.server.actions.ControllerAction;
import ua.meugen.rxjavatests.server.actions.DataWithDelayAction;
import ua.meugen.rxjavatests.server.requests.DataWithDelayRequest;

public class ActionsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<ControllerAction<DataWithDelayRequest>>() {})
                .to(DataWithDelayAction.class).in(Singleton.class);
    }
}
