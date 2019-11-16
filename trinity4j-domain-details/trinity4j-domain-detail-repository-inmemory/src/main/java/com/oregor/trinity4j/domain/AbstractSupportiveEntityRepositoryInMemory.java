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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Abstract supportive entity repository in memory.
 *
 * @param <I> the type parameter
 * @param <T> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractSupportiveEntityRepositoryInMemory<
        I extends SupportiveEntityId<?>, T extends SupportiveEntity<I>>
    implements SupportiveEntityRepository<I, T> {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private Map<I, T> map;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Abstract supportive entity repository in memory. */
  protected AbstractSupportiveEntityRepositoryInMemory() {
    map = new LinkedHashMap<>();
  }

  @Override
  public void add(T object) {
    map.put(object.getId(), object);
  }

  @Override
  public T findById(I id) {
    return map.get(id);
  }

  @Override
  public Set<T> findAll() {
    return map.entrySet().stream().map(p -> p.getValue()).collect(Collectors.toSet());
  }
}
