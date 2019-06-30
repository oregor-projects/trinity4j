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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.StreamSupport;
import org.junit.Before;
import org.junit.Test;

/** @author Christos Tsakostas */
public class DomainMessageDataConverterTest {

  private static UUID rootUuid = UuidGenerator.timeBasedUuid();
  private static UUID tenantUuid = UuidGenerator.timeBasedUuid();

  private ObjectMapper objectMapper;
  private SomeDomainMessageDataConverter someDomainMessageDataConverter;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();
    someDomainMessageDataConverter = new SomeDomainMessageDataConverter(objectMapper);
  }

  @Test
  public void shouldHaveContext() {
    assertThat(someDomainMessageDataConverter.getContext()).isEqualTo("SOME_CONTEXT");
  }

  @Test
  public void shouldConvert() {
    SomeDomainEvent someDomainEvent = new SomeDomainEvent();
    someDomainEvent.setRootId(rootUuid);
    someDomainEvent.setTenantId(tenantUuid);
    someDomainEvent.setMessageName("someName");
    someDomainEvent.setMessageVersion(1);
    someDomainEvent.setMessageType(DomainMessageType.EVENT);

    SomeDomainMessageData someDomainMessageData =
        someDomainMessageDataConverter.convert(someDomainEvent);

    assertThat(someDomainMessageData).isNotNull();
    assertThat(someDomainMessageData.getMessageId()).isNotNull();
    assertThat(someDomainMessageData.getOccurredOn()).isNotNull();
    assertThat(someDomainMessageData.getRootId()).isNotNull();
    assertThat(someDomainMessageData.getRootVersion()).isNotNull();
    assertThat(someDomainMessageData.getMessageType()).isNotNull();
    assertThat(someDomainMessageData.getMessageName()).isNotNull();
    assertThat(someDomainMessageData.getMessageBody()).isNotNull();
    assertThat(someDomainMessageData.getUserId()).isNotNull();
    assertThat(someDomainMessageData.getIpAddress()).isNotNull();
  }

  @Test
  public void shouldFailToConvertForNullRootId() {
    SomeDomainEvent someDomainEvent = new SomeDomainEvent();

    assertThatThrownBy(() -> someDomainMessageDataConverter.convert(someDomainEvent))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void shouldConvertList() {
    SomeDomainEvent someDomainEvent1 = new SomeDomainEvent();
    someDomainEvent1.setRootId(rootUuid);
    someDomainEvent1.setTenantId(tenantUuid);
    someDomainEvent1.setMessageName("someName");
    someDomainEvent1.setMessageVersion(1);

    SomeDomainEvent someDomainEvent2 = new SomeDomainEvent();
    someDomainEvent2.setRootId(rootUuid);
    someDomainEvent2.setTenantId(tenantUuid);
    someDomainEvent2.setMessageName("someName");
    someDomainEvent2.setMessageVersion(1);

    Iterable<SomeDomainMessageData> iterable =
        someDomainMessageDataConverter.convert(Arrays.asList(someDomainEvent1, someDomainEvent2));

    assertThat(iterable).isNotNull();
    assertThat(StreamSupport.stream(iterable.spliterator(), false).count()).isEqualTo(2);
  }
}
