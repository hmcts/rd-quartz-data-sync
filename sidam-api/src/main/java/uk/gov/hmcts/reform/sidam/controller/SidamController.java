package uk.gov.hmcts.reform.sidam.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.sidam.response.UserResponse;
import uk.gov.hmcts.reform.sidam.response.UserRolesUpdatedResource;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Slf4j
@RestController
public class SidamController {

    private final static String organisationManager = "pui-organisation-manager";
    private final static String userManager = "pui-user-manager";
    private final static String financeManager = "pui-finance-manager";
    private final static String caseManager = "pui-case-manager";

    @ApiOperation("Register a User Profile")
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "Register a User Profile using request body"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Bad Request",
                    response = String.class
            ),
            @ApiResponse(
                    code = 500,
                    message = "Internal Server Error",
                    response = String.class
            )
    })

    @PostMapping(
            consumes = APPLICATION_JSON_UTF8_VALUE,
            produces = APPLICATION_JSON_UTF8_VALUE,
            path = "/user/registration"
    )
    @ResponseBody
    public ResponseEntity<Object> createUserProfile(@Valid @RequestBody Object createUserProfileData) {
        log.info("Creating new User Profile");

        requireNonNull(createUserProfileData, "createUserProfileData cannot be null");

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @ApiOperation("Get a User by email")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Register a User Profile using request body",
                    response =  UserResponse.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Bad Request",
                    response = String.class
            ),
            @ApiResponse(
                    code = 500,
                    message = "Internal Server Error",
                    response = String.class
            )
    })

    @GetMapping(
            produces = APPLICATION_JSON_UTF8_VALUE,
            path = "/api/v1/users"
    )
    public ResponseEntity/*<UserRolesUpdatedResource>*/ getUserByEmail(@RequestParam(required=false) String email, @RequestParam(required=false) String query){
        log.info("Get User by id");
        if(email != null){

            log.info("Get User by id");
            UserResponse responseBody = UserResponse.builder().active(true).email(email)
                    .forename("Prashanth").id(UUID.randomUUID().toString()).surname("Kotla").locked(false).id("6884ec4e-d6d9-4803-8a93-7d88c8acb645").roles(new ArrayList<>()).build();
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }


        if(query != null){
            log.info("query:" + query);

            List<String> roles = new ArrayList<>();
            List<String> allRoles = new ArrayList<>();


            roles.add(organisationManager);

            allRoles.add(organisationManager);
            allRoles.add(userManager);
            allRoles.add(financeManager);
            allRoles.add(caseManager);

            Map<String, UserResponse> userResponseData = new HashMap<>();

            userResponseData.put("8e143a1f-f79c-4281-8540-d8f3067296d6", buildUserResponse("Alistair","ZCox", "akio.cox@hmcts.net", "8e143a1f-f79c-4281-8540-d8f3067296d6", allRoles));
            userResponseData.put("62cd5bcc-f221-439e-af40-11c007521311", buildUserResponse("Adil", "XMay","adil.oozeerally@hmcts.net", "62cd5bcc-f221-439e-af40-11c007521311", roles));
            userResponseData.put("6884ec4e-d6d9-4803-8a93-7d88c8acb645", buildUserResponse("Prashanth", "YCameron", "prashanth.kotla@hmcts.net", "6884ec4e-d6d9-4803-8a93-7d88c8acb645", roles));

            UserRolesUpdatedResource result = new UserRolesUpdatedResource();
            result.setUserResponseData(userResponseData);

            return ResponseEntity.status(HttpStatus.OK).body(result);

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    }

    private UserResponse buildUserResponse(String firstName, String lastName, String email, String id, List<String> roles) {
        return UserResponse.builder().active(true).email(email)
                        .forename(firstName).surname(lastName).id(id).locked(false).roles(roles).build();
    }

    @ApiOperation("Get a User by Id")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Register a User Profile using request body",
                    response =  UserResponse.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Bad Request",
                    response = String.class
            ),
            @ApiResponse(
                    code = 500,
                    message = "Internal Server Error",
                    response = String.class
            )
    })

    @GetMapping(
            produces = APPLICATION_JSON_UTF8_VALUE,
            path = "/api/v1/users/{userId}"
    )
    public ResponseEntity<Object> getUserById(@PathVariable String userId){
        log.info("Get User by id");
        List<String> roles = new ArrayList<String>();
        roles.add("pui-organisation-manager");
        UserResponse responseBody = UserResponse.builder().active(true).email("shreedhar.lomte@hmcts.net")
                .forename("Shreedhar").id(userId).surname("Lomte").locked(false).roles(roles).build();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

//    @ApiOperation("Get all users that have changed for a particular a role")
//    @ApiResponses({
//            @ApiResponse(
//                    code = 200,
//                    message = "Return an array of users that have changed or empty array",
//                    response =  UserResponse.class
//            ),
//            @ApiResponse(
//                    code = 400,
//                    message = "Bad Request",
//                    response = String.class
//            ),
//            @ApiResponse(
//                    code = 500,
//                    message = "Internal Server Error",
//                    response = String.class
//            )
//    })
//
//    @GetMapping(
//            produces = APPLICATION_JSON_UTF8_VALUE,
//            path = "/api/v1/users/{query}"
//                    //+ "?query=lastModified:>now-24h"
//                    //+ " AND (roles:pui-case-manager OR roles:pui-finance-manager OR roles:pui-organisation-manager OR roles:pui-user-manager)"
//    )
//    public ResponseEntity<Object> getUpdatedUsersByRole(@PathVariable String query){
//        log.info("Get User by id");
//        List<String> roles = new ArrayList<String>();
//        roles.add("pui-organisation-manager");
//        log.info("query:" + query);
//        UserResponse responseBody = UserResponse.builder().active(true).email("akio.cox@hmcts.net")
//                .forename("Akio").surname("Cox").locked(false).roles(roles).build();
//        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
//    }

}
