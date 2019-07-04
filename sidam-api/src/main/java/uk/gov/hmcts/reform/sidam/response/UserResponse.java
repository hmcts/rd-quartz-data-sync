package uk.gov.hmcts.reform.sidam.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Builder
@Setter
@Getter
public class UserResponse implements Serializable {


    private static final long serialVersionUID = 9149764756848116150L;

    private Boolean active;
    private String email;
    private String forename;
    private String id;
    private Boolean locked;
    private List<String> roles;
    private String surname;
}
