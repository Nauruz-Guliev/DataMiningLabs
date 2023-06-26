package ru.kpfu.itis.gnt.hwpebble.bloomfilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;

@Configuration
public class BloomConfiguration {


    @Bean
    public BloomFilter bloomFilter(

    ) {
        BloomFilter filter;

        List<String> words = Arrays.stream(TextHolder.text.split(" ")).map(
                        word -> word.replace(",", "")
                ).map(
                        word -> word.replace(";", "")
                ).map(
                        word -> word.replace(".", "")
                ).map(
                        word -> word.replace("!", "")
                ).map(
                        word -> word.replace("'", "")
                )
                .toList();

        filter = new BloomFilter(words.size(), 0.5);
        int i = 0;
        for (String word: words) {
            filter.setAmountOfWords(i++);
            filter.add(word);
        }
        return filter;
    }




}
