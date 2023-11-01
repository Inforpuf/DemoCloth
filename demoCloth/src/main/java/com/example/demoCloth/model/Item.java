package com.example.demoCloth.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Pojo Rest API get response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("brandId")
    private Long brandId;

    @JsonProperty("priceList")
    private Integer priceList;

    @JsonProperty("startDate")
    @JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime startDate;

    @JsonProperty("endDate")
    @JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime endDate;

    @JsonProperty("price")
    private String price;

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
        Item item = (Item) o;
        return Objects.equals(this.productId, item.productId) &&
                Objects.equals(this.brandId, item.brandId) &&
                Objects.equals(this.priceList, item.priceList) &&
                Objects.equals(this.startDate, item.startDate) &&
                Objects.equals(this.endDate, item.endDate) &&
                Objects.equals(this.price, item.price);
    }

    /**
     * Returns the hash of the object.
     *
     * @return integer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(productId, brandId, priceList, startDate, endDate, price);
    }

    /**
     * Returns the string representation of the object.
     *
     * @return String.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Item {\n");

        sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
        sb.append("    brandId: ").append(toIndentedString(brandId)).append("\n");
        sb.append("    priceList: ").append(toIndentedString(priceList)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
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
