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

import lombok.Getter;

@Getter
public class Key {

    private final String text;

    private Key(final String text) {
        this.text = text;
    }

    public static String getCanonicalName(Object object) {
        return object.getClass().getCanonicalName();
    }

    @Override
    public String toString() {
        return text;
    }

    public enum Keys {
        COUNT("count"),
        PASSED_TESTS("passed_tests"),
        FAILED_TESTS("failed_tests"),

        ACTIVE_BROWSER("browser");

        private final String text;

        private Keys(final String text) {
            this.text = text;
        }
    }

}
