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
package skeleton_tests.stepsDef;

import cucumber.api.java.en.And;

import static com.codeborne.selenide.Selenide.sleep;

public class CommonStepsDef extends AbstractStepsDef {

    @And("User wait for {int} seconds")
    public void iWaitSeconds(int sec) {
		sleep(sec*1000);
    }

    @And("The status code is {int}")
    public void theStatusCodeIs(int arg0) {
    }
}
