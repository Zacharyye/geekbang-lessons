/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.geektimes.configuration.microprofile.config.source.servlet;

import org.eclipse.microprofile.config.spi.ConfigSource;

import javax.servlet.ServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * {@link ServletRequest} {@link ConfigSource}
 *
 * @author zhouyin
 * @since 1.0.0
 */
public class ServletRequestConfigSource implements ConfigSource {

    protected ServletRequest request;

    private final int ordinal;

    private final Map<String, String> configData;

    public ServletRequestConfigSource(ServletRequest request) {
        this.request = request;
        this.ordinal = ConfigSource.super.getOrdinal();
        this.configData = new HashMap<>();
    }

    public ServletRequestConfigSource(ServletRequest request, int ordinal) {
        this.request = request;
        this.ordinal = ordinal;
        this.configData = new HashMap<>();
    }

    @Override
    public Map<String, String> getProperties() {
        Enumeration<String> params = request.getParameterNames();
        while(params.hasMoreElements()) {
            String kyeStr = params.nextElement();
            configData.put(kyeStr, request.getParameter(kyeStr));
        }
        return this.configData;
    }

    @Override
    public Set<String> getPropertyNames() {
        return getProperties().keySet();
    }

    @Override
    public int getOrdinal() {
        return this.ordinal;
    }

    @Override
    public String getValue(String propertyName) {
        return getProperties().get(propertyName);
    }

    @Override
    public String getName() {
        return this.request.getLocalName();
    }
}
