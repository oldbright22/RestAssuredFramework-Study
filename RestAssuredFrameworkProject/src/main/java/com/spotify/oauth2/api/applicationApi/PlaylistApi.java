package com.spotify.oauth2.api.applicationApi;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.requests.pojo.lombok.Playlist;
import com.spotify.oauth2.utility.ConfigLoader;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PlaylistApi {

	private static String token ="BQCa-bASrNSGniE3aoZU-rP9L5XFRT1Bsg0iYW2ko9TOB1UUOoenzBrP76snS_QnpUhTVM0E4d78NraD94Bz86obTG2jHRmm_89-z6569mnf5t0p4bKjYZoBjZPsG-RGMXnmO5q1r-uLZop8rTZdkEVnOXLnlNTEY01sRx_a8bP0EJCfkH0aS6W02l6l9gn20nBf2roBD_1fi5_MeXdZp6SIZZtQlYKG0OoemGcPlmPf38E_Dm82fhA3acR21m8OaYVCBC_lrW1wa7GZ";

	@Step
	public static Response post(Playlist requestPlaylist) {
		String User_ID = ConfigLoader.getInstance().getUserID();
		    // RestResource.post("users/"+User_ID+"/playlists",ACCESS_TOKEN,requestPlaylist); //getToken()
		return RestResource.post(USERS +"/"+ User_ID + PLAYLISTS, token, requestPlaylist);
	}

	public static Response post(String payLoad) {
		String User_ID = ConfigLoader.getInstance().getUserID();
		return RestResource.post(USERS +"/"+ User_ID + PLAYLISTS, getToken(), payLoad);
	}

	public static Response post(String accessToken, String payLoad) {
		String User_ID = ConfigLoader.getInstance().getUserID();
		return RestResource.post(USERS +"/"+ User_ID + PLAYLISTS, accessToken, payLoad);
	}

	public static Response get(String Playlist_ID) {
		return RestResource.get(PLAYLISTS +"/"+ Playlist_ID, getToken());
	}

	public static Response put(String Playlist_ID, String payLoad) {
		return RestResource.put(PLAYLISTS +"/"+ Playlist_ID, getToken(), payLoad);
	}

    public static Response post(String token, Playlist requestPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserID()
                + PLAYLISTS, token, requestPlaylist);
    }

    public static Response update(String playlistId, Playlist requestPlaylist){
		return RestResource.put(PLAYLISTS + "/" + playlistId, token, requestPlaylist);
    }

}
