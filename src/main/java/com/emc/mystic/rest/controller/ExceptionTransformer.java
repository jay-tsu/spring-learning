package com.emc.mystic.rest.controller;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface ExceptionTransformer<Q> extends Function<List<String>, Q> {
}
