package ua.meugen.rxjavatests.server.utils;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class QueryStringUtils {

    private QueryStringUtils() {}

    public static Optional<String> getSingleParam(
            final String name,
            final Map<String, String[]> params) {
        return Stream.of(params)
                .map(p -> p.get(name))
                .filter(Objects::nonNull)
                .map(v -> v[0]).findFirst();
    }
}
