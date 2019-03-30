/**
 * Created by vasilev on 30/03/2019.
 */
package pages;

import components.notifications.NotificationWindow;
import components.pagecomponents.ProductHightlights;

public class ProductPage {

    private ProductHightlights productHightlights;
    private NotificationWindow notificationWindow;

    public ProductPage() {
        productHightlights = new ProductHightlights();
    }


    public void addProductToWishList(String productOption) {
        notificationWindow = productHightlights.addProductToWishList();
    }

    public boolean isNotificationMessageDisplayed(String notifMessage) {
        return notificationWindow.isMessageDisplayed(notifMessage);
    }

    public void isFavoritesIconDisplayed() {
        productHightlights.isFavoriteIconDisplayed();
    }
}
