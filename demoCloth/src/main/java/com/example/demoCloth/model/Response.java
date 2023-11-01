package com.example.demoCloth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Pojo query response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("PRODUCT_ID")
    private Long product_id;

    @JsonProperty("PRICE_LIST")
    private Integer price_list;

    @JsonProperty("START_DATE")
    private LocalDateTime start_date;

    @JsonProperty("END_DATE")
    private LocalDateTime end_date;

    @JsonProperty("PRICE")
    private BigDecimal price;

    @JsonProperty("PRIORITY")
    private String priority;

    @JsonProperty("CURR")
    private String curr;

    @JsonProperty("BRAND_ID")
    private Long brand_id;

    /**
     * Check if two objects and identical.
     *
     * @param o Object to compare.
     * @return true if both Responses contains the same information.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Response Response = (Response) o;
        return Objects.equals(this.product_id, Response.product_id) &&
                Objects.equals(this.brand_id, Response.brand_id) &&
                Objects.equals(this.price_list, Response.price_list) &&
                Objects.equals(this.start_date, Response.start_date) &&
                Objects.equals(this.end_date, Response.end_date) &&
                Objects.equals(this.price, Response.price);
    }

    /**
     * Returns the hash of the object.
     *
     * @return integer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(product_id, brand_id, price_list, start_date, end_date, price);
    }

    /**
     * Returns the string representation of the object.
     *
     * @return String.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Response {\n");

        sb.append("    product_id: ").append(toIndentedString(product_id)).append("\n");
        sb.append("    brand_id: ").append(toIndentedString(brand_id)).append("\n");
        sb.append("    price_list: ").append(toIndentedString(price_list)).append("\n");
        sb.append("    start_date: ").append(toIndentedString(start_date)).append("\n");
        sb.append("    end_date: ").append(toIndentedString(end_date)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
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
