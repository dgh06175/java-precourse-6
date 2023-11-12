package christmas.domain.events;

import static christmas.constant.END_OF_DECEMBER_DAY;
import static christmas.constant.START_OF_MONTH_DAY;

import christmas.domain.Date;
import christmas.domain.OrderedMenu;
import christmas.domain.enums.Menu;

public class GiveAway extends AbstractEvent {
    private static final int GIVE_AWAY_MIN_PRICE = 120_000;
    private static final String SALE_NAME = "증정 이벤트";

    public GiveAway() {
        super(SALE_NAME, START_OF_MONTH_DAY, END_OF_DECEMBER_DAY);
    }

    @Override
    public boolean isSpecificEventValid(Date date, OrderedMenu orderedMenu) {
        return orderedMenu.getTotalPrice() >= GIVE_AWAY_MIN_PRICE;
    }

    @Override
    public int calculateDiscount(Date date, OrderedMenu orderedMenu) {
        // 총 주문 금액 12만원 이상일떄 샴페인 증정
        return Menu.CHAMPAGNE.price;
    }
}
