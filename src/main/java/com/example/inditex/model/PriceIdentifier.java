package com.example.inditex.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceIdentifier {

    @Min(value = 0, message = "Brand ID should be bigger than 0")
    @NotNull(message = "Brand ID cannot be null")
    private Integer brandId;

    @NotBlank(message = "Application date cannot be null")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}-[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}", message = "Application date is not in the correct format")
    private String applicationDate;

    @Min(value = 0, message = "Product ID should be bigger than 0")
    @NotNull(message = "Product ID cannot be null")
    private Integer productId;
}
