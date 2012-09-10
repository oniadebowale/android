package eu.ttbox.geoping.test.core.crypto;

import javax.crypto.SecretKey;

import android.location.Location;
import android.test.AndroidTestCase;
import android.util.Log;
import eu.ttbox.geoping.core.crypto.DesEncrypter;
import eu.ttbox.geoping.domain.GeoTrackSmsMsg;
import eu.ttbox.geoping.service.SmsMsgActionHelper;
import eu.ttbox.geoping.service.SmsMsgEncryptHelper;

public class DesEncrypterTest extends AndroidTestCase {

    public static final String TAG = "DesEncrypterTest";

    public String getMessage() {
        return "Test message to encrypt";
    }

    public String getMessageLoc() {
        String provider = "network";
        Location loc = new Location(provider);
        loc.setTime(System.currentTimeMillis());
        loc.setLatitude(43.15854941164189446d);
        loc.setLongitude(25.218546646446d);
        loc.setAccuracy(120.258446418974f);
        loc.setAltitude(124.6546533464d);
        loc.setBearing(257.16416464646446464646413f);
        loc.setSpeed(125.1464646464468946444646f);
        // Convertion 2 String
        GeoTrackSmsMsg geoTrackMsg = SmsMsgActionHelper.geoLocMessage(loc);
        String msg = SmsMsgEncryptHelper.encodeSmsMessage(geoTrackMsg);
        return msg;
    }
    
    

    public void testEncrypt() throws Exception {
        String clearText = getMessage();
        Log.d(TAG, "clearText Size : " + clearText.length() + " / for msg : " + clearText);
        // encrypt
        SecretKey key = DesEncrypter.generateKey();
        DesEncrypter encrypter = new DesEncrypter(key);

        String encryped = encrypter.encrypt(clearText);
        Log.d(TAG, "encryped Size : " + encryped.length() + " / for msg : " + encryped);

    }



    public void testEncryptMessageLoc() throws Exception {
        String clearText = getMessageLoc();
        Log.d(TAG, "clearText Size : " + clearText.length() + " / for msg : " + clearText);
        // encrypt
        SecretKey key = DesEncrypter.generateKey();
        DesEncrypter encrypter = new DesEncrypter(key);

        String encryped = encrypter.encrypt(clearText);
        Log.d(TAG, "encryped Size : " + encryped.length() + " / for msg : " + encryped);

    }
}
