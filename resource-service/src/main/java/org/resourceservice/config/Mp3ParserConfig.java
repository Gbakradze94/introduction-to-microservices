package org.resourceservice.config;

import org.apache.tika.sax.BodyContentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mp3ParserConfig {
    @Bean
    public BodyContentHandler bodyContentHandler() {
        return new BodyContentHandler();
    }

}
