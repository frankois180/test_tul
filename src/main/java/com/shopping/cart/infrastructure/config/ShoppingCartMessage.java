package com.shopping.cart.infrastructure.config;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public class ShoppingCartMessage {
    public enum MsgName {

        INFO_API_NAME,
        DATA_NOT_FOUND,
        PRODUCT_TYPE_NOT_FOUND,
        BAD_REQUEST,
        BAD_REQUEST_EMPTY,
        BAD_REQUEST_STATE_PURCHASE;

    }

    private static MessageSourceAccessor accessor;

    static {
        accessor = initAccessor();
    }

    private static MessageSourceAccessor initAccessor() {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return new MessageSourceAccessor(messageSource, Locale.forLanguageTag("es"));

    }

    public static String msg(ShoppingCartMessage.MsgName name) {

        String asProperty = name.toString().toLowerCase().replace("_", ".");
        return accessor.getMessage(asProperty);

    }

    public static String msg(ShoppingCartMessage.MsgName name, Object... params) {

        String asProperty = name.toString().toLowerCase().replace("_", ".");
        return accessor.getMessage(asProperty, params);

    }

    public static String apiName() {

        return msg(ShoppingCartMessage.MsgName.INFO_API_NAME);

    }

}
