package com.cemgunduz.payconiq.configuration;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cem on 27/12/17.
 *
 * Contains the special model mapper configuration that enables the current behaviour of the update method
 * That is to skip the null input instead of also mapping and persisting the null changes
 */
@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        return modelMapper;
    }
}
