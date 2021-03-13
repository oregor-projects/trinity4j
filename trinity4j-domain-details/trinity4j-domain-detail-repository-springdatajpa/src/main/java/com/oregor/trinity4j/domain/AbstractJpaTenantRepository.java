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
 * The type Abstract jpa tenant repository.
 *
 * @param <T> the type parameter
 * @param <I> the type parameter
 * @param <D> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractJpaTenantRepository<
        T extends TenantAggregateRoot<I>,
        I extends AbstractAggregateRootId,
        D extends DomainMessageData>
    extends AbstractJpaIdentityRepository<I> implements TenantRepository<T, I> {

  // ===============================================================================================
  // STATIC
  // ===============================================================================================

  private static final String TENANT_ID_IS_REQUIRED = "Tenant Id is required";
  private static final String AGGREGATE_ROOT_IS_REQUIRED = "Aggregate Root is required";
  private static final String AGGREGATE_ROOT_ID_IS_REQUIRED = "Aggregate Root Id is required";
  private static final String DIFFERENT_TENANT_IDS = "Forbidden (Invalid Tenant)";
  private static final String PAGE_NUMBER_IS_REQUIRED = "pageNumber is required";
  private static final String PAGE_SIZE_IS_REQUIRED = "pageSize is required";

  // ===============================================================================================
  // DEPENDENCIES
  // ===============================================================================================

  /** The Spring Data Tenant Repository. */
  protected SpringDataTenantRepository<T, I> springDataTenantRepository;

  /** The Domain message data repository. */
  protected SpringDataGenericRepository<D, UUID> domainMessageDataRepository;

  /** The Domain message data converter. */
  protected DomainMessageDataConverter<D> domainMessageDataConverter;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract jpa tenant repository.
   *
   * @param idClass the id class
   * @param springDataTenantRepository the spring data tenant repository
   * @param domainMessageDataRepository the domain message data repository
   * @param domainMessageDataConverter the domain message data converter
   */
  protected AbstractJpaTenantRepository(
      Class<I> idClass,
      SpringDataTenantRepository<T, I> springDataTenantRepository,
      SpringDataGenericRepository<D, UUID> domainMessageDataRepository,
      DomainMessageDataConverter<D> domainMessageDataConverter) {
    super(idClass);
    this.springDataTenantRepository = springDataTenantRepository;
    this.domainMessageDataRepository = domainMessageDataRepository;
    this.domainMessageDataConverter = domainMessageDataConverter;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public T store(T object) {
    Assertion.isNotNull(object, AGGREGATE_ROOT_IS_REQUIRED);

    T storedObject = this.springDataTenantRepository.save(object);
    domainMessageDataRepository.saveAll(
        domainMessageDataConverter.convert(object.getDomainMessages()));
    return storedObject;
  }

  @Override
  public T store(TenantId tenantId, T object) {
    Assertion.isNotNull(tenantId, TENANT_ID_IS_REQUIRED);
    Assertion.isNotNull(object, AGGREGATE_ROOT_IS_REQUIRED);

    if (!tenantId.equals(object.getTenantId())) {
      throw new DomainException(DIFFERENT_TENANT_IDS);
    }

    T storedObject = this.springDataTenantRepository.save(object);
    domainMessageDataRepository.saveAll(
        domainMessageDataConverter.convert(object.getDomainMessages()));
    return storedObject;
  }

  @Override
  public Optional<T> restore(I objectId) {
    Assertion.isNotNull(objectId, AGGREGATE_ROOT_IS_REQUIRED);

    return springDataTenantRepository.findById(objectId);
  }

  @Override
  public Optional<T> restore(TenantId tenantId, I objectId) {
    Assertion.isNotNull(tenantId, TENANT_ID_IS_REQUIRED);
    Assertion.isNotNull(objectId, AGGREGATE_ROOT_ID_IS_REQUIRED);

    Optional<T> optionalObject = springDataTenantRepository.findById(objectId);

    if (optionalObject.isPresent() && !tenantId.equals(optionalObject.get().getTenantId())) {
      throw new DomainException(DIFFERENT_TENANT_IDS);
    }

    return optionalObject;
  }

  @Override
  public void remove(I objectId) {
    Assertion.isNotNull(objectId, AGGREGATE_ROOT_ID_IS_REQUIRED);

    springDataTenantRepository.deleteById(objectId);
  }

  @Override
  public void remove(TenantId tenantId, T object) {
    Assertion.isNotNull(tenantId, TENANT_ID_IS_REQUIRED);
    Assertion.isNotNull(object, AGGREGATE_ROOT_IS_REQUIRED);

    Optional<T> optionalObject = springDataTenantRepository.findById(object.getId());

    if (optionalObject.isPresent() && !tenantId.equals(optionalObject.get().getTenantId())) {
      throw new DomainException(DIFFERENT_TENANT_IDS);
    }

    springDataTenantRepository.deleteById(object.getId());
  }

  @Override
  public Iterable<T> findAll(TenantId tenantId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterable<T> findAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Paginated<T> findPaginated(TenantId tenantId, Integer pageNumber, Integer pageSize) {
    Assertion.isNotNull(tenantId, "tenantId is required");
    Assertion.isNotNull(pageNumber, "pageNumber is required");
    Assertion.isNotNull(pageSize, "pageSize is required");

    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<T> dataPage = springDataTenantRepository.findByTenantId(tenantId, pageable);

    return new Paginated<>(
        dataPage.getContent().stream().collect(Collectors.toList()),
        dataPage.getTotalPages(),
        dataPage.getTotalElements());
  }

  @Override
  public Paginated<T> findPaginated(Integer pageNumber, Integer pageSize) {
    Assertion.isNotNull(pageNumber, PAGE_NUMBER_IS_REQUIRED);
    Assertion.isNotNull(pageSize, PAGE_SIZE_IS_REQUIRED);

    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<T> dataPage = springDataTenantRepository.findAll(pageable);

    return new Paginated<>(
        dataPage.getContent().stream().collect(Collectors.toList()),
        dataPage.getTotalPages(),
        dataPage.getTotalElements());
  }
}
