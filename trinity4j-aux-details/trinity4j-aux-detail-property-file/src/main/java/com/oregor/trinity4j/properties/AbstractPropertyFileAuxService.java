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
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * The type Abstract property file aux service.
 *
 * @author Christos Tsakostas
 */
public abstract class AbstractPropertyFileAuxService implements PropertyFileAuxService {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private final ReloadableResourceBundleMessageSource messageSource;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract property file aux service.
   *
   * @param basename the basename
   */
  protected AbstractPropertyFileAuxService(String basename) {
    messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename(basename);
    messageSource.setDefaultEncoding("UTF-8");
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public String getMessage(String code, Locale locale) {
    return messageSource.getMessage(code, null, locale);
  }

  @Override
  public String getMessage(String code, Object[] args, Locale locale) {
    return messageSource.getMessage(code, args, locale);
  }
}
