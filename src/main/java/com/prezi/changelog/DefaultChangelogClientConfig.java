package com.prezi.changelog;

public class DefaultChangelogClientConfig implements ChangelogClientConfig {
    public static final String ENDPOINT_PROPERTY = "com.prezi.changelog.endpoint";
    public static final String CRITICALITY_PROPERTY = "com.prezi.changelog.criticality";
    public static final String CATEGORY_PROPERTY = "com.prezi.changelog.category";
    public static final String AUTH_PROVIDER_TYPE_PROPERTY = "com.prezi.changelog.auth.providerType";

    public String defaultCriticality() { return "1"; }
    public String defaultCategory() { return "misc"; }
    public ChangelogAuthProviderType defaultAuthProviderType() { return ChangelogAuthProviderType.NOOP; }

    @Override
    public String getEndpoint() {
        return System.getProperty(ENDPOINT_PROPERTY);
    }

    @Override
    public Integer getCriticality() {
        return Integer.parseInt(System.getProperty(CRITICALITY_PROPERTY, defaultCriticality()), 10);
    }

    @Override
    public String getCategory() {
        return System.getProperty(CATEGORY_PROPERTY, defaultCategory());
    }

    @Override
    public ChangelogAuthProviderType getAuthProviderType() {
        return ChangelogAuthProviderType.valueOf(
                System.getProperty(AUTH_PROVIDER_TYPE_PROPERTY, defaultAuthProviderType().name())
        );
    }
}
