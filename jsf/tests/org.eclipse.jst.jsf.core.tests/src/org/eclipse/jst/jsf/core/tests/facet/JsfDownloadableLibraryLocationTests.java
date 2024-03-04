/******************************************************************************
 * Copyright (c) 2001, 2024 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Debajit Adhikary - initial API and implementation
 *****************************************************************************/

package org.eclipse.jst.jsf.core.tests.facet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Tests for JSF downloadable-library locations.
 *
 * @author Debajit Adhikary
 *
 */
public class JsfDownloadableLibraryLocationTests extends TestCase {
	private static final String CATALOG_URL_PREFIX = "https://www.eclipse.org/webtools/jsf/jsf-library";
	//@formatter:off
	private static final String[] CATALOG_URLS = {
			CATALOG_URL_PREFIX + "/jsf-2.1-downloadable-libraries.xml",
			CATALOG_URL_PREFIX + "/jsf-2.0-downloadable-libraries.xml",
			CATALOG_URL_PREFIX + "/jsf-1.2-downloadable-libraries.xml",
			CATALOG_URL_PREFIX + "/jsf-1.1-downloadable-libraries.xml"
	};
	//@formatter:on

	private Proxy httpProxy = Proxy.NO_PROXY;
	private final List<String> downloadUrls;

	/**
	 * @param name
	 */
	public JsfDownloadableLibraryLocationTests(final String name) {
		super(name);
		downloadUrls = new ArrayList<String>();
	}

	/**
	 * Extracts the downloadable library URL's from the catalog files and adds them
	 * to the downloadUrls list.
	 *
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setProxy();

		for (final String catalog : CATALOG_URLS) {
			// Extract the download-library url
			final Document document = getXmlDocument(catalog);
			final NodeList nodes = evaluateXpathQuery(document, "//download-url/text()");
			for (int i = 0; i < nodes.getLength(); ++i) {
				final String downloadUrl = nodes.item(i).getNodeValue();
				downloadUrls.add(downloadUrl);
				System.out.println("JSF library download URL (extracted from catalog): " + downloadUrl);
			}
		}
	}

	private void setProxy() throws IOException {
		final String PROXY_TEST_URL = "http://www.eclipse.org/";
		final List<Proxy> possibleProxies = new ArrayList<Proxy>();
		// add direct (no proxy)
		possibleProxies.add(Proxy.NO_PROXY);
		// add system-specified proxy (if any)
		final String sysProxyHost = System.getProperty("http.proxyHost");
		if (sysProxyHost != null) {
			final String sysProxyPort = System.getProperty("http.proxyPort");
			if (sysProxyPort != null) {
				int proxyPort = -1;
				try {
					proxyPort = Integer.parseInt(sysProxyPort);
				} catch (NumberFormatException nfex) {
					// do nothing - proxyPort remains an invalid value
				}
				if (proxyPort > -1) {
					possibleProxies.add(new Proxy(Type.HTTP, new InetSocketAddress(sysProxyHost, proxyPort)));
				}
			}
		}
		// add Oracle-specific proxy
		possibleProxies.add(new Proxy(Type.HTTP, new InetSocketAddress("www-proxy.us.oracle.com", 80)));
		final URL proxyTestURL = new URL(PROXY_TEST_URL);
		for (final Proxy proxy : possibleProxies) {
			InputStream is = null;
			try {
				final URLConnection connection = proxyTestURL.openConnection(proxy);
				is = connection.getInputStream();
				// if we get to here, the current proxy is viable
				httpProxy = proxy;
				break;
			} catch (IOException ioex) {
				// do nothing - current proxy is not viable and was not set
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException ioex) {
						// do nothing
					}
				}
			}
		}
	}

	/**
	 * Validates all the downloadable-library URL's extracted from the catalog file.
	 *
	 * @throws IOException
	 */
	public void testDownloadLibraryUrls() throws IOException {
		for (final String url : downloadUrls) {
			assertTrue("Cannot download library from: " + url, isValidUrl(url));
		}
	}

	private Document getXmlDocument(final String url)
			throws ParserConfigurationException, MalformedURLException, IOException, SAXException {
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final URL catalogUrl = new URL(url);
		final URLConnection catalogUrlConnection = catalogUrl.openConnection(httpProxy);
		return builder.parse(catalogUrlConnection.getInputStream());
	}

	private NodeList evaluateXpathQuery(final Document xmlDocument, final String xpathQuery)
			throws XPathExpressionException {
		final XPath xpath = XPathFactory.newInstance().newXPath();
		final XPathExpression expr = xpath.compile(xpathQuery);
		return (NodeList) expr.evaluate(xmlDocument, XPathConstants.NODESET);
	}

	/**
	 * Validates a URL by connecting to it and checking the HTTP response code.
	 *
	 * @param urlString URL to check.
	 *
	 * @return True if the HTTP response code indicates success (Response code 2xx.
	 *         See http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html)
	 *
	 * @throws IOException
	 */
	private boolean isValidUrl(final String urlString) throws IOException {
		final URL url = new URL(urlString);
		final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(httpProxy);
		urlConnection.connect();
		int responseCode = -1;
		try {
			responseCode = urlConnection.getResponseCode();
		} catch (SocketException sockEx) {
			// ignore and proceed with response code == -1
		}
		return 200 <= responseCode && responseCode <= 299;
	}
}
