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

import com.codeborne.selenide.Selenide;
import work.rustam.common.services.ui.pages.GoogleStartPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;

public class GoogleSearchStepsDef extends AbstractStepsDef {

	@Given("^I open Google Page$")
	public void openHomePage() {
		googleStartPage = Selenide.open(baseUrl, GoogleStartPage.class);
	}

	@When("^Enter search query into text input$")
	public void performSearch() {
		googleResultsPage = googleStartPage.search("selenide");
	}

	@Then("^Search results returned$")

	public void assertResults() {
		googleResultsPage.results().get(0).shouldHave(text("Selenide:"));
	}

	@Then("^Verify that footer contains elements:$")
	public void verifyThatFooterContainsElements(List<String> footerElementsNames) {
		googleResultsPage.navigationLabels().shouldHave(texts(footerElementsNames));
	}


}
