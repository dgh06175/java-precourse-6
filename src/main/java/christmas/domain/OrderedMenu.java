package christmas.domain;

import christmas.domain.enums.Menu;
import christmas.domain.enums.MenuCategory;
import christmas.exception.OrderException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OrderedMenu {
    private static final int MAX_MENU_QUANTITY = 20;
    private final EnumMap<Menu, Integer> value;

    public OrderedMenu(EnumMap<Menu, Integer> parsedOrder) {
        validateOrder(parsedOrder);
        this.value = new EnumMap<>(parsedOrder);
    }

    public int getTotalPrice() {
        return value.entrySet().stream()
                .mapToInt(entry -> entry.getKey().price * entry.getValue())
                .sum();
    }

    public Map<String, Integer> getMenuStringAndCount() {
        return value.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().name,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing
                ));
    }

    public int getDiscountByCategory(MenuCategory category, int discountAmount) {
        return value.entrySet().stream()
                .filter(entry -> entry.getKey().menuCategory == category)
                .mapToInt(Entry::getValue)
                .sum() * discountAmount;
    }

    private void validateOrder(EnumMap<Menu, Integer> order) {
        if (isAnyMenuQuantityLowerThanOne(order)) {
            throw new OrderException();
        }
        if (isTotalMenuQuantityMoreThanMax(order)) {
            throw new OrderException();
        }
        if (isEveryMenuCategoryIsDrink(order)) {
            throw new OrderException();
        }
    }

    private boolean isAnyMenuQuantityLowerThanOne(EnumMap<Menu, Integer> order) {
        return order.values().stream().anyMatch(quantity -> quantity < 1);
    }

    private boolean isTotalMenuQuantityMoreThanMax(EnumMap<Menu, Integer> order) {
        return order.values().stream().mapToInt(Integer::intValue).sum() > MAX_MENU_QUANTITY;
    }

    private boolean isEveryMenuCategoryIsDrink(EnumMap<Menu, Integer> order) {
        return order.keySet().stream().allMatch(menu -> menu.menuCategory == MenuCategory.DRINK);
    }
}
