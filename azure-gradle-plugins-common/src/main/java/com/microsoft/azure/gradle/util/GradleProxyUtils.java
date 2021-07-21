/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.microsoft.azure.gradle.util;

import com.azure.core.util.Configuration;
import com.microsoft.azure.toolkit.lib.common.bundle.AzureString;
import com.microsoft.azure.toolkit.lib.common.messager.AzureMessager;
import com.microsoft.azure.toolkit.lib.common.messager.IAzureMessager;
import com.microsoft.azure.toolkit.lib.common.proxy.ProxyManager;

import java.util.Objects;

public class GradleProxyUtils {
    @SuppressWarnings("HttpUrlsUsage")
    public static void configureProxy() {
        final ProxyManager proxyManager = ProxyManager.getInstance();
        ProxyManager.getInstance().init();
        final IAzureMessager messager = AzureMessager.getMessager();
        if (Objects.nonNull(proxyManager.getProxy())) {
            messager.info(AzureString.format("Use proxy: %s:%s", proxyManager.getHttpProxyHost(),
                Integer.toString(proxyManager.getHttpProxyPort())));

            Configuration.getGlobalConfiguration().put(Configuration.PROPERTY_HTTP_PROXY,
                String.format("http://%s:%s", proxyManager.getHttpProxyHost(), proxyManager.getHttpProxyPort()));
        }
    }
}
