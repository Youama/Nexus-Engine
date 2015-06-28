package com.youama.nexus.crawler;

/**
 * This class handles the URL as a string, doesn't the content of the URL.
 *
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.06.27.
 */
public class Url {

    /**
     * Url without HTTP definition.
     */
    protected String url = "";

    /**
     * Domain address from URL.
     */
    protected String domain = "";

    /**
     * Most common, possible resource extensions.
     */
    protected String[] extensions = {"jpg", "jpeg", "png", "gif", "bmp", "js", "css", "sass", "less"};

    /**
     * File extension from current URL. It can be fake extension.
     */
    protected String extension = "";

    /**
     * Current URL is a kind of resource, depends by self.extensions property.
     */
    protected boolean resourceUrl = false;

    /**
     * Url secure state by http or https sub string.
     */
    protected boolean secure = false;

    /**
     * It sets url to the property, removes the HTTP or HTTPS prefixes. It also sets the the secure state in the
     * property. It resets the property values before it should sets the new values.
     *
     * @param url Any kind of url.
     */
    public void setUrl(String url) {
        reset();
        this.url = this.computeUrlWithProtocol(url);
        setDomainFromUrl();
    }

    /**
     * Resetting of property - when setUrl() is called.
     */
    protected void reset() {
        this.domain = "";
        this.extension = "";
        this.resourceUrl = false;
        this.secure = false;
    }

    /**
     * It retrieves the URL without HTTP and HTTPS prefixes. It sets the secure property to true when URL starts by
     * https:// string.
     *
     * @param url Any kind of URL.
     * @return The URL without HTTP and HTTPS.
     */
    protected String computeUrlWithProtocol(String url) {
        if (url.startsWith("http://")) {
            this.secure = false;
            return url.substring(7);
        } else if (url.startsWith("https://")) {
            this.secure = true;
            return url.substring(8);
        }

        this.secure = false;
        return url;
    }

    /**
     * It retrieves the url from property without http prefix.
     *
     * @return The url.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * It finds the domain address in the URL and sets it to the property.
     */
    protected void setDomainFromUrl() {
        this.domain = "";
        char[] urlArray = this.url.toCharArray();

        for (char character : urlArray) {
            if (character != '/' && character != ':' && character != '?') {
                this.domain += character;
            } else {
                break;
            }
        }
    }

    /**
     * It retrieves the domain address.
     *
     * @return Typical domain address.
     */
    public String getDomain() {
        return this.domain;
    }

    /**
     * It retrieves the url in the property is secure or not.
     *
     * @return True when it is secure.
     */
    public boolean isSecure() {
        return this.secure;
    }

    /**
     * It retrieves the given string is a resource file extension or is not, e.g. css, js. It is defined in the
     * property.
     *
     * @param extension Any kind of file extension. It also can be wrong extension.
     * @return It is true when this is a resource extension.
     */
    protected boolean isResourceExtension(String extension) {
        int length = this.extensions.length;

        for (int i = 0; i <= length - 1; i++) {
            if (extension.toLowerCase().equals(this.extensions[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * It retrieves the extension from url, e.g. "css" from domain.com/style.css.
     *
     * @return Extension. It can be a non defined extension.
     */
    protected String getExtensionFromUrl() {
        char[] urlArray = this.url.toCharArray();
        int arrayLength = urlArray.length;
        String extension = "";

        for (int i = arrayLength - 1; i > 0; i--) {
            if (urlArray[i] != '.' && i > arrayLength - 7) {
                extension = urlArray[i] + extension;
            } else {
                break;
            }
        }

        return extension;
    }

    /**
     * It retrieves the URL is a resource URL or is not. Before retrieve the return value it saves to the property.
     *
     * @return It is true in case of "domain.com/test.css" or "domain.com/test.js" for example.
     */
    public boolean isResourceUrl() {
        if (this.extension.length() <= 0) {
            this.extension = getExtensionFromUrl();
            this.resourceUrl = isResourceExtension(extension);
        }

        return this.resourceUrl;
    }
}
