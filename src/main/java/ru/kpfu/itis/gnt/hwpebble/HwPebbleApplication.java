package ru.kpfu.itis.gnt.hwpebble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kpfu.itis.gnt.hwpebble.bloomfilter.BloomFilter;
import ru.kpfu.itis.gnt.hwpebble.bloomfilter.TextHolder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HwPebbleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HwPebbleApplication.class, args);
    }


}
