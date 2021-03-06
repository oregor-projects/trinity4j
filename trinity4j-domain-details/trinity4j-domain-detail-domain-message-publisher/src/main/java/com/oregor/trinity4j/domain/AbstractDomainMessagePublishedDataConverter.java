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
import java.time.LocalDateTime;

/**
 * The type Abstract domain message published data converter.
 *
 * @param <T> the type parameter
 * @author Christos Tsakostas
 */
public class AbstractDomainMessagePublishedDataConverter<T extends DomainMessagePublishedData>
    implements DomainMessagePublishedDataConverter<T> {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private final Constructor<?> constructor;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract domain message published data converter.
   *
   * @param clazz the clazz
   */
  protected AbstractDomainMessagePublishedDataConverter(Class<T> clazz) {
    Constructor<?>[] allConstructors = clazz.getConstructors();
    constructor = allConstructors[0];
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @SuppressWarnings("unchecked")
  @Override
  public T convert(DomainMessageData domainMessageData) {
    Assertion.isNotNull(domainMessageData, "domainMessageData is required");

    try {
      Object[] objects = {
        domainMessageData.getMessageId(),
        domainMessageData.getOccurredOn(),
        domainMessageData.getRootId(),
        domainMessageData.getRootVersion(),
        DomainMessageType.valueOf(domainMessageData.getMessageType()),
        domainMessageData.getMessageName(),
        domainMessageData.getMessageVersion(),
        domainMessageData.getMessageBody(),
        domainMessageData.getUserId(),
        domainMessageData.getIpAddress(),
        LocalDateTime.now()
      };
      return (T) constructor.newInstance(objects);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
