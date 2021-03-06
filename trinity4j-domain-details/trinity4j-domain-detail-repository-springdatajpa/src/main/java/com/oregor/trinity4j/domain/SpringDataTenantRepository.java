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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The interface Spring data tenant repository.
 *
 * @param <T> the type parameter
 * @param <I> the type parameter
 * @author Christos Tsakostas
 */
@NoRepositoryBean
public interface SpringDataTenantRepository<
        T extends AbstractAggregateRoot<I>, I extends AbstractAggregateRootId>
    extends PagingAndSortingRepository<T, I> {

  Optional<T> findById(I id);

  /**
   * Find by id tenant id page.
   *
   * @param tenantId the tenant id
   * @param pageable the pageable
   * @return the page
   */
  Page<T> findByTenantId(TenantId tenantId, Pageable pageable);
}
