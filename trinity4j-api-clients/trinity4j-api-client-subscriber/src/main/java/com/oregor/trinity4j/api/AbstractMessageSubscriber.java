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

package com.oregor.trinity4j.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oregor.trinity4j.commons.assertion.Assertion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * The type Abstract message subscriber.
 *
 * @author Christos Tsakostas
 */
public abstract class AbstractMessageSubscriber implements MessageSubscriber {

  // ===============================================================================================
  // STATE / DEPENDENCIES
  // ===============================================================================================

  /** The Object mapper. */
  protected final ObjectMapper objectMapper;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract message subscriber.
   *
   * @param objectMapper the object mapper
   */
  protected AbstractMessageSubscriber(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  // ===============================================================================================
  // FUNCTIONALITY
  // ===============================================================================================

  /**
   * Transforms a JsonNode into a UUID.
   *
   * @param jsonNode the json node
   * @return the uuid
   * @throws IllegalArgumentException if the node does not contain a valid UUID.
   */
  protected UUID asUuid(JsonNode jsonNode) {
    Assertion.isNotNull(jsonNode, "jsonNode is required");
    return UUID.fromString(jsonNode.asText());
  }

  /**
   * Transforms a JsonNode into a LocalDate.
   *
   * @param jsonNode the json node
   * @return the local date
   */
  protected LocalDate asLocalDate(JsonNode jsonNode) {
    Assertion.isNotNull(jsonNode, "jsonNode is required");
    return LocalDate.parse(jsonNode.asText(), DateTimeFormatter.ISO_DATE);
  }

  /**
   * Transforms a JsonNode into a LocalDateTime.
   *
   * @param jsonNode the json node
   * @return the local date time
   */
  protected LocalDateTime asLocalDateTime(JsonNode jsonNode) {
    Assertion.isNotNull(jsonNode, "jsonNode is required");
    return LocalDateTime.parse(jsonNode.asText(), DateTimeFormatter.ISO_DATE_TIME);
  }
}
