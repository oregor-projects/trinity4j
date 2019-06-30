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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oregor.trinity4j.commons.messaging.publisher.MessagePublisher;
import org.junit.Before;
import org.junit.Test;

/**
 * The type Abstract batch message dispatcher test.
 *
 * @author Christos Tsakostas
 */
public class AbstractBatchMessageDispatcherTest {

  private ObjectMapper objectMapper;
  private MessagePublisher messagePublisher;
  private TestBatchMessageDispatcher testBatchMessageDispatcher;

  /**
   * Sets up.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp() throws Exception {
    objectMapper = new ObjectMapper();
    messagePublisher = mock(MessagePublisher.class);
    testBatchMessageDispatcher = new TestBatchMessageDispatcher(objectMapper, messagePublisher);
  }

  /** Should dispatch one message. */
  @Test
  public void shouldDispatchOneMessage() {
    String message = "{}";

    testBatchMessageDispatcher.dispatch(message);

    verify(messagePublisher).send("");
  }
}
