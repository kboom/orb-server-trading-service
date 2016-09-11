package com.kbhit.orangebox.trading.config;

import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@ComponentScan(basePackages = {
        "com.kbhit.orangebox.trading.controllers.dto.converters",
        "com.kbhit.orangebox.trading.controllers.dto.mappers"
})
public class MappingConfiguration {


    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper dozerBean(
            Collection<CustomConverter> customConverters,
            Collection<BeanMappingBuilder> mappingBuilders) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setCustomConverters(newArrayList(customConverters));
        mappingBuilders.forEach(mapper::addMapping);
        return mapper;
    }

}
