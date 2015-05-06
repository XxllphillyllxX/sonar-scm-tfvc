/*
 * SonarQube :: SCM :: TFS :: Plugin
 * Copyright (C) 2014 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.scm.tfs;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import org.sonar.api.BatchComponent;
import org.sonar.api.CoreProperties;
import org.sonar.api.PropertyType;
import org.sonar.api.batch.InstantiationStrategy;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Qualifiers;

import java.util.List;

@InstantiationStrategy(InstantiationStrategy.PER_BATCH)
public class TfsConfiguration implements BatchComponent {

  private static final String CATEGORY = "TFS";
  private static final String USERNAME_PROPERTY_KEY = "sonar.tfs.username";
  private static final String PASSWORD_PROPERTY_KEY = "sonar.tfs.password.secured";
  private final Settings settings;

  public TfsConfiguration(Settings settings) {
    this.settings = settings;
  }

  public static List<PropertyDefinition> getProperties() {
    return ImmutableList.of(
      PropertyDefinition.builder(USERNAME_PROPERTY_KEY)
        .name("Username")
        .description("Username to be used for TFS authentication")
        .type(PropertyType.STRING)
        .onQualifiers(Qualifiers.PROJECT)
        .category(CoreProperties.CATEGORY_SCM)
        .subCategory(CATEGORY)
        .index(0)
        .build(),
      PropertyDefinition.builder(PASSWORD_PROPERTY_KEY)
        .name("Password")
        .description("Password to be used for TFS authentication")
        .type(PropertyType.PASSWORD)
        .onQualifiers(Qualifiers.PROJECT)
        .category(CoreProperties.CATEGORY_SCM)
        .subCategory(CATEGORY)
        .index(1)
        .build());
  }

  public String username() {
    return Strings.nullToEmpty(settings.getString(USERNAME_PROPERTY_KEY));
  }

  public String password() {
    return Strings.nullToEmpty(settings.getString(PASSWORD_PROPERTY_KEY));
  }

}
