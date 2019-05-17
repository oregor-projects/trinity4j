/*-
 * ==========================LICENSE_START=================================
 * TRINITY4J: A set of Domain-Driven Design Libraries for Java Applications
 * ========================================================================
 * Copyright (C) 2019 Christos Tsakostas, OREGOR LTD
 * ========================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ===========================LICENSE_END==================================
 */

package com.oregor.trinity4j.domain;

import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * The type Abstract jpa projection repository.
 *
 * @param <O> the type parameter
 * @param <I> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractJpaProjectionRepository<
        O extends Projection<I>, I extends ProjectionId>
    extends AbstractJpaIdentityRepository<I> implements ProjectionRepository<O, I> {

  /** The Spring Data Repository. */
  protected ProjectionSpringDataRepository<O, I> springDataRepository;

  /**
   * Instantiates a new Abstract jpa projection repository.
   *
   * @param idClass the id class
   * @param springDataRepository the spring data repository
   */
  public AbstractJpaProjectionRepository(
      Class<I> idClass, ProjectionSpringDataRepository<O, I> springDataRepository) {
    super(idClass);
    this.springDataRepository = springDataRepository;
  }

  @Override
  public O store(O object) {
    O storedObject = this.springDataRepository.save(object);
    return storedObject;
  }

  @Override
  public Optional<O> restore(I objectId) {
    return springDataRepository.findById(objectId);
  }

  @Override
  public void remove(I objectId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterable<O> findAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Paginated<O> findPaginated(Integer pageNumber, Integer pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<O> dataPage = springDataRepository.findAll(pageable);

    return new Paginated<>(
        dataPage.getContent().stream().collect(Collectors.toList()),
        dataPage.getTotalPages(),
        dataPage.getTotalElements());
  }
}
