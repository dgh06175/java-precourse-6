package christmas.domain;

import christmas.domain.records.MenuQuantity;
import christmas.domain.records.StringIntPair;
import christmas.domain.enums.MenuCategory;
import christmas.exception.OrderException;
import java.util.ArrayList;
import java.util.List;

public class OrderedMenu {
    private static final int MAX_MENU_QUANTITY = 20;
    private final List<MenuQuantity> menuQuantities;

    public OrderedMenu(List<MenuQuantity> parsedOrder) {
        validateOrder(parsedOrder);
        this.menuQuantities = new ArrayList<>(parsedOrder);
    }

    /**
     * @return OrderedMenu 에 저장된 메뉴들 가격 총합
     */
    public int getTotalPrice() {
        return menuQuantities.stream()
                .mapToInt(item -> item.menu().getPrice(item.quantity()))
                .sum();
    }

    /**
     * 카테고리 음식이 얼마나 할인받는지 계산
     * @param category 카테고리
     * @param discountAmount 할인금
     * @return 카테고리에 맞는 음식 개수 * 할인금
     */
    public int getDiscountByCategory(MenuCategory category, int discountAmount) {
        return menuQuantities.stream()
                .filter(entry -> entry.menu().isCategoryEquals(category))
                .mapToInt(MenuQuantity::quantity)
                .sum() * discountAmount;
    }

    /**
     * @return 출력을 위한 메뉴와 메뉴개수 dto
     */
    public List<StringIntPair> getMenuStringQuantity() {
        List<StringIntPair> stringOrder = new ArrayList<>();
        for(var item: menuQuantities) {
            StringIntPair stringIntPair = new StringIntPair(item.menu().displayName, item.quantity());
            stringOrder.add(stringIntPair);
        }
        return stringOrder;
    }

    private void validateOrder(List<MenuQuantity> orders) {
        if (isAnyMenuQuantityLowerThanOne(orders)) {
            throw new OrderException();
        }
        if (isTotalMenuQuantityMoreThanMax(orders)) {
            throw new OrderException();
        }
        if (isEveryMenuCategoryIsDrink(orders)) {
            throw new OrderException();
        }
    }

    private boolean isAnyMenuQuantityLowerThanOne(List<MenuQuantity> orders) {
        return orders.stream()
                .map(MenuQuantity::quantity)
                .anyMatch(quantity -> quantity < 1);
    }

    private boolean isTotalMenuQuantityMoreThanMax(List<MenuQuantity> orders) {
        return orders.stream()
                .map(MenuQuantity::quantity)
                .mapToInt(i -> i).sum() > MAX_MENU_QUANTITY;
    }

    private boolean isEveryMenuCategoryIsDrink(List<MenuQuantity> orders) {
        return orders.stream()
                .map(MenuQuantity::menu)
                .allMatch(menu -> menu.isCategoryEquals(MenuCategory.DRINK));
    }
}
