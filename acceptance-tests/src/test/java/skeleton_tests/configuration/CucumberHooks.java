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
package skeleton_tests.configuration;

import com.codeborne.selenide.logevents.SelenideLogger;
import work.rustam.common.utils.TestStatusText;
import work.rustam.common.utils.session.Key;
import work.rustam.common.utils.session.Session;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static work.rustam.common.services.ui.drivers.DriverManager.initBrowser;

@Slf4j
public class CucumberHooks {
    private static final TestStatusText testStatusText = new TestStatusText();
    private static int failedTests = 0;
    private static int passedTests = 0;
    private static int count = 0;

    @Value("${webdriver.browser}")
    private String browser;

    @Before
    public void setScenarioInfoIntoLog(Scenario scenario) {
        log.info("Scenario " + scenario.getName());
        log.info(testStatusText.getTEXT_FOR_START());
        setIdIntoLog(scenario);
        setScenarioName(scenario);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Session.getCurrentSession().put(Key.Keys.ACTIVE_BROWSER,browser);
        initBrowser(browser);
    }

    @After
    public void logCountOfTest(Scenario scenario) {
        count++;
        if (scenario.isFailed()) {
            failedTests++;
        } else {
            passedTests++;
        }
        log.info("There are {} tests completed.", count);
        Session.getCurrentSession().put(Key.Keys.COUNT, String.valueOf(count));
        log.info("Status of last test is {}", scenario.getStatus());
        log.info("Passed tests: {}, Failed test: {}", passedTests, failedTests);
        Session.getCurrentSession().put(Key.Keys.PASSED_TESTS, String.valueOf(passedTests));
        Session.getCurrentSession().put(Key.Keys.FAILED_TESTS, String.valueOf(failedTests));
        if (scenario.isFailed()) {
            log.info(testStatusText.getTEXT_FOR_FAILED_TEST());
        } else {
            log.info(testStatusText.getTEXT_FOR_PASSED_TEST());
        }
    }

    public void setScenarioName(Scenario scenario) {
        MDC.put("scenarioName", scenario.getName());
    }

    public void setIdIntoLog(Scenario scenario) {
        final Pattern testCaseIdPattern = Pattern.compile("@TestCaseId\\(\"+?([^\"]+)\"+?\\)");
        for (String tag : scenario.getSourceTagNames()) {
            Matcher matcher = testCaseIdPattern.matcher(tag);
            if (matcher.matches()) {
                final String testCaseId = matcher.group(1);
                MDC.put("testCaseId", testCaseId);
            }
        }
    }
}
