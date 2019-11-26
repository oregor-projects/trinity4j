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

package com.oregor.trinity4j.shared.camel;

import org.apache.camel.Endpoint;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.DeadLetterChannelBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Dead letter route builder.
 *
 * @author Christos Tsakostas
 */
public abstract class DeadLetterRouteBuilder extends RouteBuilder {

  /** The constant DEAD. */
  protected static final String DEAD = ".dead";

  private static final Logger LOGGER = LoggerFactory.getLogger(DeadLetterRouteBuilder.class);

  @Override
  public DeadLetterChannelBuilder deadLetterChannel(String uri) {
    return applyOptions(super.deadLetterChannel(uri));
  }

  @Override
  public DeadLetterChannelBuilder deadLetterChannel(Endpoint endpoint) {
    return applyOptions(super.deadLetterChannel(endpoint));
  }

  @Override
  public RouteDefinition from(String endpoint) {
    return super.from(endpoint).id(endpoint);
  }

  private DeadLetterChannelBuilder applyOptions(DeadLetterChannelBuilder builder) {
    return (DeadLetterChannelBuilder)
        builder
            .logHandled(true)
            .allowRedeliveryWhileStopping(false)
            .asyncDelayedRedelivery()
            .logExhausted(true)
            .logExhaustedMessageBody(true)
            .logExhaustedMessageHistory(true)
            .log(LOGGER)
            .loggingLevel(LoggingLevel.ERROR);
  }
}
