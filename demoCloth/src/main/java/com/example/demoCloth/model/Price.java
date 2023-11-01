package com.example.demoCloth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Pojo of the table PRICES that contains the items to query.
 */
@Entity
@Table(name = "PRICES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("PRODUCT_ID")
    private Long productId;

    @JsonProperty("PRICE_LIST")
    private Integer priceList;

    @JsonProperty("START_DATE")
    private LocalDateTime startDate;

    @JsonProperty("END_DATE")
    private LocalDateTime endDate;

    @JsonProperty("PRICE")
    private BigDecimal price;

    @JsonProperty("PRIORITY")
    private String priority;

    @JsonProperty("CURR")
    private String curr;

    /**
     * Check if two objects and identical.
     *
     * @param o Object to compare.
     * @return true if both items contains the same information.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        com.example.demoCloth.model.Price price = (com.example.demoCloth.model.Price) o;
        return Objects.equals(this.productId, price.productId) &&
                //this.brandSet.equals(price.brandSet) &&
                Objects.equals(this.priceList, price.priceList) &&
                Objects.equals(this.startDate, price.startDate) &&
                Objects.equals(this.endDate, price.endDate) &&
                Objects.equals(this.price, price.price) &&
                Objects.equals(this.priority, price.priority) &&
                Objects.equals(this.curr, price.curr);
    }

    /**
     * Returns the hash of the object.
     *
     * @return integer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(productId, priceList, startDate, endDate, price, priority, curr);
    }

    /**
     * Returns the string representation of the object.
     *
     * @return String.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Price {\n");

        sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
        sb.append("    priceList: ").append(toIndentedString(priceList)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
        sb.append("    currency: ").append(toIndentedString(curr)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}