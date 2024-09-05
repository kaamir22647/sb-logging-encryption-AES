package com.example.logging;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

@Plugin(name = "EncryptedLogAppender", category = "Core", elementType = "appender", printObject = true)
public class LogEncryptionConverter extends AbstractAppender {

    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final byte[] SECRET_KEY = "1234567890123456".getBytes();
    private static final String LOG_FILE_PATH =  "/D:/WORKSPACE/Springboot/Master Spring boot udemy/logging//logs/encrypted-log.txt";// Specify the path for the log file

    // Constructor ensuring layout is not null
    protected LogEncryptionConverter(String name, Layout<?> layout) {
        super(name, null, layout == null ? PatternLayout.createDefaultLayout() : layout, true);
        System.out.println("LogEncryptionConverter initialized with layout.");
    }

    @Override
    public void append(LogEvent event) {
        try {
            // Get the log message
            String logMessage = new String(getLayout().toByteArray(event));

            // Encrypt the log message
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY, ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] encryptedLogMessage = cipher.doFinal(logMessage.getBytes());
            String encryptedMessage = Base64.getEncoder().encodeToString(encryptedLogMessage);

            // Write encrypted message to the log file
            try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
                writer.write(encryptedMessage + System.lineSeparator());
            }
        } catch (Exception e) {
            // Print exception
            e.printStackTrace();
        }
    }

    @PluginFactory
    public static LogEncryptionConverter createAppender() {
        return new LogEncryptionConverter("EncryptedLogAppender", null);
    }
}
