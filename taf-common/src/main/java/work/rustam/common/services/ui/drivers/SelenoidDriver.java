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
package work.rustam.common.services.ui.drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SelenoidDriver implements WebDriverProvider {

	@Override
	public WebDriver createDriver(DesiredCapabilities capabilities) {
		String currTimeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd_HH:mm"));

		DesiredCapabilities browser = new DesiredCapabilities();
		browser.setBrowserName("chrome");
		browser.setVersion("75.0");
		browser.setCapability("enableVNC", true);
		browser.setCapability("enableVideo", true);
		browser.setCapability("videoName", new StringBuilder(currTimeStamp).append(".mp4").toString());
		browser.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		try {
			RemoteWebDriver driver = new RemoteWebDriver(
					URI.create("http://172.21.21.63:4444/wd/hub").toURL(),
					browser
			);
			driver.manage().window().setSize(new Dimension(1920, 1080));
			return driver;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
