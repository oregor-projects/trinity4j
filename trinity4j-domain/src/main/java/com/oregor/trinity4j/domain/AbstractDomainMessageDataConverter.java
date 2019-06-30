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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oregor.trinity4j.commons.assertion.Assertion;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Abstract domain message data converter.
 *
 * @param <D> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractDomainMessageDataConverter<D extends DomainMessageData>
    implements DomainMessageDataConverter<D> {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private ObjectMapper objectMapper;
  private Class<D> clazz;
  private Constructor<?> constructor;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract domain message data converter.
   *
   * @param objectMapper the object mapper
   */
  @SuppressWarnings("unchecked")
  protected AbstractDomainMessageDataConverter(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    clazz = getTypeParameterClass();
    Constructor<?>[] allConstructors = clazz.getConstructors();
    constructor = allConstructors[0];
  }

  // ===============================================================================================
  // ABSTRACT
  // ===============================================================================================

  /**
   * Gets context.
   *
   * @return the context
   */
  protected abstract String getContext();

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @SuppressWarnings("unchecked")
  @Override
  public D convert(DomainMessage domainMessage) {
    Assertion.isNotNull(domainMessage, "domainMessage is required");
    Assertion.isNotNull(domainMessage.getRootId(), "domainMessage.getUuid() is required");
    Assertion.isNotNull(domainMessage.getRootId(), "domainMessage.getTenantId() is required");

    try {
      Object[] objects = {
        UuidGenerator.timeBasedUuid(),
        domainMessage.getOccurredOn(),
        domainMessage.getRootId(),
        1,
        domainMessage.getMessageType(),
        domainMessage.getMessageName(),
        domainMessage.getMessageVersion(),
        objectMapper.writeValueAsString(domainMessage),
        UuidGenerator.timeBasedUuid(),
        "ipAddress",
      };
      return (D) constructor.newInstance(objects);
    } catch (JsonProcessingException
        | InstantiationException
        | IllegalAccessException
        | InvocationTargetException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  @Override
  public Iterable<D> convert(List<DomainMessage> domainMessages) {
    return domainMessages.stream().map(this::convert).collect(Collectors.toList());
  }

  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================

  @SuppressWarnings("unchecked")
  private Class<D> getTypeParameterClass() {
    Type type = getClass().getGenericSuperclass();
    if (type instanceof ParameterizedType) {
      ParameterizedType paramType = (ParameterizedType) type;
      return (Class<D>) paramType.getActualTypeArguments()[0];
    } else {
      throw new IllegalStateException(
          "Type type = getClass().getGenericSuperclass(); is not a ParameterizedType");
    }
  }
}
