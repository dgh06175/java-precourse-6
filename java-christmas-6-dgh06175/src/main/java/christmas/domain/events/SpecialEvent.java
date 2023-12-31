package christmas.domain.events;

import static christmas.domain.events.Constant.CHRISTMAS_DAY;
import static christmas.domain.events.Constant.START_OF_MONTH_DAY;

import christmas.domain.OrderedMenu;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialEvent extends Event {
    private static final int SALE_AMOUNT = 1000;
    private static final String SALE_NAME = "특별 할인";

    public SpecialEvent(LocalDate eventDate) {
        super(eventDate, SALE_NAME, START_OF_MONTH_DAY, eventDate.lengthOfMonth());
    }

    /**
     * 달력에 별이 있는 경우에만 이벤트 적용
     * @param visitDate 방문 날짜
     * @param orderedMenu 주문 메뉴
     * @return 적용 여부
     */
    @Override
    protected boolean isSpecificEventValid(LocalDate visitDate, OrderedMenu orderedMenu) {
        return isSpecial(visitDate);
    }

    @Override
    protected int calculateDiscount(LocalDate visitDate, OrderedMenu orderedMenu) {
        return SALE_AMOUNT;
    }

    private boolean isSpecial(LocalDate visitDate) {
        return visitDate.getDayOfWeek() == DayOfWeek.SUNDAY || visitDate.getDayOfMonth() == CHRISTMAS_DAY;
    }
}
