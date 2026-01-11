package net.thewesthill.wps.model.doclibs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocLibsRequest {

    @NotNull(message = "Please Input Valid PageSize.")
    private Integer page_size;

    private String page_token;

    private String[] user_role;

}
