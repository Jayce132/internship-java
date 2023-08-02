package ro.nymphis.model.user;

public enum Roles {
    ROLE_USER("ROLE_USER");

    private final String roleName;

    Roles(final String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
