/*
 * Copyright 2002 - 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package work.rustam.common.utils.session;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestSessionVariables<K, V> extends ConcurrentHashMap implements Map {
    private final Map<String, String> metadata = new ConcurrentHashMap();

    public TestSessionVariables() {
    }

    public void shouldContainKey(Object key) {
        Object result = super.get(key);
        if (result == null) {
            throw new AssertionError("Session variable " + key + " expected but not found.");
        }
    }

    public Object put(Object key, Object value) {
        return value == null ? this.remove(key) : super.put(key, value);
    }

    public Map<String, String> getMetaData() {
        return ImmutableMap.copyOf(this.metadata);
    }

    public void addMetaData(String key, String value) {
        this.metadata.put(key, value);
    }

    public void clearMetaData() {
        this.metadata.clear();
    }

    public void clear() {
        this.clearMetaData();
        super.clear();
    }
}