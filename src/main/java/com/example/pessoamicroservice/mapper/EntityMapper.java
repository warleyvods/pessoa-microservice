package com.example.pessoamicroservice.mapper;

import java.util.List;

public interface EntityMapper<E, T, D> {

    E toDomain(final T requestDTO);

    D fromDomain(final E entity);

    List<D> fromDomainList(final List<E> entityList);
}
