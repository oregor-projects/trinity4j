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

package com.oregor.trinity4j.api.clients.domainmessage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oregor.trinity4j.api.clients.subscriber.AbstractMessageSubscriber;
import java.io.IOException;

/**
 * The type Abstract domain message subscriber.
 *
 * @param <T> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractDomainMessageSubscriber<T extends IncomingDomainMessage>
    extends AbstractMessageSubscriber {

  // ===============================================================================================
  // STATE / DEPENDENCIES
  // ===============================================================================================

  private final Class<T> classIncomingDomainMessage;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract domain message subscriber.
   *
   * @param objectMapper the object mapper
   * @param classIncomingDomainMessage the class incoming domain message
   */
  public AbstractDomainMessageSubscriber(
      ObjectMapper objectMapper, Class<T> classIncomingDomainMessage) {
    super(objectMapper);
    this.classIncomingDomainMessage = classIncomingDomainMessage;
  }

  // ===============================================================================================
  // ABSTRACT
  // ===============================================================================================

  /**
   * Process.
   *
   * @param incomingDomainMessage the incoming domain message
   * @param jsonNodeBody the json node body
   */
  protected abstract void process(T incomingDomainMessage, JsonNode jsonNodeBody);

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public void process(String message) {
    try {
      T incomingDomainMessage = objectMapper.readValue(message, classIncomingDomainMessage);
      JsonNode jsonNodeBody = objectMapper.readTree(incomingDomainMessage.getMessageBody());

      process(incomingDomainMessage, jsonNodeBody);
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage(), e);
    }
  }
}
