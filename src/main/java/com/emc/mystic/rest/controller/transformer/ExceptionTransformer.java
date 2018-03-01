package com.emc.mystic.rest.controller.transformer;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionTransformer<Q> extends Function<String, Q> {
}
