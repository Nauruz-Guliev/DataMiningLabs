package ru.kpfu.itis.gnt.hwpebble.mapper;

public interface BaseMapper<From, To> {
    To mapTo(From model);
    From mapFrom(To model);
}
