package ru.mattgroy.conspectusshare.models;

import org.springframework.security.oauth2.core.user.OAuth2User;

public class GoogleUser extends CustomOAuth2User {

    public GoogleUser(OAuth2User oauth2User) {
        super(oauth2User);
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }

    @Override
    public String getPrincipalId() {
        return oauth2User.getAttribute("sub");
    }

    @Override
    public String getFirstName() {
        return oauth2User.getAttribute("given_name");
    }

    @Override
    public String getLastName() {
        return oauth2User.getAttribute("family_name");
    }

    @Override
    public String getEmail() {
        return oauth2User.getAttribute("email");
    }

    @Override
    public String getPhoto() {
        return oauth2User.getAttribute("picture");
    }
}
