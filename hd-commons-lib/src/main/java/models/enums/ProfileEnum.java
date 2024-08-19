package models.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProfileEnum {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_TECHNICIAN("ROLE_TECHNICIAN");

    private final String description;


    ProfileEnum(String description) {
        this.description = description;
    }

    public static ProfileEnum toEnum(final String description) {
        return Arrays.stream(ProfileEnum.values())
                .filter(profileEnum -> profileEnum.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid description: " + description));


    }
}