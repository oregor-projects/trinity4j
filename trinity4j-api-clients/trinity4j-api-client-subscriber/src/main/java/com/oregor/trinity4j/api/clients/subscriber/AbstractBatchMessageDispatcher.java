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

package com.oregor.trinity4j.api.clients.subscriber;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oregor.trinity4j.commons.messaging.publisher.MessagePublisher;
import java.io.IOException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

/**
 * The type Abstract batch message dispatcher.
 *
 * @param <S> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractBatchMessageDispatcher<S extends MessageSubscriber>
    implements MessageDispatcher {

  // ===============================================================================================
  // STATE / DEPENDENCIES
  // ===============================================================================================

  private final ObjectMapper objectMapper;
  private final MessagePublisher messagePublisher;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract batch message dispatcher.
   *
   * @param objectMapper the object mapper
   * @param messagePublisher the message publisher
   */
  public AbstractBatchMessageDispatcher(
      ObjectMapper objectMapper, MessagePublisher messagePublisher) {
    this.objectMapper = objectMapper;
    this.messagePublisher = messagePublisher;
  }

  // ===============================================================================================
  // ABSTRACT
  // ===============================================================================================

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public void dispatch(String message) {
    try {
      JsonNode jsonNode = objectMapper.readTree(message);

      if (jsonNode.isArray()) {
        Iterator<JsonNode> iterator = jsonNode.elements();
        StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
            .forEach(jsonNodeChild -> messagePublisher.send(jsonNodeChild.asText()));
      } else {
        messagePublisher.send(jsonNode.asText());
      }
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    }
  }
}
