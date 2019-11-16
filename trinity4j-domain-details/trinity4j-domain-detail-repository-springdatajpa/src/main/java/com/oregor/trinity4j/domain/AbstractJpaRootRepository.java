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

import com.oregor.trinity4j.commons.assertion.Assertion;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * The type Abstract jpa root repository.
 *
 * @param <T> the type parameter
 * @param <I> the type parameter
 * @param <D> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractJpaRootRepository<
        T extends AggregateRoot<I>, I extends AggregateRootId, D extends DomainMessageData>
    extends AbstractJpaIdentityRepository<I> implements Repository<T, I> {

  /** The Spring Data Repository. */
  protected SpringDataRootRepository<T, I> springDataRootRepository;

  /** The Spring domain message data repository. */
  protected SpringDataGenericRepository<D, UUID> springDomainMessageDataRepository;

  /** The Domain message data converter. */
  protected DomainMessageDataConverter<D> domainMessageDataConverter;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract jpa root repository.
   *
   * @param idClass the id class
   * @param springDataRootRepository the spring data repository
   * @param springDomainMessageDataRepository the spring domain message data repository
   * @param domainMessageDataConverter the domain message data converter
   */
  protected AbstractJpaRootRepository(
      Class<I> idClass,
      SpringDataRootRepository<T, I> springDataRootRepository,
      SpringDataGenericRepository<D, UUID> springDomainMessageDataRepository,
      DomainMessageDataConverter<D> domainMessageDataConverter) {
    super(idClass);
    this.springDataRootRepository = springDataRootRepository;
    this.springDomainMessageDataRepository = springDomainMessageDataRepository;
    this.domainMessageDataConverter = domainMessageDataConverter;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public T store(T object) {
    Assertion.isNotNull(object.getId(), "Aggregate Root Id is required");

    T storedObject = this.springDataRootRepository.save(object);
    springDomainMessageDataRepository.saveAll(
        domainMessageDataConverter.convert(object.getDomainMessages()));
    return storedObject;
  }

  @Override
  public Optional<T> restore(I objectId) {
    Assertion.isNotNull(objectId, "Aggregate Root Id is required");

    return springDataRootRepository.findById(objectId);
  }

  @Override
  public void remove(I objectId) {
    Assertion.isNotNull(objectId, "Aggregate Root Id is required");

    throw new UnsupportedOperationException();
  }

  @Override
  public Iterable<T> findAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Paginated<T> findPaginated(Integer pageNumber, Integer pageSize) {
    Assertion.isNotNull(pageNumber, "pageNumber is required");
    Assertion.isNotNull(pageSize, "pageSize is required");

    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<T> dataPage = springDataRootRepository.findAll(pageable);

    return new Paginated<>(
        dataPage.getContent().stream().collect(Collectors.toList()),
        dataPage.getTotalPages(),
        dataPage.getTotalElements());
  }
}
