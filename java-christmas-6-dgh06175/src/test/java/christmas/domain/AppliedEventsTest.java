package christmas.domain;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.domain.testUtil.createSampleOrder1;
import static christmas.domain.testUtil.createSampleOrder2;
import static christmas.domain.testUtil.eventDate;
import static org.assertj.core.api.Assertions.assertThat;

public class AppliedEventsTest {
    private LocalDate visitDate;

    @DisplayName("적용된 이벤트의 총 할인 금액 계산")
    @Test
    void calculateTotalDiscount() {
        OrderedMenu orderedMenu = createSampleOrder1();

        visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), 15);

        AppliedEvents appliedEvents = AppliedEvents.of(visitDate, orderedMenu);
        int totalDiscount = appliedEvents.getTotalDiscount();

        assertThat(totalDiscount).isEqualTo(2400 + 2023 * 3 + 25000);
    }

    @DisplayName("Giveaway 이벤트 포함 여부 확인")
    @Test
    void checkGiveawayEvent() {
        OrderedMenu orderedMenu1 = createSampleOrder1();
        OrderedMenu orderedMenu2 = createSampleOrder2();
        visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), 15);

        AppliedEvents appliedEvents = AppliedEvents.of(visitDate, orderedMenu1);
        AppliedEvents appliedEvents2 = AppliedEvents.of(visitDate, orderedMenu2);
        boolean containsGiveaway1 = appliedEvents.containsGiveawayEvent();
        boolean containsGiveaway2 = appliedEvents2.containsGiveawayEvent();

        assertThat(containsGiveaway1).isTrue();
        assertThat(containsGiveaway2).isFalse();
    }

    @DisplayName("할인 후 최종 가격 테스트")
    @Test
    void testGetPriceAfterDiscount() {
        OrderedMenu orderedMenu = createSampleOrder1();
        visitDate = LocalDate.of(eventDate.getYear(), eventDate.getMonth(), 15);

        AppliedEvents appliedEvents = AppliedEvents.of(visitDate, orderedMenu);
        int priceBeforeDiscount = 164_000;
        int discountedPrice = appliedEvents.getPriceAfterDiscount(priceBeforeDiscount);
        int expectedPrice = 130_531 + 25_000;

        assertThat(discountedPrice).isEqualTo(expectedPrice);
    }
}
