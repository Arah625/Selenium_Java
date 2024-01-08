package Utilities;

public enum SocialMediaData {

    TWITTER("Twitter", "Zaloguj się do serwisu X / X", "https://twitter.com/i/flow/login?redirect_after_login=%2Fowasp_juiceshop"),
    FACEBOOK("Facebook", "OWASP Juice Shop | Facebook", "https://www.facebook.com/owasp.juiceshop"),
    SLACK("Slack", "Slack Invite | OWASP Foundation", "https://owasp.org/slack/invite"),
    REDDIT("Reddit", "owasp_juiceshop", "https://www.reddit.com/r/owasp_juiceshop/"),
    PRESS_KIT("Press Kit", "owasp-swag/projects/juice-shop at master · OWASP/owasp-swag · GitHub", "https://github.com/OWASP/owasp-swag/tree/master/projects/juice-shop"),
    NFT("NFT", "Juice Shop - Collection | OpenSea", "https://opensea.io/collection/juice-shop");

    private final String socialMediaPlatform;
    private final String pageTitle;
    private final String url;

    SocialMediaData(String socialMediaPlatform, String pageTitle, String url) {
        this.socialMediaPlatform = socialMediaPlatform;
        this.pageTitle = pageTitle;
        this.url = url;
    }

    public String getSocialMediaPlatform() {
        return socialMediaPlatform;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getUrl() {
        return url;
    }
}
