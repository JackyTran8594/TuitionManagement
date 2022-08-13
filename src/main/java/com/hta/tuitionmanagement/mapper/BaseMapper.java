package com.hta.tuitionmanagement.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.ArrayList;
import java.util.List;

public class BaseMapper<Model, DTO> {
    private static final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private MapperFacade mapperFacade;
    private Class<Model> model;
    private Class<DTO> dto;

    public BaseMapper(Class<Model> model, Class<DTO> dto) {
        mapperFactory.classMap(model, dto).constructorA().constructorB().byDefault().register();
        mapperFacade = mapperFactory.getMapperFacade();
        this.dto = dto;
        this.model = model;
    }

    public BaseMapper() {
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public DTO toDtoBean(Model _model) {
        if (_model == null) {
            return null;
        }
        return mapperFacade.map(_model, dto);
    }

    public Model toPersistenceBean(DTO _dto) {
        return mapperFacade.map(_dto, model);
    }

    public List<DTO> toDtoBean(Iterable<Model> models) {
        List<DTO> dtoBeans = new ArrayList<>();
        if (models == null) {
            return dtoBeans;
        }
        for(Model model: models) {
            dtoBeans.add((toDtoBean(model)));
        }
        return dtoBeans;
    }

    public List<Model> toPersistenceBean(Iterable<DTO> dtoBeans) {
        List<Model> models = new ArrayList<>();
        if (dtoBeans == null) {
            return models;
        }
        for(DTO dto: dtoBeans) {
            models.add((toPersistenceBean(dto)));
        }
        return models;
    }


}
