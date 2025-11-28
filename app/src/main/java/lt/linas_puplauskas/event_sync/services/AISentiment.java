package lt.linas_puplauskas.event_sync.services;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import lt.linas_puplauskas.event_sync.constants.Constants;

public class AISentiment {
    public static String analyze(String feedback) throws Exception {
        try (LanguageServiceClient language = LanguageServiceClient.create()) {

            Document doc = Document.newBuilder()
                    .setContent(feedback).setType(Document.Type.PLAIN_TEXT).build();

            Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

            if (sentiment.getScore() > 0.25) return Constants.POSITIVE;
            if (sentiment.getScore() < -0.25) return Constants.NEGATIVE;
            return Constants.NEUTRAL;
        }
    }
}
