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
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class AbstractJpaRepository<
        T extends AggregateRoot<I>, I extends AggregateRootId, D extends DomainMessageData>
    implements Repository<T, I> {

  /** The Repository. */
  protected SpringDataRepository<T, I> repository;

  /** The Domain message data repository. */
  protected SpringDomainMessageDataRepository<D> domainMessageDataRepository;

  /** The Domain message data converter. */
  protected DomainMessageDataConvertible<D> domainMessageDataConverter;

  private Class<I> idClass;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  protected AbstractJpaRepository(
      SpringDataRepository<T, I> repository,
      SpringDomainMessageDataRepository<D> domainMessageDataRepository,
      DomainMessageDataConvertible<D> domainMessageDataConverter,
      Class<I> idClass) {
    this.repository = repository;
    this.domainMessageDataRepository = domainMessageDataRepository;
    this.domainMessageDataConverter = domainMessageDataConverter;
    this.idClass = idClass;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public T store(T object) {
    T storedObject = this.repository.save(object);
    domainMessageDataRepository.saveAll(
        domainMessageDataConverter.convert(object.getDomainMessages()));
    return storedObject;
  }

  @Override
  public Optional<T> restore(I objectId) {
    return repository.findById(objectId);
  }

  @Override
  public void remove(I objectId) {
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

    Page<T> dataPage = repository.findAll(pageable);

    return new Paginated<>(
        dataPage.getContent().stream().collect(Collectors.toList()),
        dataPage.getTotalPages(),
        dataPage.getTotalElements());
  }

  @Override
  @SuppressWarnings("unchecked")
  public I nextId() {
    try {
      Constructor<?>[] allConstructors = idClass.getConstructors();
      Constructor<?> constructor = allConstructors[0];
      Object[] objects = {UuidGenerator.timeBasedUuid()};
      return (I) constructor.newInstance(objects);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
