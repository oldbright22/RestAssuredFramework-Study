package com.spotify.oauth2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.spotify.oauth2.api.enums.AuthorType;
import com.spotify.oauth2.api.enums.CategoryType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BaseAnnotation {

	// This is not a method
	public AuthorType[] author();

	// public String[] category();
	public CategoryType[] category();

}
