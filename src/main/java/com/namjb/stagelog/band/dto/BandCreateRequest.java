package com.namjb.stagelog.band.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BandCreateRequest {

    @NotBlank(message = "밴드이름 입력해주세요")
    @Size(max = 50,message = "밴드이름은 50자 이하여야합니다")
    private String name;

}
