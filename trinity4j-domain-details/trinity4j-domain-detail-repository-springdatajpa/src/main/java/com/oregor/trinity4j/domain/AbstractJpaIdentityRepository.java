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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/**
 * The type Abstract jpa identity repository.
 *
 * @param <I> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractJpaIdentityRepository<I extends AbstractId<UUID>> {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private Class<I> idClass;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract jpa identity repository.
   *
   * @param idClass the id class
   */
  protected AbstractJpaIdentityRepository(Class<I> idClass) {
    this.idClass = idClass;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  /**
   * Next id.
   *
   * @return the
   */
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
