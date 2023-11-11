package christmas.view;

import christmas.domain.Order;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final DecimalFormat decimalFormat = new DecimalFormat("###,###");
    private static final String READ_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String READ_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EVENT_START_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public void printReadDateMessage() {
        System.out.println(READ_DATE_MESSAGE);
    }

    public void printReadOrderMessage() {
        System.out.println(READ_ORDER_MESSAGE);
    }

    public void printMenu(Map<String, Integer> orders) {
        System.out.println(EVENT_START_MESSAGE);
        System.out.println();
        System.out.println("<주문 메뉴>");
        for (String menuName: orders.keySet()) {
            System.out.printf("%s %d개\n", menuName, orders.get(menuName));
        }
    }

    public void printPriceBeforeSale(int priceBeforeSale) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        String formattedPrice = decimalFormat.format(priceBeforeSale);
        System.out.printf("%s원\n", formattedPrice);
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
