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
    private static final GoogleAuthenticator gAuth = TwoFAUtils.Initialize().GetClient();

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
    
    public void SetProvider(Provider provider) {
        this.provider = provider;
    }

    public void SetUsername(String username) {
        this.username = !ObjUtils.String.IsEmpty(username) ? username : "Player";
    }

    public void SetEmail(String email) {
        if(ValidateEmailAddress(email)) {
            this.email = email;
        } else {
            logger.error("An exception occured while trying to Set email address of user " + this.id + "!");
            logger.trace(new InvalidEmailException("The provided email address is not valid!"));
        }
    }

    public void SetPassword(String password) {
        this.password = password;
    }

    public void SetAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void Use2FA(boolean behaviour) {
        this.use2FA = behaviour;
    }

    public String GetId() {
        return id;
    }

    public Provider GetProvider() {
        return provider;
    }

    public String GetUsername() {
        return username;
    }

    public String GetEmail() {
        return email;
    }

    public String GetPassword() {
        return password;
    }

    public String GetAvatarUrl() {
        return avatarUrl;
    }

    public ICredentialRepository Get2FASecKey() {
        return secretKey2FA;
    }

    public void PushToDB() {
        
    }

    public User PullFromDB() {
        
        return null;
    }

    public static boolean ValidateEmailAddress(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
            .matcher(email)
            .matches();
    }
}
