package net.hasibix.ps2game.server.utils;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.ICredentialRepository;

public class TwoFAUtils {
    private static final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    private TwoFAUtils() {}

    public static TwoFAUtils Initialize() {
        return new TwoFAUtils();
    }

    public GoogleAuthenticator GetClient() {
        return gAuth;
    }
    
    public void SetSecretKey(ICredentialRepository secretKeysCredentialRepository) {
        gAuth.setCredentialRepository(secretKeysCredentialRepository);
    }

    public String GetPairQRCodeImageUrl() {

        return null;
    }
}

/* OLD VERSION
public class TwoFAUtils {
    private static final Logger logger = Logger.of(TwoFAUtils.class);

    public static String[] Pair(HttpTransport client, String appName, String appInfo, String secretCode) {
        HttpRequest request;

        try {
            request = client.createRequestFactory().buildGetRequest(
                new GenericUrl(
                    String.format("%s?%s&%s&%s",
                        "https://authenticatorapi.com/api.asmx/Pair",
                        "appName=" + new UrlEncodedContent(appName).toString(),
                        "appInfo=" + new UrlEncodedContent(appInfo).toString(),
                        "secretCode=" + new UrlEncodedContent(secretCode).toString() 
                    )
                )
            );
        } catch (IOException e) {
            logger.error("An exception occured while trying to create a pairing request!");
            logger.trace(e);
            request = null;
        }

        HttpResponse response;

        if(request != null) {
            response = HttpRequestSender.SendAsync(request);
        } else {
            response = null;
        }

        if(response != null) {
            try {
                String responseData = response.parseAsString();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(new StringReader(responseData)));

                Element root = doc.getDocumentElement();

                NodeList manualSetupCodeList = root.getElementsByTagName("ManualSetupCode");
                Element manualSetupCodeElement = (Element) manualSetupCodeList.item(0);
                String manualSetupCode = manualSetupCodeElement.getTextContent();

                NodeList htmlList = root.getElementsByTagName("Html");
                Element htmlElement = (Element) htmlList.item(0);
                String html = htmlElement.getTextContent();

                String qrCodeImageUrl = html.substring(html.indexOf("src='") + "src='".length(), html.indexOf("' border=0"));

                List<String> result = new ArrayList<String>(2);
                result.add(manualSetupCode);
                result.add(qrCodeImageUrl);

                return result.toArray(new String[2]);
            } catch (Exception e) {
                logger.error("An exception occured while trying to create a pairing request!");
                logger.trace(e);
            }
        }
        return null;
    }

    public static boolean Validate(HttpTransport client, String pin, String secretCode) {
        HttpRequest request;

        try {
            request = client.createRequestFactory().buildGetRequest(
                new GenericUrl(
                    String.format("%s?%s&%s&%s",
                        "https://authenticatorapi.com/api.asmx/ValidatePin",
                        "pin=" + new UrlEncodedContent(pin).toString(),
                        "secretCode=" + new UrlEncodedContent(secretCode).toString()
                    )
                )
            );
        } catch (IOException e) {
            logger.error("An exception occured while trying to create a validation check request!");
            logger.trace(e);
            request = null;
        }

        HttpResponse response;

        if(request != null) {
            response = HttpRequestSender.SendAsync(request);
        } else {
            response = null;
        }

        if(response != null) {
            try {
                String responseData = response.parseAsString();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(new StringReader(responseData)));

                Element root = doc.getDocumentElement();

                NodeList isValidList = root.getElementsByTagName("boolean");
                Element isValidElement = (Element) isValidList.item(0);
                boolean isValid = Boolean.getBoolean(isValidElement.getTextContent());

                return isValid;
            } catch (Exception e) {
                logger.error("An exception occured while trying to create a validation check request!");
                logger.trace(e);
            }
        }
        return false;
    }
}
*/
