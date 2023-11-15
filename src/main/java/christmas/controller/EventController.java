package christmas.controller;

import christmas.domain.MenuService;
import christmas.domain.OrderedMenu;
import christmas.domain.dto.MenuQuantity;
import christmas.domain.dto.StringIntPair;
import christmas.exception.DateException;
import christmas.util.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class EventController {
    private final LocalDate eventDate;
    private final InputView inputView;
    private final InputParser inputParser;
    private final EventFacade eventFacade;

    public EventController(int year, Month eventMonth, InputView inputView, OutputView outputView, InputParser inputParser) {
        this.eventDate = LocalDate.of(year, eventMonth, 1);
        this.inputView = inputView;
        this.inputParser = inputParser;
        this.eventFacade = new EventFacade(outputView);
    }

    public void run() {
        eventFacade.displayMenuList();
        // 날짜, 주문 입력 받기
        LocalDate visitDate = requestVisitDate();
        OrderedMenu orderedMenu = requestOrder();
        // 주문 내역 출력
        eventFacade.displayOrderedMenu(visitDate, orderedMenu);
        // 이벤트 혜택 출력
        eventFacade.displayEventResult(visitDate, orderedMenu);
    }

    private LocalDate requestVisitDate() {
        return eventFacade.executeWithRetry(() -> {
            String inputDate = inputView.readDate(eventDate);
            int parsedDay = inputParser.parseDay(inputDate);
            return initLocalDate(parsedDay);
        });
    }

    private LocalDate initLocalDate(int parsedDay) {
        try {
            return LocalDate.of(eventDate.getYear(), eventDate.getMonth(), parsedDay);
        } catch (DateTimeException e) {
            throw new DateException();
        }

    }

    private OrderedMenu requestOrder() {
        return eventFacade.executeWithRetry(() -> {
            String inputOrder = inputView.readOrder();
            List<StringIntPair> parsedStringOrder = inputParser.parseOrder(inputOrder);
            List<MenuQuantity> parsedOrder = MenuService.stringToMenu(parsedStringOrder);
            return new OrderedMenu(parsedOrder);
        });
    }
}
