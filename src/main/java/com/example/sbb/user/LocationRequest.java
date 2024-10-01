package com.example.sbb.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationRequest {
    @NotNull(message = "mapX는 필수 항목입니다.")
    @Min(value = -90, message = "mapX는 -90 이상이어야 합니다.")
    @Max(value = 90, message = "mapX는 90 이하이어야 합니다.")
    private Float mapX;

    @NotNull(message = "mapY는 필수 항목입니다.")
    @Min(value = -180, message = "mapY는 -180 이상이어야 합니다.")
    @Max(value = 180, message = "mapY는 180 이하이어야 합니다.")
    private Float mapY;
}
