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

package com.oregor.trinity4j.properties;

import java.util.Locale;

/**
 * The interface Property file aux service.
 *
 * @author Christos Tsakostas
 */
public interface PropertyFileAuxService {

  /**
   * Gets message.
   *
   * @param code the code
   * @param locale the locale
   * @return the message
   */
  String getMessage(String code, Locale locale);

  /**
   * Gets message.
   *
   * @param code the code
   * @param args the args
   * @param locale the locale
   * @return the message
   */
  String getMessage(String code, Object[] args, Locale locale);
}
