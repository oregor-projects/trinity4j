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
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public abstract class AbstractJpaTenantRepository<
        T extends TenantAggregateRoot<I>,
        I extends TenantAggregateRootId,
        D extends DomainMessageData>
    implements TenantRepository<T, I> {

  /** The Repository. */
  protected SpringDataTenantRepository<T, I> multiTenantRepository;

  /** The Domain message data repository. */
  protected SpringDomainMessageDataRepository<D> domainMessageDataRepository;

  /** The Domain message data converter. */
  protected DomainMessageDataConvertible<D> domainMessageDataConverter;

  private Class<I> idClass;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  protected AbstractJpaTenantRepository(
      SpringDataTenantRepository<T, I> repository,
      SpringDomainMessageDataRepository<D> domainMessageDataRepository,
      DomainMessageDataConvertible<D> domainMessageDataConverter,
      Class<I> idClass) {
    this.multiTenantRepository = repository;
    this.domainMessageDataRepository = domainMessageDataRepository;
    this.domainMessageDataConverter = domainMessageDataConverter;
    this.idClass = idClass;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public T store(T object) {
    T storedObject = this.multiTenantRepository.save(object);
    domainMessageDataRepository.saveAll(
        domainMessageDataConverter.convert(object.getDomainMessages()));
    return storedObject;
  }

  @Override
  public Optional<T> restore(I objectId) {
    return multiTenantRepository.findById(objectId);
  }

  @Override
  public void remove(I objectId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterable<T> findAll(UUID tenantUuid) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Paginated<T> findPaginated(UUID tenantId, Integer pageNumber, Integer pageSize) {
    Assertion.isNotNull(tenantId, "tenantId is required");
    Assertion.isNotNull(pageNumber, "pageNumber is required");
    Assertion.isNotNull(pageSize, "pageSize is required");

    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<T> dataPage = multiTenantRepository.findById_TenantId(tenantId, pageable);

    return new Paginated<>(
        dataPage.getContent().stream().collect(Collectors.toList()),
        dataPage.getTotalPages(),
        dataPage.getTotalElements());
  }

  @Override
  @SuppressWarnings("unchecked")
  public I nextId(UUID tenantUuid) {
    try {
      Constructor<?>[] allConstructors = idClass.getConstructors();
      Constructor<?> constructor = allConstructors[0];
      Object[] objects = {UuidGenerator.timeBasedUuid(), tenantUuid};
      return (I) constructor.newInstance(objects);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
