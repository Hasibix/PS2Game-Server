package net.hasibix.ps2game.server.models.client.payloads;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import com.harium.postgrest.Condition;
import com.harium.postgrest.Insert;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.ICredentialRepository;
import net.hasibix.ps2game.server.models.client.exceptions.users.InvalidEmailException;
import net.hasibix.ps2game.server.utils.Logger;
import net.hasibix.ps2game.server.utils.ObjUtils;
import net.hasibix.ps2game.server.utils.Supabase;
import net.hasibix.ps2game.server.utils.TwoFAUtils;

public class User {
    private static final GoogleAuthenticator gAuth = TwoFAUtils.Initialize().getClient();

    public enum Provider {
        Email,
        Apple,
        Discord,
        Facebook,
        GitHub,
        Google,
        Microsoft
    }
        
    @Nonnull private String id;
    @Nonnull private Provider provider = Provider.Email;
    @Nonnull private String username = "Player";
    @Nonnull private String email;
    @Nonnull private String password;
    @Nonnull private String avatarUrl;
    @Nonnull private boolean use2FA = false;
    @Nonnull private ICredentialRepository secretKey2FA = null;
    
    SecureRandom random = new SecureRandom();
    private static final Logger logger = Logger.of(User.class);

    public User() {
        this.id = Integer.toString(random.nextInt(10));
        gAuth.createCredentials(this.id);
        this.secretKey2FA = gAuth.getCredentialRepository();
    }
    
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setUsername(String username) {
        this.username = !ObjUtils.String.isEmpty(username) ? username : "Player";
    }

    public void setEmail(String email) {
        if(validateEmailAddress(email)) {
            this.email = email;
        } else {
            logger.error("An exception occured while trying to set email address of user " + this.id + "!");
            logger.trace(new InvalidEmailException("The provided email address is not valid!"));
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void use2FA(boolean behaviour) {
        this.use2FA = behaviour;
    }

    public String getId() {
        return id;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public ICredentialRepository get2FASecKey() {
        return secretKey2FA;
    }

    public void pushToDB() {
        
    }

    public User pullFromDB() {
        
        return null;
    }

    public static boolean validateEmailAddress(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
            .matcher(email)
            .matches();
    }
}
