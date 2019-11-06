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

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Value;
import work.rustam.common.services.api.models.UserResponse;
import work.rustam.common.services.api.models.UsersResponse;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ApiStepDef extends AbstractStepsDef  {

    @Value("${reqres.path.get.user.url}")
    String GET_USER_INFO_ENDPOINT;

    @Value("${reqres.path.get.allUsers.url}")
    String GET_ALL_USERS_ENDPOINT;



    @Given("API request for user with id equals {int} is sent to web service")
    public void apiRequestForUserWithIdEqualsIsSentToWebService(int id) {
        restService.sendGetRequest(BaseEndpoint + GET_USER_INFO_ENDPOINT + id);
    }

    @Then("response contain user with first name equal to {string}")
    public void responseContainUserWithFirstNameEqualToJanet(String name) {
        UserResponse userResponse = restService.getResponse().as(UserResponse.class);
        assertThat(userResponse.getData().getName()).isEqualTo(name);
    }

    @Given("get all users API request is sent to web service")
    public void getAllUsersAPIRequestIsSentToWebService() {
        restService.sendGetRequest(BaseEndpoint + GET_ALL_USERS_ENDPOINT);
    }

    @Then("response contain data for {int} users")
    public void responseContainDataForUsers(int numberOfUsers) {
        UsersResponse usersResponse = restService.getResponse().as(UsersResponse.class);
        assertThat(usersResponse.getData().size()).isEqualTo(numberOfUsers);
    }

}
