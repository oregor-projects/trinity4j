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
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * The type Abstract jpa uuid repository.
 *
 * @param <T> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractJpaUuidRepository<T> implements GenericRepository<T, UUID> {

  // ===============================================================================================
  // DEPENDENCIES
  // ===============================================================================================

  private SpringDataGenericRepository<T, UUID> springDataGenericRepository;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract jpa uuid repository.
   *
   * @param springDataGenericRepository the spring data generic repository
   */
  public AbstractJpaUuidRepository(
      SpringDataGenericRepository<T, UUID> springDataGenericRepository) {
    this.springDataGenericRepository = springDataGenericRepository;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public Iterable<T> findAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Paginated<T> findPaginated(Integer pageNumber, Integer pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<T> dataPage = springDataGenericRepository.findAll(pageable);

    return new Paginated<>(
        dataPage.getContent().stream().collect(Collectors.toList()),
        dataPage.getTotalPages(),
        dataPage.getTotalElements());
  }

  @Override
  public UUID nextId() {
    return UuidGenerator.timeBasedUuid();
  }

  @Override
  public T store(T entity) {
    return springDataGenericRepository.save(entity);
  }

  @Override
  public Optional<T> restore(UUID entityId) {
    return springDataGenericRepository.findById(entityId);
  }

  @Override
  public void remove(UUID entityId) {
    springDataGenericRepository.deleteById(entityId);
  }
}
