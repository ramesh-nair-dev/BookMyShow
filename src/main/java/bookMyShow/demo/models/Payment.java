package bookMyShow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
public class Payment extends BaseClass{
    private String refNumber;
    @Enumerated(EnumType.STRING)
    private PaymentGateWay gateway;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private int amount;
    @ManyToOne
    private Booking booking;
    private Date paymentDate;


}


/**
 * Payment class represents a record of a transaction made by the user for a booking.
 *
 * Fields:
 *
 * 1. `refNumber` — A unique reference number for tracking the payment.
 *
 * 2. `gateway` — Represents which **Payment Gateway** was used (e.g., Razorpay, PayPal, Stripe).
 *
 * 3. `paymentMode` — Represents the mode of payment (e.g., Credit Card, Debit Card, UPI, Net Banking).
 *
 * 4. `paymentStatus` — Represents the current status of the payment (e.g., SUCCESSFUL, FAILED, PENDING).
 *
 * 5. `amount` — The total payment amount for this transaction.
 *
 * 6. `booking` — The Booking associated with this payment.
 *    - This establishes a **ManyToOne** relationship:
 *      - One Payment → belongs to one Booking.
 *      - One Booking → can have multiple Payments (e.g., retries, partial payments).
 *
 * 7. `paymentDate` — The timestamp when the payment was made.
 *
 *
 * Cardinality & Mapping Notes:
 *
 * 1. **Payment ↔ Booking**
 *    - One Booking can have multiple Payments (e.g., multiple attempts before success).
 *    - One Payment always belongs to exactly one Booking.
 *    - Hence: `@ManyToOne` relationship.
 *
 * 2. **Enum Fields (gateway, paymentMode, paymentStatus)**
 *    - Stored in DB as **string values** using `@Enumerated(EnumType.STRING)`.
 *    - This approach is preferred over `EnumType.ORDINAL` because:
 *        - It improves readability in DB.
 *        - It prevents errors when new enum values are added or order changes.
 *
 */
