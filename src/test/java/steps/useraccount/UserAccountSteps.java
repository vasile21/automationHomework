/**
 * Created by vasilev on 28/03/2019.
 */
package steps.useraccount;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import steps.UserPagesContainer;

public class UserAccountSteps {
    private UserPagesContainer container;

    public UserAccountSteps(UserPagesContainer container){
        this.container = container;
    }

    @And("^I open user menu$")
    public void openUserMenu() {
        container.emagHomePage.openUserAccountMenu();
    }

    @Then("^I verify that user name \"([^\"]*)\" is displayed$")
    public void verifyUserNameIsDisplayed(String userName) {
        String actualMessage = container.userHomePage.greetMessage(userName);
        Assert.assertTrue(actualMessage.contains(userName));
    }

//    @And("^I verify user account details$")
//    public void iVerifyUserAccountDetails(DataTable dataTable) throws Throwable {
//        List<UserAccountDetail> userAccountDetails = dataTable.asList(UserAccountDetail.class);
//
//        for (UserAccountDetail accountDetails : userAccountDetails) {
//            System.out.println("forma de dresare: " + accountDetails.forma_de_adresare);
//            System.out.println("nume si prenume: " + accountDetails.nume_Prenume);
//            System.out.println("nickname: " + accountDetails.nickname);
//        }
//    }
//
//    public class UserAccountDetail {
//        private String forma_de_adresare;
//        private String nume_Prenume;
//        private String nickname;
//
//        UserAccountDetail(String formaAdresare, String numePrenume, String nickNamee) {
//            this.forma_de_adresare = formaAdresare;
//            this.nume_Prenume = numePrenume;
//            this.nickname = nickNamee;
//        }
//    }
}