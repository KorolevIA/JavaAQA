package FinishProject.core.model;

public record AuthResponse(String userToken, String role, String displayName, String login) {
}
