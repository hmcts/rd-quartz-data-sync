package uk.gov.hmcts.reform.sidam.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Data
public class UpdatedUserArrayResource implements Serializable {

    private static final long serialVersionUID = 9149764756848116149L;

    // private List<UserResponse> userResponseData;
    private Object[] userResponseData;


}
