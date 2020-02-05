package org.henu.blastfurnace.master.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
public class SlaveCallerConfig {
    private Environment env;
    @Autowired
    public SlaveCallerConfig(Environment env) {
        this.env = env;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SlaveCaller slaveCaller(){
        return new SlaveCaller(Objects.requireNonNull(env.getProperty("slave.hosts", String[].class)));
    }
}
