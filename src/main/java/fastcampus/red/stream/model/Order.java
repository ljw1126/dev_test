package fastcampus.red.stream.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
public class Order {
    private long id;
    private LocalDateTime createdAt;
    private long createdByUserId;
    private OrderStatus status;
    private BigDecimal amount;
    private List<OrderLine> orderLines;

    public enum OrderStatus{
        CREATED,
        IN_PROGRESS,
        ERROR,
        PROCESSED
    }

    @Builder
    public Order(long id, LocalDateTime createdAt, long createdByUserId, OrderStatus status, BigDecimal amount, List<OrderLine> orderLines) {
        this.id = id;
        this.createdAt = createdAt;
        this.createdByUserId = createdByUserId;
        this.status = status;
        this.amount = amount;
        this.orderLines = orderLines;
    }
}
