package uk.gov.hmcts.reform.sidam.response;

import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Data
public class UserRolesUpdatedResource implements Serializable {

    private static final long serialVersionUID = 9149764756848116149L;

    //OK private List<UserResponse> userResponseData;
    private Map<String, UserResponse> userResponseData;


}
