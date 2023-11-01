package com.example.demoCloth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * Pojo of the table BRAND that contains the brands to query.
 */
@Entity
@Table(name = "BRANDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonProperty("BRAND_NAME")
    private String brandName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Price> prices;


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
        Brand brand = (Brand) o;
        return Objects.equals(this.id, brand.id) &&
                this.brandName.equals(brand.brandName) &&
                this.prices.equals(brand.prices);
    }

    /**
     * Returns the hash of the object.
     *
     * @return integer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, brandName, prices);
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

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    brand_name: ").append(toIndentedString(brandName)).append("\n");
        prices.forEach(item -> sb.append("    price: ").append(item.toString()).append("\n"));
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
