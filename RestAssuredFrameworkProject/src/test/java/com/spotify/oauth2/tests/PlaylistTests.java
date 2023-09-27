package com.spotify.oauth2.tests;

import static com.spotify.oauth2.utility.FakerUtils.generateDescription;
import static com.spotify.oauth2.utility.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import com.spotify.oauth2.annotations.BaseAnnotation;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.api.enums.AuthorType;
import com.spotify.oauth2.api.enums.CategoryType;
import com.spotify.oauth2.api.enums.StatusCode;
import com.spotify.oauth2.utility.UtilityConstants;
import com.spotify.oauth2.requests.pojo.lombok.Error;
import com.spotify.oauth2.requests.pojo.lombok.Playlist;
import com.spotify.oauth2.utility.DataLoader;
import com.spotify.oauth2.utility.VerificationManager;

import io.restassured.response.Response;

@Epic("Spotify")
@Feature("Playlist API")
public class PlaylistTests extends _BaseTest {


    @Story("Create a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description - From ")
    @Severity(SeverityLevel.BLOCKER)
    @BaseAnnotation(author = { AuthorType.Tester1, AuthorType.Tester2},
	category = { CategoryType.SMOKE,CategoryType.SANITY, CategoryType.REGRESSION })
    @Test(priority = 0, groups = {"SMOKE","SANITY","REGRESSION"}, description = "create a new playlist - TestNG")
    public void CreateNewPlaylist(){
        Playlist requestPlaylist = playlistBuilder("NewTestPlaylist2024", "NewTestPlaylist2024", true);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201,"New playlist created successfully");
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }


    @Severity(SeverityLevel.BLOCKER)
    @BaseAnnotation(author = { AuthorType.Tester3, AuthorType.Tester4},
			category = { CategoryType.SMOKE,CategoryType.REGRESSION })
	@Test(priority = 0, groups = {"SMOKE","REGRESSION"}, description = "get details of active playlist - TestNG")
    public void GetPlaylist(){
        Playlist requestPlaylist = playlistBuilder("TestPlaylist2025", "TestPlaylist2025", true);
        Response response = PlaylistApi.get(DataLoader.getInstance().get_GetPlaylistID());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200,"Get playlist details retrieved successfully");
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Severity(SeverityLevel.BLOCKER)
    @BaseAnnotation(author = { AuthorType.Tester1, AuthorType.Tester3},
			category = { CategoryType.BVT,CategoryType.SANITY,CategoryType.REGRESSION })
	@Test(priority = 0, groups = {"BVT","SANITY","REGRESSION"}, description = "update playlist details - TestNG")
    public void UpdatePlaylist(){
        Playlist requestPlaylist = playlistBuilder("TestPlaylist2025", "TestPlaylist2025", true);
        Response response = PlaylistApi.update(DataLoader.getInstance().get_UpdatePlaylistID(), requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200,"Update playlist details successfully");
    }


    @Story("Create a playlist story")
    @Severity(SeverityLevel.NORMAL)
    @BaseAnnotation(author = { AuthorType.Tester1, AuthorType.Tester3},
	category = { CategoryType.BVT,CategoryType.REGRESSION })
    @Test(priority = 2, groups = {"BVT","REGRESSION"})
    public void CreatePlaylistWithoutName(){
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400, "User should not be able to create a Playlist without Name");
        assertError(response.as(Error.class), StatusCode.CODE_400);
    }

    @Story("Create a playlist story")
    @Severity(SeverityLevel.CRITICAL)
    @BaseAnnotation(author = { AuthorType.Tester2, AuthorType.Tester4},
	category = { CategoryType.BVT,CategoryType.REGRESSION })
    @Test(priority = 1, groups = {"BVT","REGRESSION"})
    public void CreatePlaylistWithExpiredToken(){
        String invalid_token = "12345";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(invalid_token, requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401, "User should not be able to create a Playlist with Expired Token");
        assertError(response.as(Error.class), StatusCode.CODE_401);
    }


    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public){
		Playlist playlist = Playlist.builder().name(name)
                          .description(description)
                          ._public(_public)
                          .build();
		  return playlist;
    }

    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
        
        VerificationManager.validateResponse(responsePlaylist.getName(),requestPlaylist.getName(),
        		UtilityConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD+" - <b> <u> NAME </u> </b>");
        VerificationManager.validateResponse(responsePlaylist.getDescription(),requestPlaylist.getDescription(),
        		UtilityConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD+" - <b> <u> DESCRIPTION </u> </b>");
        VerificationManager.validateResponse(responsePlaylist.get_public(),requestPlaylist.get_public(),
        		UtilityConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD+" - <b> <u> PUBLIC </u> </b>");
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode, String message){
        assertThat(actualStatusCode, equalTo(statusCode.code));
    	VerificationManager.validateResponse(actualStatusCode,statusCode.code,
    	UtilityConstants.ASSERTION_FOR_RESPONSE_STATUS_CODE +" - <b> <u> "+message+" </u> </b>");
    }

    @Step
    public void assertError(Error responseErr, StatusCode statusCode){
        assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
        assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
        
        VerificationManager.validateResponse(responseErr.getError().getStatus(),statusCode.code,
        		UtilityConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD+" - <b> <u> STATUS </u> </b>");
        VerificationManager.validateResponse(responseErr.getError().getMessage(),statusCode.msg,
        		UtilityConstants.ASSERTION_FOR_RESPONSE_CUSTOM_FIELD+" - <b> <u> MSG </u> </b>");
    }

}
