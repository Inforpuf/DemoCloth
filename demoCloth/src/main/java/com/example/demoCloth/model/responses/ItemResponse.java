package com.example.demoCloth.model.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Item", description = "Operation response")
public class ItemResponse {

    @Schema(name = "Product ID", example = "35455", description = "Unique identifier of the product.")
    @JsonProperty("productId")
    private Long productId;

    @Schema(name = "Brand ID", example = "1", description = "Unique identifier of the brand.")
    @JsonProperty("brandId")
    private Long brandId;

    @Schema(name = "List of price", example = "5", description = "Number of the price list.")
    @JsonProperty("priceList")
    private Integer priceList;

    @JsonProperty("startDate")
    @JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss")
    @Schema(name = "Start Date", example = "2020-06-14-00.00.00", description = "Start Date for item being active.")
    private LocalDateTime startDate;

    @JsonProperty("endDate")
    @JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss")
    @Schema(name = "End Date", example = "2020-06-14-00.00.00", description = "End Date for item being active.")
    private LocalDateTime endDate;

    @Schema(name = "Price", example = "35.50", description = "Price in Euros.")
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
        ItemResponse itemResponse = (ItemResponse) o;
        return Objects.equals(this.productId, itemResponse.productId) &&
                Objects.equals(this.brandId, itemResponse.brandId) &&
                Objects.equals(this.priceList, itemResponse.priceList) &&
                Objects.equals(this.startDate, itemResponse.startDate) &&
                Objects.equals(this.endDate, itemResponse.endDate) &&
                Objects.equals(this.price, itemResponse.price);
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
